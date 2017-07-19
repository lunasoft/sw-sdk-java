package Services.Cancelation;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Requests.Cancelation.CancelationRequest;
import Utils.Responses.IResponse;



public class SWCancelationService extends SWService {
    
    public SWCancelationService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWCancelationService(String token, String URI) {
        super(token, URI);
    }
    
    
    public IResponse Cancelation(String uuid, String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException {

        if (getToken()==null){
            generateToken();
        }
        
        //MAKE CANCELATION PROCESS, CUSTOMER ALREADY HAS TOKEN

        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),uuid, password_csd, rfc, b64Cer, b64Key);

        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings);

    }
    
    public IResponse Cancelation(String xml) throws AuthException, GeneralException {

        if (getToken()==null){
            generateToken();
        }
        
        //MAKE CANCELATION PROCESS, CUSTOMER ALREADY HAS TOKEN

        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),xml);

        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings, true);

    }
    
}
