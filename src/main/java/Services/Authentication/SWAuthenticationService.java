package Services.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.IResponse;

public class SWAuthenticationService extends SWService {
    public SWAuthenticationService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWAuthenticationService(String user, String password, String URI, String hostProxy, String portProxy) {
        super(user, password, hostProxy, portProxy, URI);
    }


    public IResponse Token() throws GeneralException, AuthException {
        AuthOptionsRequest settings;
        if(getProxyHost() != null){

            settings = new AuthOptionsRequest(getURI(),getUser(),getPassword(),getProxyHost(),getPortHost());
        }else{
            settings = new AuthOptionsRequest(getURI(),getUser(),getPassword());
        }



        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
