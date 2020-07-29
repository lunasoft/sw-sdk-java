package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateLcoOptionsRequest extends IRequest {
	
	public ValidateLcoOptionsRequest(String token, String URI, String Lco, String proxyHost, int proxyPort) {
        super(token, URI + Constants.VALIDATE_LCO_PATH + Lco, proxyHost, proxyPort);
    }
}