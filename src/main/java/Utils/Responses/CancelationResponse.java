package Utils.Responses;

/**
 * Created by asalvio on 10/02/2017.
 */
public class CancelationResponse extends IResponse {
    public CancelationResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status);
    }
}
