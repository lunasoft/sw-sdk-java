package Utils.Responses.Authentication;

import Utils.Responses.IResponse;

public class SuccessAuthResponse extends IResponse {
    public String token;
    public long expires_in;
    public SuccessAuthResponse(int httpStatusCode, String status, String _token, int expires_in, String msg, String msgDetail) {

        super(httpStatusCode, status, msg,msgDetail);
        this.token = _token;
        this.expires_in = expires_in;
    }
}
