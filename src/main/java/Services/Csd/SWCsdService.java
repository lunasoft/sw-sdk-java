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
    
    public IResponse CargarCsd(String b64Cer, String b64Key, String passwordCsd, String certificateType, boolean isActive) throws AuthException, GeneralException, IOException {
        CsdOptionsRequest settings = new CsdOptionsRequest(getToken(), getURI(), b64Cer, b64Key, passwordCsd, certificateType, isActive);
        CsdRequest req = new CsdRequest();
        return req.sendRequest(settings);
    }
}

