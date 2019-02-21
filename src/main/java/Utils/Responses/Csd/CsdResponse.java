package Utils.Responses.Csd;

import Utils.Responses.IResponse;

public class CsdResponse extends IResponse {
    
    public String data;
    
    public CsdResponse(int httpStatusCode, String status, String data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }
    public CsdResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}