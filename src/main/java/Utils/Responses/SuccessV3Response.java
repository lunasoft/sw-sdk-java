package Utils.Responses;

/**
 * Created by asalvio on 19/07/2017.
 */
public class SuccessV3Response extends IResponse {
    public String cfdi;
    public SuccessV3Response(int httpStatusCode, String status, String _cfdi, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.cfdi = _cfdi;
    }
}
