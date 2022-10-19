package Utils.Requests.Pdf;

import java.util.Map;

import Utils.Constants;
import Utils.Helpers.PdfTemplates;
import Utils.Requests.IRequest;

public class PdfOptionsRequest extends IRequest {
	private String xml;
	private String templateId;
	private String b64Logo;
	private Map<String,String> extras;
	private String uuid;
	
	public PdfOptionsRequest(String token, String URI, String xml, String b64Logo, String proxyHost, int proxyPort) {
        super(token, URI + Constants.GENERATE_PDF_PATH, proxyHost, proxyPort);
        this.xml = xml;
		this.b64Logo = b64Logo;
		this.templateId = PdfTemplates.cfdi40.toString();
    }
	public PdfOptionsRequest(String token, String URI, String xml, String b64Logo, Map<String,String> extras, String proxyHost, int proxyPort) {
        super(token, URI + Constants.GENERATE_PDF_PATH, proxyHost, proxyPort);
        this.xml = xml;
		this.b64Logo = b64Logo;
        this.extras = extras;
		this.templateId = PdfTemplates.cfdi40.toString();
    }
	public PdfOptionsRequest(String token, String URI, String xml, String templateId, String b64Logo, Map<String,String> extras, String proxyHost, int proxyPort) {
        super(token, URI + Constants.GENERATE_PDF_PATH, proxyHost, proxyPort);
        this.xml = xml;
		this.b64Logo = b64Logo;
        this.extras = extras;
        this.templateId = templateId;
    }
	public PdfOptionsRequest(String token, String uuid, String URI, String proxyHost, int proxyPort)
	{
		super(token, URI + Constants.REGENERATE_PDF, proxyHost, proxyPort);
		this.uuid = uuid;
	}
	
	public String getTemplateId() {
		return templateId;
	}
	public Map<String,String> getExtras() {
		return extras;
	}
	public String getB64Logo() {
		return b64Logo;
	}	
	public String getXml() {
		return xml;
	}
	public String getUuid(){
		return uuid;
	}
}