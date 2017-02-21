package Utils.Responses;

public class AuthResponse extends IResponse implements IJSend {
    public AuthResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status);
    }

    @Override
    public String show() {
        return this.Data;
    }
}
