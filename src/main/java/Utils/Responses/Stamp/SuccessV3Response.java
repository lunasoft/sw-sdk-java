package Utils.Responses.Stamp;

import Utils.Responses.IResponse;

public class SuccessV3Response extends IResponse {
    public String cfdi;
    public SuccessV3Response(int httpStatusCode, String status, String _cfdi, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.cfdi = _cfdi;
    }
}
