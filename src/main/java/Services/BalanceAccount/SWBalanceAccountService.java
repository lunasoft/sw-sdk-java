/**
 * SWBalanceAccountService proporciona servicios para gestionar el saldo de cuentas.
 * Esta clase extiende SWService y ofrece métodos para obtener el saldo de una cuenta y para realizar un movimiento de saldo (agregar y remover).
 */
package Services.BalanceAccount;

import java.io.IOException;
import java.util.UUID;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.BalanceAccount.BalanceAcctOptionsRequest;
import Utils.Requests.BalanceAccount.BalanceAcctRequest;
import Utils.Helpers.EnumBalanceStamp.AccountBalanceAction;
import Utils.Responses.IResponse;

/**
 * SWBalanceAccountService proporciona servicios para gestionar el saldo de
 * cuentas.
 */
public class SWBalanceAccountService extends SWService {

    /**
     * Constructor que requiere credenciales de autenticación para el servicio.
     * @param user              Nombre de usuario para la autenticación.
     * @param password          Contraseña para la autenticación.
     * @param URI               URI base para la autenticación.
     * @throws AuthException    Si la autenticación falla.
     */
    public SWBalanceAccountService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    /**
     * Constructor que utiliza un token de autenticación para el servicio.
     * @param user              Nombre de usuario para la autenticación.
     * @param password          Contraseña para la autenticación.
     * @param URI               URI base para la autenticación.
     * @param URIAPI            URI de la API para el servicio.
     * @throws AuthException    Si la autenticación falla.
     */
    public SWBalanceAccountService(String user, String password, String URI, String URIAPI) throws AuthException {
        super(user, password, URI, URIAPI);
    }

    /**
     * Constructor que utiliza un token de autenticación para el servicio.
     * @param token             Token de autenticación.
     * @param URI               URI base para la autenticación.
     * @throws AuthException    Si la autenticación falla.
     */
    public SWBalanceAccountService(String token, String URI) throws AuthException {
        super(token, URI);
    }

    /**
     * Constructor que utiliza un token de autenticación para el servicio.
     * @param user             Nombre de usuario para la autenticación.
     * @param password         Contraseña para la autenticación.
     * @param URI              URI base para la autenticación.
     * @param proxyHost        Host del proxy (puede ser nulo).
     * @param proxyPort        Puerto del proxy.
     * @throws AuthException    Si la autenticación falla.
     */
    public SWBalanceAccountService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    /**
     * Constructor que utiliza un token de autenticación para el servicio.
     * @param token             Token de autenticación.
     * @param URI               URI base para la autenticación.
     * @param proxyHost         Host del proxy (puede ser nulo).
     * @param proxyPort         Puerto del proxy.
     * @throws AuthException    Si la autenticación falla.
     */
    public SWBalanceAccountService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }

    /**
     * Obtiene el saldo de la cuenta.
     * @return                  IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse GetBalanceAccount() throws AuthException, GeneralException, IOException {
        BalanceAcctOptionsRequest settings = BalanceAcctOptionsRequest.sendRequest(getToken(), getURI(), getProxyHost(), getProxyPort());
        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);
    }

    /**
     * Realiza un movimiento de saldo en la cuenta.
     * @param idUser            ID del usuario.
     * @param stamps            Número de timbres a agregar o remover.
     * @param comment           Comentario del movimiento.
     * @param action            Tipo de movimiento (add o remove).
     * @return                  IResponse con el resultado de la operación.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general.
     * @throws IOException      Si hay un error de entrada/salida.
     */
    public IResponse BalanceAccountStamp(UUID idUser, int stamps, String comment, AccountBalanceAction action) throws AuthException, GeneralException, IOException {
        BalanceAcctOptionsRequest settings = BalanceAcctOptionsRequest.BalanceAccountStamp(getToken(), getURIAPI() == null ? getURI() : getURIAPI(), idUser, stamps, comment, action, getProxyHost(), getProxyPort());
        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.BalanceStampRequest(settings);
    }
}
