package Utils.Responses.Stamp;

import Utils.Responses.IResponse;

public class SuccessV2Response extends IResponse {
    public String tfd;
    public String cfdi;
    public SuccessV2Response(int httpStatusCode, String status,String _tfd, String _cfdi, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.tfd = _tfd;
        this.cfdi = _cfdi;
    }
}
