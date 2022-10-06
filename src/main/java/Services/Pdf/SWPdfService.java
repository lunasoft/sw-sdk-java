package Services.Pdf;

import java.io.IOException;
import java.util.Map;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Helpers.PdfTemplates;
import Utils.Helpers.RequestHelper;
import Utils.Requests.Pdf.PdfOptionsRequest;
import Utils.Requests.Pdf.PdfRequest;
import Utils.Responses.IResponse;


public class SWPdfService extends SWService {
    /**
     * Crear una instancia de la clase SWPdfService.
     * @param user Email del usuario.
     * @param password Contrasena del usuario.
     * @param urlApi Url API.
     * @param url Url Services.
     * @throws AuthException
     */
    public SWPdfService(String user, String password, String urlApi, String url) throws AuthException {
        super(user, password, url, urlApi);
    }
    /**
     * Crear una instancia de la clase SWPdfService.
     * @param token Token de autenticacion.
     * @param urlApi Url API.
     */
    public SWPdfService(String token, String urlApi) {
        super(token, urlApi);
    }
    /**
     * Crear una instancia de la clase SWPdfService.
     * @param user Email del usuario.
     * @param password Contrasena del usuario.
     * @param urlApi Url API.
     * @param url Url Services.
     * @param proxyHost Proxy.
     * @param proxyPort Puerto Proxy.
     * @throws AuthException
     */
    public SWPdfService(String user, String password, String urlApi, String url, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, url, urlApi, proxyHost, proxyPort);
    }
    /**
     * Crear una instancia de la clase SWPdfService.
     * @param token Token de autenticacion.
     * @param urlApi Url API.
     * @param proxyHost Proxy.
     * @param proxyPort Puerto Proxy.
     */
    public SWPdfService(String token, String urlApi, String proxyHost, int proxyPort) {
        super(token, urlApi, proxyHost, proxyPort);
    }
    /**
     * Servicio para generar PDF con plantilla por defecto CFDI 4.0
     * @param xml String CFDI formato XML.
     * @param b64Logo Logo en B64.
     * @return {@link Utils.Responses.Pdf.PdfResponse} 
     * @throws AuthException
     * @throws GeneralException
     * @throws IOException
     */
    public IResponse GeneratePdf(String xml, String b64Logo) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(), RequestHelper.stringEmptyOrNull(getURIAPI()) ? getURI() : getURIAPI(), xml, b64Logo, getProxyHost(), getProxyPort());
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
    /**
     * Servicio para generar PDF con plantilla por defecto CFDI 4.0.
     * @param xml String CFDI formato XML.
     * @param b64Logo Logo en B64.
     * @param extras Especifica datos adicionales.
     * @return {@link Utils.Responses.Pdf.PdfResponse} 
     * @throws AuthException
     * @throws GeneralException
     * @throws IOException
     */
    public IResponse GeneratePdf(String xml, String b64Logo, Map<String, String> extras) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(), RequestHelper.stringEmptyOrNull(getURIAPI()) ? getURI() : getURIAPI(), xml, b64Logo, extras, getProxyHost(), getProxyPort());
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
    /**
     * Servicio para generar PDF con plantilla personalizada. 
     * @param xml String CFDI formato XML.
     * @param templateId Identificador de la plantilla.
     * @param b64Logo Logo en B64.
     * @param extras Especifica parametros extras.
     * @return {@link Utils.Responses.Pdf.PdfResponse} 
     * @throws AuthException
     * @throws GeneralException
     * @throws IOException
     */
    public IResponse GeneratePdf(String xml, String templateId, String b64Logo, Map<String, String> extras) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(), RequestHelper.stringEmptyOrNull(getURIAPI()) ? getURI() : getURIAPI(), xml, templateId, b64Logo, extras, getProxyHost(), getProxyPort());
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
    /**
     * Servicio para generar PDF con plantilla generica.
     * @param xml String CFDI formato XML.
     * @param templateId Identificador de la plantilla.
     * @param b64Logo Logo en B64.
     * @param extras Especifica parametros extras.
     * @return {@link Utils.Responses.Pdf.PdfResponse} 
     * @throws AuthException
     * @throws GeneralException
     * @throws IOException
     */
    public IResponse GeneratePdf(String xml, PdfTemplates templateId, String b64Logo, Map<String,String> extras) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(), RequestHelper.stringEmptyOrNull(getURIAPI()) ? getURI() : getURIAPI(), xml, templateId.toString(), b64Logo, extras, getProxyHost(), getProxyPort());
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
}