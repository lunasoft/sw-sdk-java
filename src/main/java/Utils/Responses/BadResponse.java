package Utils.Responses;

/**
 * Created by asalvio on 08/05/2017.
 */
public class BadResponse extends IResponse {

    public BadResponse(int httpStatusCode, String status, String message, String messageDetail) {
        super(httpStatusCode, status, message, messageDetail);
    }
}
