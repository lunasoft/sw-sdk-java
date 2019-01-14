package Services.Csd;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Csd.CsdOptionsRequest;
import Utils.Requests.Csd.CsdRequest;
import Utils.Responses.IResponse;

public class SWCsdService extends SWService {
    
    public SWCsdService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWCsdService(String token, String URI) {
        super(token, URI);
    }
    public SWCsdService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWCsdService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public IResponse UploadMyCsd(String b64Cer, String b64Key, String passwordCsd, String certificateType, boolean isActive) throws AuthException, GeneralException, IOException {
    	CsdOptionsRequest settings = null;
    	settings = new CsdOptionsRequest(getToken(), getURI(), b64Cer, b64Key, passwordCsd, certificateType, isActive, getProxyHost(), getProxyPort());
        CsdRequest req = new CsdRequest();
        return req.sendRequest(settings);
    }
}

