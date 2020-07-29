package Services.AcceptReject;

import java.io.IOException;
import java.util.Map;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.AcceptReject.AceptarRechazarCancelationRequest;
import Utils.Requests.AcceptReject.AceptarRechazarOptionsRequest;
import Utils.Responses.IResponse;

public class SWAcceptRejectService extends SWService{
    public SWAcceptRejectService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWAcceptRejectService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public SWAcceptRejectService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWAcceptRejectService(String token, String URI) {
        super(token, URI);
    }
    
    public IResponse AceptarRechazarCancelacionCSD(Map<String,String> uuids,String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException, IOException {
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuids, password_csd, rfc, b64Cer, b64Key, getProxyHost(), getProxyPort());
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse AceptarRechazarCancelacionPFX(Map<String,String> uuids,String password_csd, String rfc, String b64pfx) throws AuthException, GeneralException, IOException {
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuids, password_csd, rfc, b64pfx, getProxyHost(), getProxyPort());
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequestPFX(settings);
    }
    
    public IResponse AceptarRechazarCancelacionXML(String xml) throws AuthException, GeneralException, IOException {
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), xml, getProxyHost(), getProxyPort());
        AceptarRechazarCancelationRequest req = new AceptarRechazarCancelationRequest();
        return req.sendRequestXML(settings);
    }
    
    public IResponse AceptarRechazarCancelacionUUID(String uuid, String rfc, String action) throws AuthException, GeneralException, IOException {
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuid, rfc, action, getProxyHost(), getProxyPort());
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequestUUID(settings);
    }
}
