package Utils.Responses;

/**
 * Created by asalvio on 08/02/2017.
 */
public abstract class IResponse {
    public int HttpStatusCode;
    public String Msg;

    public IResponse(int httpStatusCode, String msg) {
        HttpStatusCode = httpStatusCode;
        Msg = msg;
    }


}
