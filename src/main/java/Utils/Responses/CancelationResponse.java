package Utils.Responses;

public class CancelationResponse extends IResponse {
    public CancelationResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status, 1);
    }
}
