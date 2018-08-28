package Services.Authentication;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.IResponse;
import java.text.ParseException;

public class SWAuthenticationService extends SWService {
    public SWAuthenticationService(String URI, String user, String password) throws ParseException {
        super( URI, user, password);
    }


    public IResponse Token() throws GeneralException, AuthException, IOException {
        AuthOptionsRequest settings = new AuthOptionsRequest(getURI(),getUser(),getPassword());

        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
