package Utils.Requests.Authentication;

import Utils.Constants;
import Utils.Requests.IRequest;

public class AuthOptionsRequest extends IRequest {


    public AuthOptionsRequest(String URI, String user, String password) {
        super(URI+ Constants.AUTH_PATH, user, password);
    }
}
