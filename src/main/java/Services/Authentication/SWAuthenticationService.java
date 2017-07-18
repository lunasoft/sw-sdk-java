package Services.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Authentication.AuthRequestDummy;
import Utils.Requests.Authentication.AuthSoapRequest;
import Utils.Responses.IResponse;

public class SWAuthenticationService extends SWService {
    public SWAuthenticationService(String user, String password, String URI) {
        super(user, password, URI);
    }


    public IResponse Token() throws GeneralException, AuthException {
        AuthOptionsRequest settings = new AuthOptionsRequest(getURI(),getUser(),getPassword());


        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
