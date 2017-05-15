package Utils.Requests.Cancelation;

import Utils.Requests.IRequest;

 class CancelationOptionsRequest extends IRequest {

    public CancelationOptionsRequest(String URI, String user, String password) {
        super(URI, user, password);
    }

    public CancelationOptionsRequest(String token, String URI) {
        super(token, URI);
    }
}
