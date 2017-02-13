package Utils.Responses;

import Exceptions.GenaralException;

/**
 * Created by asalvio on 13/02/2017.
 */
public class JSendFactory {
    public static IJSend response(String status,String msg, int code) throws GenaralException {
        switch (status){
            case "success":
                return new SuccessResponse(code, msg);

            case "fail":
                return new FailureResponse(code, msg);
            case "error":
                throw new GenaralException(code,msg);

            default:
                return new FailureResponse(code, msg);

        }
    }
}
