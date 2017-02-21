package Utils.Responses;

import Exceptions.GenaralException;

public class JSendFactory {
    public static IJSend response(String status,String msg, int code) throws GenaralException {

        int idcae = 0;
        if (status.equalsIgnoreCase("success")){
            idcae = 1;
        }
        else if(status.equalsIgnoreCase("fail")){
            idcae = 2;
        }
        else if (status.equalsIgnoreCase("error")){
            idcae = 3;
        }
        switch (idcae){
            case 1:
                return new SuccessResponse(code, msg, status);

            case 2:
                return new FailureResponse(code, msg, status);
            case 3:
                throw new GenaralException(code,msg);

            default:
                return new FailureResponse(code, msg, status);

        }
    }
}
