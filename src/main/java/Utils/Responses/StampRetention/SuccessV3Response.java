package Utils.Responses.StampRetention;
import Utils.Responses.IResponse;

public class SuccessV3Response extends IResponse{
    public String retencion;
    public SuccessV3Response(int httpStatusCode, String status, String _retencion, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.retencion = _retencion;
    }
}
