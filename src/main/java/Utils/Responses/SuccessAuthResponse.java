package Utils.Responses;

/**
 * Created by asalvio on 08/05/2017.
 */
public class SuccessAuthResponse extends IResponse {
    public String token;
    public SuccessAuthResponse(int httpStatusCode, String status, String _token, String Expires_in,String msg, String msgDetail) {

        super(httpStatusCode, status, msg,msgDetail);
        this.token = _token;
    }
}
