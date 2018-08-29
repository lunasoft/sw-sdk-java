package Utils.Requests.Validate;

import Utils.Constants;
import Utils.Requests.IRequest;

public class ValidateLcoOptionsRequest extends IRequest {
	private String lco;
	
	public ValidateLcoOptionsRequest(String token, String URI, String Lco) {
        super(token, URI+ Constants.VALIDATE_LCO_PATH+Lco);
        this.lco = Lco;
    }
}