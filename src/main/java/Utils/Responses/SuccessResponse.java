package Utils.Responses;

public class SuccessResponse extends IResponse implements IJSend {
    public SuccessResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status);
    }

    @Override
    public String show() {
        return this.Data;
    }
}
