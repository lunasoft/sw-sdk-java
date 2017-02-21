package Utils.Responses;

public class FailureResponse extends IResponse implements IJSend {
    public FailureResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, "Error de validaciones "+msg, status);
    }

    @Override
    public String show() {
        return this.Data;
    }
}
