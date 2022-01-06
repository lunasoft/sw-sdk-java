package Services.Cancelation;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Requests.Cancelation.CancelationRequest;
import Utils.Responses.IResponse;



public class SWCancelationService extends SWService {
    
    public SWCancelationService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWCancelationService(String token, String URI) {
        super(token, URI);
    }
    
    public SWCancelationService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWCancelationService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public IResponse Cancelation(String uuid, String password_csd, String rfc, String b64Cer, String b64Key, String motivo, String foliosustitucion) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse Cancelation(String xml) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), xml, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestxml(settings, true);
    }
    
    public IResponse Cancelation(String uuid, String password_csd, String rfc, String b64Pfx, String motivo, String foliosustitucion) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), uuid, password_csd, rfc, b64Pfx, motivo, foliosustitucion, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestPfx(settings);
    }
    
    public IResponse Cancelationuuid(String uuid, String rfc, String motivo, String foliosustitucion) throws AuthException, GeneralException, IOException {
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(), getURI(), uuid, rfc, motivo, foliosustitucion, getProxyHost(), getProxyPort());
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestUuid(settings);
    }
}