package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateLrfcOptionsRequest extends IRequest {
	
	public ValidateLrfcOptionsRequest(String token, String URI, String Lrfc, String proxyHost, int proxyPort) {
        super(token, URI + Constants.VALIDATE_LRFC_PATH + Lrfc, proxyHost, proxyPort);
    }
}