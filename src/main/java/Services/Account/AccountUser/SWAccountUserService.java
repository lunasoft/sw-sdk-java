/**
 * SWAccountUserService es una clase que proporciona servicios relacionados con la gestión de usuarios de cuentas.
 * Esta clase extiende SWService y ofrece métodos para crear, actualizar, eliminar y obtener información sobre usuarios de cuentas.
 */
package Services.Account.AccountUser;

import java.io.IOException;
import java.util.UUID;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Requests.Account.AccountUser.AccountUserOptionsRequest;
import Utils.Requests.Account.AccountUser.AccountUserRequest;
import Utils.Responses.IResponse;

/**
 * SWAccountUserService proporciona servicios para gestionar usuarios de
 * cuentas.
 */
public class SWAccountUserService extends SWService {

    /**
     * Constructor que requiere credenciales de autenticación para el servicio.
     *
     * @param user      Nombre de usuario para la autenticación.
     * @param password  Contraseña para la autenticación.
     * @param URI       URI base para la autenticación.
     * @param URIAPI    URI de la API para el servicio.
     * @param proxyHost Host del proxy (puede ser nulo).
     * @param proxyPort Puerto del proxy.
     * @throws AuthException Si la autenticación falla.
     */
    public SWAccountUserService(String user, String password, String URI, String URIAPI, String proxyHost,
            int proxyPort) throws AuthException {
        super(user, password, URI, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Constructor que utiliza un token de autenticación para el servicio.
     *
     * @param token     Token de autenticación.
     * @param URIAPI    URI de la API para el servicio.
     * @param proxyHost Host del proxy (puede ser nulo).
     * @param proxyPort Puerto del proxy.
     */
    public SWAccountUserService(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Crea un nuevo usuario de cuenta con la información proporcionada.
     *
     * @param email        Correo electrónico del nuevo usuario.
     * @param passwordUser Contraseña del nuevo usuario.
     * @param name         Nombre del nuevo usuario.
     * @param rfc          RFC del nuevo usuario.
     * @param stamps       Número de timbres del nuevo usuario.
     * @param profile      Perfil del nuevo usuario.
     * @param unlimited    Indica si el usuario tiene acceso ilimitado.
     * @param active       Indica si el usuario está activo.
     * @return IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */

    public IResponse CrearUsuario(String email, String passwordUser, String name, String rfc, int stamps,
            AccountUserProfiles profile, boolean unlimited, boolean active)
            throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.crearUsuarioRequest(getToken(), getURIAPI(),
                email, passwordUser,
                name, rfc, stamps, profile, unlimited, active, getProxyHost(), getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.createUserRequest(settings);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param idUser    Identificador único del usuario a actualizar.
     * @param name      Nuevo nombre del usuario.
     * @param rfc       Nuevo RFC del usuario.
     * @param unlimited Indica si el usuario tiene acceso ilimitado.
     * @param active    Indica si el usuario está activo.
     * @return IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ActualizarUsuario(UUID idUser, String name, String rfc, boolean unlimited, boolean active)
            throws AuthException, GeneralException, IOException {

        AccountUserOptionsRequest settings = AccountUserOptionsRequest.actualizarUsuarioRequest(getToken(), getURIAPI(),
                idUser, name, rfc,
                unlimited, active, getProxyHost(), getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.updateUserRequest(settings, idUser);
    }

    /**
     * Elimina un usuario existente.
     *
     * @param idUser Identificador único del usuario a eliminar.
     * @return IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse EliminarUsuario(UUID idUser) throws AuthException, GeneralException, IOException {

        AccountUserOptionsRequest settings = AccountUserOptionsRequest.usuarioIdRequest(getToken(), getURIAPI(), idUser,
                getProxyHost(), getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.deleteUserRequest(settings, idUser);
    }

    /**
     * Obtiene la lista de usuarios paginada.
     *
     * @param page     Número de página.
     * @param pageSize Número usuarios por página.
     * @return IResponse con la lista de usuarios.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuarios(int page, int pageSize) throws AuthException, GeneralException, IOException {

        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuariosRequest(getToken(), getURIAPI(),
                page, pageSize,
                getProxyHost(), getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.getAllUsersRequest(settings, page, pageSize);
    }

    /**
     * Obtiene la información del usuario actual.
     *
     * @return IResponse con la información del usuario.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerInfoUsuario() throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuarioPorTokenRequest(getToken(),
                getURIAPI(), getProxyHost(),
                getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.getUserRequest(settings);
    }

    /**
     * Obtiene la información de un usuario específico por su identificador ú
     * ico.
     *
     * @param idUser Identificador único del usuario.
     * @return IResponse con la información del usuario.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerInfoUsuarioId(UUID idUser) throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.usuarioIdRequest(getToken(), getURIAPI(), idUser,
                getProxyHost(), getProxyPort());
        AccountUserRequest req = new AccountUserRequest();
        return req.getUserIdRequest(settings, idUser);
    }
}
