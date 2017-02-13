package Utils.Responses;

/**
 * Created by asalvio on 13/02/2017.
 */
public class FailureResponse extends IResponse implements IJSend {
    public FailureResponse(int httpStatusCode, String msg) {
        super(httpStatusCode, "Error de validaciones "+msg);
    }

    @Override
    public String show() {
        return this.Msg;
    }
}
