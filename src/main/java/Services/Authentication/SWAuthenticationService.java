package Services.Authentication;

import java.io.IOException;

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


    public IResponse Token() throws GeneralException, AuthException, IOException {
        AuthOptionsRequest settings = new AuthOptionsRequest(getURI(),getUser(),getPassword());


        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
