package Services.Resend;

import java.io.IOException;
import java.util.UUID;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Resend.ResendOptionsRequest;
import Utils.Requests.Resend.ResendRequest;
import Utils.Responses.IResponse;
/*
 * SWResendService - Una clase de servicio para manejar el reenvío de correos electrónicos con archivos PDF/XML utilizando la API de SW.
 * Extiende SWService para heredar funcionalidades de autenticación y URL base de la API.
 */

public class SWResendService extends SWService {

    
    /**
     * Constructor para inicializar el servicio con credenciales de usuario, URL base, URL de la API y configuraciones de proxy.
     * Lanza AuthException si la autenticación falla.
     *
     * @param user      El nombre de usuario para la autenticación.
     * @param password  La contraseña asociada al nombre de usuario.
     * @param URI       La URL base del servicio.
     * @param URIAPI    La URL de la API específica para el reenvío.
     * @param proxyHost El host del proxy, si es necesario.
     * @param proxyPort El puerto del proxy, si es necesario.
     * @throws AuthException Si la autenticación falla.
     */
    public SWResendService(String user, String password, String URI, String URIAPI, String proxyHost,
            int proxyPort) throws AuthException {
        super(user, password, URI, URIAPI, proxyHost, proxyPort);
    }

     /**
     * Constructor para inicializar el servicio con un token existente, URL de la API y configuraciones de proxy.
     *
     * @param token     El token de autenticación existente.
     * @param URIAPI    La URL de la API específica para el reenvío.
     * @param proxyHost El host del proxy, si es necesario.
     * @param proxyPort El puerto del proxy, si es necesario.
     */
    public SWResendService(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }
      /**
     * ResendEmail - Inicia el proceso de reenvío de un correo electrónico.
     *
     * @param uuid    El UUID asociado al correo electrónico que se va a reenviar.
     * @param correos La cadena de direcciones de correo electrónico separadas por comas.
     * @return Un objeto IResponse que representa el resultado de la operación de reenvío.
     * @throws AuthException    Si la autenticación falla.
     * @throws GeneralException Si ocurre un error general durante la operación.
     * @throws IOException      Si ocurre un error de entrada/salida durante la operación.
     */

    public IResponse ResendEmail(UUID uuid, String correos)
            throws AuthException, GeneralException, IOException {

        ResendOptionsRequest settings = ResendOptionsRequest.resendEmail(getToken(), getURIAPI(),
                uuid, correos, getProxyHost(), getProxyPort());
        return ResendRequest.createResendRequest(settings, uuid, correos);
    }
}
