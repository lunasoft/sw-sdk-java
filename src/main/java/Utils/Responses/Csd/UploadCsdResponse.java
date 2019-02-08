package Utils.Responses.Csd;

import Utils.Responses.IResponse;

public class UploadCsdResponse extends IResponse {
    
    public String data;
    
    public UploadCsdResponse(int httpStatusCode, String status, String data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }
    public UploadCsdResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}