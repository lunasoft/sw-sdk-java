package Utils.Requests.Csd;

import Utils.Constants;
import Utils.Requests.IRequest;

public class CsdOptionsRequest extends IRequest {
	private String b64Key;
	private String b64Cer;
	private String passwordCsd;
	private String certificateType;
	private boolean isActive;
	private String proxyHost;
	private String proxyPort;
	
	public CsdOptionsRequest(String token, String URI, String b64Cer, String b64Key, String passwordCsd, String certificateType, boolean isActive, String proxyHost, String proxyPort) {
		super(token, URI+ Constants.SAVE_CSD);
        this.b64Cer = b64Cer;
        this.b64Key = b64Key;
        this.passwordCsd = passwordCsd;
        this.certificateType = certificateType;
        this.isActive = isActive;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
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
    
    public String getProxyHost() {
    	return proxyHost;
    }
    
    public String getProxyPort() {
    	return proxyPort;
    }
}
