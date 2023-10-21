package Services.Validate;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Validate.ValidateXmlOptionsRequest;
import Utils.Requests.Validate.ValidateXmlRequest;
import Utils.Responses.IResponse;

public class SWValidateService extends SWService {

	public SWValidateService(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWValidateService(String token, String URI) {
		super(token, URI);
	}
	
	public SWValidateService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWValidateService(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	public IResponse ValidateXml(String xml) throws AuthException, GeneralException, IOException {
		ValidateXmlOptionsRequest settings = new ValidateXmlOptionsRequest(getToken(), getURI(), xml, getProxyHost(), getProxyPort());
		ValidateXmlRequest req = new ValidateXmlRequest();
		return req.sendRequest(settings);
	}

}
