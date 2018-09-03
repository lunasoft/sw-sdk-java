package Utils.Responses.AcceptReject;

import java.util.List;

import Utils.Responses.IResponse;

public class AceptarRechazarCancelationResponse extends IResponse {
    
    public String acuse;
    public List<CancelationData> folios;
    
    public AceptarRechazarCancelationResponse(int httpStatusCode, String status, String acuse, List<CancelationData> folios, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.acuse = acuse;
        this.folios = folios;
    }
    public AceptarRechazarCancelationResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}
