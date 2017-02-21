package Utils.Responses;

/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthResponse extends IResponse implements IJSend {
    public AuthResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status);
    }

    @Override
    public String show() {
        return this.Data;
    }
}
