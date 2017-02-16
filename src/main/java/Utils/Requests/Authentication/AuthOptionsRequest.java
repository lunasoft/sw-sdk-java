package Utils.Requests.Authentication;

import Utils.Constants;
import Utils.Requests.IRequest;

/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthOptionsRequest extends IRequest {


    public AuthOptionsRequest(String URI, String user, String password) {
        super(URI+ Constants.AUTH_PATH, user, password);
    }
}
