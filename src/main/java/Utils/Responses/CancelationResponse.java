package Utils.Responses;

public class CancelationResponse extends IResponse {
    
    public String acuse;
    public String uuid;
    public int uuidStatusCode;
    
    public CancelationResponse(int httpStatusCode, String status, String acuse, String uuid, int uuidStatusCode) {
        super(httpStatusCode, status);
        this.acuse = acuse;
        this.uuid = uuid;
        this.uuidStatusCode = uuidStatusCode;
    }
}
