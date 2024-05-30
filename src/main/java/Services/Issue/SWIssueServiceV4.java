
package Services.Issue;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Issue.IssueOptionsRequest;
import Utils.Requests.Issue.IssueRequest;
import Utils.Responses.IResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class SWIssueServiceV4 extends SWService {

    public SWIssueServiceV4(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWIssueServiceV4(String token, String URI) {
        super(token, URI);
    }

    public SWIssueServiceV4(String user, String password, String URI, String proxyHost, int proxyPort)
            throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWIssueServiceV4(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }

    /**
     * Servicio para emitir un CFDI en formato JSON.
     *
     * Se puede especificar un Custom Id y realizar el envío del CFDI 
     * y PDF por correo, así como guardar el PDF en el ADT.
     *
     * @param json     String del CFDI en formato JSON.
     * @param version  Versión del servicio.
     * @param emails   Arreglo de correos para el envío del CFDI y PDF.
     * @param customId Identificador único asignado al comprobante.
     * @param isPdf    Opción para habilitar la generación y guardado del PDF en el
     *                 ADT.
     * @return                  Respuesta del servicio.
     * @throws AuthException    Excepción de autenticación.
     * @throws GeneralException Excepción general.
     * @throws IOException      Excepción de entrada/salida.
     */
    public IResponse IssueJson(String json, String version, String[] emails, String customId, boolean isPdf)
            throws AuthException, GeneralException, IOException {
        IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), json, version, false,
                getProxyHost(), getProxyPort(), emails, customId, isPdf);
        IssueRequest req = new IssueRequest();
        return req.sendRequest(settings);
    }

    /**
     * Servicio para emitir un CFDI en formato JSON a partir de un archivo byte[].
     *
     * Realiza el timbrado de un CFDI en formato JSON a partir de un archivo byte[].
     * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
     * correo, así como guardar el PDF en el ADT.
     *
     * @param jsonFile Arreglo de bytes que representa el archivo JSON del CFDI.
     * @param version  Versión del servicio.
     * @param emails   Arreglo de correos para el envío del CFDI y PDF.
     * @param customId Identificador único asignado al comprobante.
     * @param isPdf    Opción para habilitar la generación y guardado del PDF en el
     *                 ADT.
     * @return                  Respuesta del servicio.
     * @throws AuthException    Excepción de autenticación.
     * @throws GeneralException Excepción general.
     * @throws IOException      Excepción de entrada/salida.
     */
    public IResponse IssueJson(byte[] jsonFile, String version, String[] emails, String customId, boolean isPdf)
            throws AuthException, GeneralException, IOException {
        String jsonProcess = new String(jsonFile, Charset.forName("UTF-8"));
        IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), jsonProcess, version, false,
                getProxyHost(), getProxyPort(), emails, customId, isPdf);
        IssueRequest req = new IssueRequest();
        return req.sendRequest(settings);
    }

    /**
     * Servicio para emitir un CFDI en formato XML.
     *
     * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
     * correo, así como guardar el PDF en el ADT.
     *
     * @param xml      String del CFDI en formato XML.
     * @param version  Versión del servicio.
     * @param emails   Arreglo de correos para el envío del CFDI y PDF.
     * @param customId Identificador único asignado al comprobante.
     * @param isPdf    Opción para habilitar la generación y guardado del PDF en el
     *                 ADT.
     * @return                  Respuesta del servicio.
     * @throws AuthException    Excepción de autenticación.
     * @throws GeneralException Excepción general.
     * @throws IOException      Excepción de entrada/salida.
     */
    public IResponse IssueXml(String xml, String version, String[] emails, String customId, boolean isPdf)
            throws AuthException, GeneralException, IOException {
        IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xml, version, true, getProxyHost(),
                getProxyPort(), emails, customId, isPdf);
        IssueRequest req = new IssueRequest();
        return req.sendRequestXml(settings);
    }

    /**
     * Servicio para emitir un CFDI en formato XML a partir de un archivo byte[].
     *
     * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
     * correo, así como guardar el PDF en el ADT.
     *
     * @param xmlfile  Arreglo de bytes que representa el archivo XML del CFDI.
     * @param version  Versión del servicio.
     * @param emails   Arreglo de correos para el envío del CFDI y PDF.
     * @param customId Identificador único asignado al comprobante.
     * @param isPdf    Opción para habilitar la generación y guardado del PDF en el ADT.                
     * @return                  Respuesta del servicio.
     * @throws AuthException    Excepción de autenticación.
     * @throws GeneralException Excepción general.
     * @throws IOException      Excepción de entrada/salida.
     */
    public IResponse IssueXml(byte[] xmlfile, String version, String[] emails, String customId, boolean isPdf)
            throws AuthException, GeneralException, IOException {
        String xmlProcess = new String(xmlfile, Charset.forName("UTF-8"));
        IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xmlProcess, version, true,
                getProxyHost(), getProxyPort(), emails, customId, isPdf);
        IssueRequest req = new IssueRequest();
        return req.sendRequestXml(settings);
    }
}
