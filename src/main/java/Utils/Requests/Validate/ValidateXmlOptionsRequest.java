package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateXmlOptionsRequest extends IRequest {
	private String xml;
	
	public ValidateXmlOptionsRequest(String token, String URI, String Xml, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.VALIDATE_XML_PATH, proxyHost, proxyPort);
        this.xml = Xml;
    }
	
	public String getXml() {
        return xml;
    }
}
