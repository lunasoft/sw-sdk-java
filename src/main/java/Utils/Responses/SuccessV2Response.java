package Utils.Responses;

/**
 * Created by asalvio on 19/07/2017.
 */
public class SuccessV2Response extends IResponse {
    public String tfd;
    public String cfdi;
    public SuccessV2Response(int httpStatusCode, String status,String _tfd, String _cfdi, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.tfd = _tfd;
        this.cfdi = _cfdi;
    }
}
