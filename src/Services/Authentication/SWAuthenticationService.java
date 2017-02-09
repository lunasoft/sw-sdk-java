package Services.Authentication;

import Services.SWService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.IResponse;

/**
 * Created by asalvio on 08/02/2017.
 */
public class SWAuthenticationService extends SWService {
    public SWAuthenticationService(String user, String password, String URI) {
        super(user, password, URI);
    }


    public IResponse getToken() {
        AuthOptionsRequest settings = new AuthOptionsRequest(URI,User,Password);
        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
