package Services.Validate;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Validate.ValidateLcoOptionsRequest;
import Utils.Requests.Validate.ValidateLcoRequest;
import Utils.Requests.Validate.ValidateLrfcOptionsRequest;
import Utils.Requests.Validate.ValidateLrfcRequest;
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

	public IResponse ValidateXml(String xml) throws AuthException, GeneralException, IOException {
		ValidateXmlOptionsRequest settings = new ValidateXmlOptionsRequest(getToken(), getURI(), xml);
		ValidateXmlRequest req = new ValidateXmlRequest();
		return req.sendRequest(settings);
	}

	public IResponse ValidateLrfc(String Lrfc) throws AuthException, GeneralException, IOException {
		ValidateLrfcOptionsRequest settings = new ValidateLrfcOptionsRequest(getToken(), getURI(), Lrfc);
		ValidateLrfcRequest req = new ValidateLrfcRequest();
		return req.sendRequest(settings);
	}

	public IResponse ValidateLco(String Lco) throws AuthException, GeneralException, IOException {
		ValidateLcoOptionsRequest settings = new ValidateLcoOptionsRequest(getToken(), getURI(), Lco);
		ValidateLcoRequest req = new ValidateLcoRequest();
		return req.sendRequest(settings);
	}
}
