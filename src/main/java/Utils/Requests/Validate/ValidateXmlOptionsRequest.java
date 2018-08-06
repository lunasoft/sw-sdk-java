package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateXmlOptionsRequest extends IRequest {
	private String xml;
	
	public ValidateXmlOptionsRequest(String token, String URI, String Xml) {
        super(token, URI+ Constants.VALIDATE_XML_PATH);
        this.xml = Xml;
    }
	
	public String getXml() {
        return xml;
    }
}
