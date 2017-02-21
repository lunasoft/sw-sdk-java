package Utils.Responses;

/**
 * Created by asalvio on 08/02/2017.
 */
public abstract class IResponse {
    public int HttpStatusCode;
    public String Data;
    public String Status;

    public IResponse(int httpStatusCode, String msg, String status) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        Data = msg;
    }



}
