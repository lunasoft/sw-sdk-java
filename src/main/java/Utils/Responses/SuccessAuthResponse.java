package Utils.Responses;

/**
 * Created by asalvio on 08/05/2017.
 */
public class SuccessAuthResponse extends IResponse {
    public SuccessAuthResponse(int httpStatusCode, String status, String token, boolean isAuth) {
        super(httpStatusCode, status, token, isAuth);
    }
}
