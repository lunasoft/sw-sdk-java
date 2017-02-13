package Utils.Responses;

/**
 * Created by asalvio on 13/02/2017.
 */
public class SuccessResponse extends IResponse implements IJSend {
    public SuccessResponse(int httpStatusCode, String msg) {
        super(httpStatusCode, msg);
    }

    @Override
    public String show() {
        return this.Msg;
    }
}
