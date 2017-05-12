package Utils.Responses;

/**
 * Created by asalvio on 08/05/2017.
 */
public class SuccessCFDIResponse extends IResponse {
    public SuccessCFDIResponse(int httpStatusCode, String data, String status, String tfd, String cfdi) {
        super(httpStatusCode, data, status, tfd, cfdi);
    }
}
