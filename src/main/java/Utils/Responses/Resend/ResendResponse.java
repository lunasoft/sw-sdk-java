package Utils.Responses.Resend;

import Utils.Responses.IResponse;

public class ResendResponse extends IResponse {

    public String data;

    public ResendResponse(int httpStatusCode, String status, String data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }

    public ResendResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }

    public String getData() {
        return this.data;
    }
}