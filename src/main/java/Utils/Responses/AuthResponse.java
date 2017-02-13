package Utils.Responses;

/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthResponse extends IResponse implements IJSend {
    public AuthResponse(int httpStatusCode, String msg) {
        super(httpStatusCode, msg);
    }

    @Override
    public String show() {
        return this.Msg;
    }
}
