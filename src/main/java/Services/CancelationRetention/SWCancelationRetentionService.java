package Services.CancelationRetention;

import java.io.IOException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Requests.Cancelation.CancelationRequest;
import Utils.Responses.IResponse;

/**
 * Servicio para implementaci�n de cancelaci�n de retenciones.
 */
public class SWCancelationRetentionService extends SWService {

    public SWCancelationRetentionService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWCancelationRetentionService(String token, String URI) {
        super(token, URI);
    }

    public SWCancelationRetentionService(String user, String password, String URI, String proxyHost, int proxyPort)
            throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWCancelationRetentionService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }

    /**
     * Realiza la cancelaci�n de retenciones utilizando el certificado CSD.
     *
     * @param uuid             uuid factura.
     * @param password         password de llave privada.
     * @param rfc              rfc emisor.
     * @param csd              String base64 del certificado.
     * @param key              String base64 de llave privada.
     * @param motivo           motivo de cancelacion.
     * @param folioSustitucion uuid factura que sustituye.
     * @throws AuthException    Si ocurre un error de autenticaci�n.
     * @throws GeneralException Si ocurre un error general en el proceso.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @return {@link IResponse} La respuesta del servicio de cancelaci�n.
     */
    public IResponse Cancelation(String uuid, String password, String rfc, String b64Cer, String b64Key, String motivo,
            String folioSustitucion) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), uuid, password, rfc,
                b64Cer, b64Key, motivo, folioSustitucion, true, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings);
    }

    /**
     * Realiza la cancelaci�n de retenciones mediante PFX.
     *
     * @param uuid             uuid factura.
     * @param password         password de llave privada.
     * @param rfc              rfc emisor.
     * @param b64Pfx           El archivo PFX del contribuyente codificado en
     *                         Base64.
     * @param motivo           motivo de cancelacion.
     * @param folioSustitucion uuid factura que sustituye.
     * @throws AuthException    Si ocurre un error de autenticaci�n.
     * @throws GeneralException Si ocurre un error general en el proceso.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @return {@link IResponse} La respuesta del servicio de cancelaci�n.
     */
    public IResponse Cancelation(String uuid, String password, String rfc, String b64Pfx, String motivo,
            String folioSustitucion) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), uuid, password, rfc,
                b64Pfx, motivo, folioSustitucion, true, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestPfx(settings);
    }

    /**
     * Realiza la cancelaci�n de retenciones mediante XML.
     *
     * @param xml String XML de cancelaci�n de retenciones.
     * @throws AuthException    Si ocurre un error de autenticaci�n.
     * @throws GeneralException Si ocurre un error general en el proceso.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @return {@link IResponse} respuesta del servicio de cancelaci�n.
     */
    public IResponse Cancelation(String xml) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), xml, true, getProxyHost(),
                getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestXml(settings, true);
    }
}