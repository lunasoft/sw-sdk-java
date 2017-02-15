package Services.Authentication;

import Exceptions.GenaralException;
import Services.SWService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Authentication.AuthRequestDummy;
import Utils.Responses.IResponse;

/**
 * Created by asalvio on 08/02/2017.
 */
public class SWAuthenticationService extends SWService {
    public SWAuthenticationService(String user, String password, String URI) {
        super(user, password, URI);
    }


    public IResponse Token() throws GenaralException {
        AuthOptionsRequest settings = new AuthOptionsRequest(getURI(),getUser(),getPassword());

        String dum = settings.URI.split("-")[0];



        if (dum.equalsIgnoreCase("d")){
            AuthRequestDummy req = new AuthRequestDummy();
            return req.sendRequest(settings);
        }
        AuthRequest req = new AuthRequest();
        return req.sendRequest(settings);

    }
}
