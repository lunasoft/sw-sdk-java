package Utils.Requests.Pdf;

import Utils.Constants;
import Utils.Requests.IRequest;

public class PdfOptionsRequest extends IRequest {
	private String xml;
	private String templateId;
	private String extras;
	
	public PdfOptionsRequest(String token, String URI, String xml) {
        super(token, URI+ Constants.GENERATE_PDF_PATH);
        this.xml = xml;
    }
	public PdfOptionsRequest(String token, String URI, String xml, String extras) {
        super(token, URI+ Constants.GENERATE_PDF_PATH);
        this.xml = xml;
        this.extras = extras;
    }
	public PdfOptionsRequest(String token, String URI, String xml, String extras, String templateId) {
        super(token, URI+ Constants.GENERATE_PDF_PATH);
        this.xml = xml;
        this.extras = extras;
        this.templateId = templateId;
    }
	
	public String getTemplateId() {
		return templateId;
	}
	
	public String getExtras() {
		return extras;
	}
	
	public String getXml() {
		return xml;
	}
}