package Utils.Responses.Stamp;

import Utils.Responses.IResponse;

public class SuccessV1Response extends IResponse {
    public String tfd;
    public SuccessV1Response(int httpStatusCode, String status, String _tfd, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.tfd = _tfd;
    }
}
