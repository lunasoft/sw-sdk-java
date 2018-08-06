package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateLrfcOptionsRequest extends IRequest {
	private String lrfc;
	
	public ValidateLrfcOptionsRequest(String token, String URI, String Lrfc) {
        super(token, URI+ Constants.VALIDATE_LRFC_PATH+Lrfc);
        this.lrfc = Lrfc;
    }
}