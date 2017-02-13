package Utils.Requests.Cancelation;

import Utils.Requests.IRequest;

/**
 * Created by asalvio on 10/02/2017.
 */
public class CancelationOptionsRequest extends IRequest {

    public CancelationOptionsRequest(String URI, String user, String password) {
        super(URI, user, password);
    }

    public CancelationOptionsRequest(String token, String URI) {
        super(token, URI);
    }
}
