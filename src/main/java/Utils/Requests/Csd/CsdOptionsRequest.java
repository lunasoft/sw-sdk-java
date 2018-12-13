package Utils.Requests.Csd;

import Utils.Constants;
import Utils.Requests.IRequest;

public class CsdOptionsRequest extends IRequest {
	String b64Key;
	String b64Cer;
	String passwordCsd;
	String certificateType;
	boolean isActive;
	public CsdOptionsRequest(String token, String URI, String b64Cer, String b64Key, String passwordCsd, String certificateType, boolean isActive) {
		super(token, URI+ Constants.SAVE_CSD);
        this.b64Cer = b64Cer;
        this.b64Key = b64Key;
        this.passwordCsd = passwordCsd;
        this.certificateType = certificateType;
        this.isActive = isActive;
	}
	
    public String getB64Cer() {
        return b64Cer;
    }

    public String getB64key() {
        return b64Key;
    }
    
    public String getPasswordCsd() {
    	return passwordCsd;
    }
    
    public String getCertificateType() {
    	return certificateType;
    }
    
    public boolean getIsActive() {
    	return isActive;
    }
}
