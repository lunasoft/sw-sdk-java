package Utils.Requests.Csd;

import Utils.Constants;
import Utils.Requests.IRequest;

public class CsdOptionsRequest extends IRequest {
	private String b64Key;
	private String b64Cer;
	private String passwordCsd;
	private String certificateType;
	private boolean isActive;
	
	public CsdOptionsRequest(String token, String URI, String b64Cer, String b64Key, String passwordCsd, String certificateType, boolean isActive, String proxyHost, int proxyPort) {
		super(token, URI+ Constants.SAVE_CSD, proxyHost, proxyPort);
        this.b64Cer = b64Cer;
        this.b64Key = b64Key;
        this.passwordCsd = passwordCsd;
        this.certificateType = certificateType;
        this.isActive = isActive;
	}
	
	public CsdOptionsRequest(String token, String URI, String CertificateNumber, String proxyHost, int proxyPort) {
		super(token, URI + Constants.DISABLE_SEARCH_CSD + CertificateNumber, proxyHost, proxyPort);
	}
	public CsdOptionsRequest(String token, String URI, String CertificateNumber, String type, String proxyHost, int proxyPort) {
		super(token, URI + Constants.DISABLE_SEARCH_CSD + CertificateNumber + "/" + type, proxyHost, proxyPort);
	}
	
	public CsdOptionsRequest(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI + Constants.LIST_CSD, proxyHost, proxyPort);
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
