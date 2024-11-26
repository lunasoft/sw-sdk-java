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
import Utils.Requests.Account.AccountUser.AccountUserOptionsRequest;
import Utils.Requests.Account.AccountUser.AccountUserRequest;
import Utils.Responses.IResponse;

/**
 * SWAccountUserService proporciona servicios para gestionar usuarios de
 * cuentas.
 */
public class SWAccountUserService extends SWService {

    /**
     * Constructor que inicializa el servicio utilizando credenciales de
     * autenticación.
     *
     * @param user      Nombre de usuario para la autenticación.
     * @param password  Contraseña para la autenticación.
     * @param URI       URI base para la autenticación.
     * @param URIAPI    URI base para la autenticación.
     * @param proxyHost Host del proxy (puede ser nulo).
     * @param proxyPort Puerto del proxy.
     * @throws AuthException Si la autenticación falla.
     */
    public SWAccountUserService(String user, String password, String URI, String URIAPI, String proxyHost,
            int proxyPort) throws AuthException {
        super(user, password, URI, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Constructor que inicializa el servicio utilizando un token de autenticación.
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
     * @param email             Correo electrónico del nuevo usuario.
     * @param passwordUser      Contraseña del nuevo usuario.
     * @param name              Nombre del nuevo usuario.
     * @param rfc               RFC del nuevo usuario.
     * @param stamps            Número de timbres asignados al usuario.
     * @param phone             Número telefónico del usuario.
     * @param unlimited         Indica si el usuario tiene acceso ilimitado.
     * @param notificationEmail Correo electrónico para notificaciones.
     * @return IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse CrearUsuario(String email, String passwordUser, String name, String rfc, int stamps,
            String phone, boolean unlimited, String notificationEmail)
            throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.crearUsuarioRequest(getToken(), getURIAPI(),
                email, passwordUser, name, rfc, stamps, phone, unlimited, notificationEmail, getProxyHost(),
                getProxyPort());
        return AccountUserRequest.createCreateUserRequest(settings);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param idUser            Identificador único del usuario a actualizar.
     * @param name              Nuevo nombre del usuario.
     * @param rfc               Nuevo RFC del usuario.
     * @param unlimited         Indica si el usuario tiene acceso ilimitado.
     * @param phone             Nuevo número telefónico.
     * @param notificationEmail Nuevo correo para notificaciones.
     * @return IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ActualizarUsuario(UUID idUser, String name, String rfc, boolean unlimited, String phone,
            String notificationEmail)
            throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.actualizarUsuarioRequest(getToken(), getURIAPI(),
                idUser, name, rfc, unlimited, phone, notificationEmail, getProxyHost(), getProxyPort());
        return AccountUserRequest.createUpdateUserRequest(settings, idUser);
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
        return AccountUserRequest.createDeleteUserRequest(settings, idUser);
    }

    /**
     * Obtiene la lista de usuarios existentes.
     *
     * @return IResponse con la lista de usuarios.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuariosHijo() throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuariosRequest(getToken(), getURIAPI(),
                getProxyHost(), getProxyPort());
        return AccountUserRequest.createGetAllUsersRequest(settings);
    }

    /**
     * Obtiene información de un usuario específico por su ID.
     *
     * @param idUser Identificador único del usuario.
     * @return IResponse con la información del usuario.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuarioPorId(UUID idUser) throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuarioPorTokenRequest(getToken(),
                getURIAPI(), getProxyHost(), getProxyPort());
        return AccountUserRequest.createGetUserById(settings, idUser);
    }

    /**
     * Obtiene un usuario por su RFC.
     *
     * @param rfc RFC del usuario.
     * @return IResponse con la información del usuario.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuarioPorRfc(String rfc) throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuarioPorTokenRequest(getToken(),
                getURIAPI(), getProxyHost(), getProxyPort());
        return AccountUserRequest.createGetUserByRfc(settings, rfc);
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return IResponse con la información del usuario.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuarioPorEmail(String email) throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuarioPorTokenRequest(getToken(),
                getURIAPI(), getProxyHost(), getProxyPort());
        return AccountUserRequest.createGetUserByEmail(settings, email);
    }

    /**
     * Obtiene los usuarios activos.
     *
     * @param isActive Indica si se buscan usuarios activos o inactivos.
     * @return IResponse con la lista de usuarios.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse ObtenerUsuariosActivos(Boolean isActive) throws AuthException, GeneralException, IOException {
        AccountUserOptionsRequest settings = AccountUserOptionsRequest.obtenerUsuarioPorTokenRequest(getToken(),
                getURIAPI(), getProxyHost(), getProxyPort());
        return AccountUserRequest.createGetUserByActive(settings, isActive);
    }
}
