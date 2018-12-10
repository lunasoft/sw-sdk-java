package Services.Pdf;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Pdf.PdfOptionsRequest;
import Utils.Requests.Pdf.PdfRequest;
import Utils.Responses.IResponse;


public class SWPdfService extends SWService {
    
    public SWPdfService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWPdfService(String token, String URI) {
        super(token, URI);
    }
    
    public IResponse GeneratePdf(String xml) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(),getURI(), xml);
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse GeneratePdf(String xml, String extras) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(),getURI(), xml, extras);
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse GeneratePdf(String xml, String extras, String templateId) throws AuthException, GeneralException, IOException {
        PdfOptionsRequest settings = new PdfOptionsRequest(getToken(),getURI(), xml, extras, templateId);
        PdfRequest req = new PdfRequest();
        return req.sendRequest(settings);
    }
}