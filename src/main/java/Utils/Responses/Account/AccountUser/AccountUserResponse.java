package Utils.Responses.Account.AccountUser;

import Utils.Responses.IResponse;

public class AccountUserResponse extends IResponse{
    public String data;
    
    public AccountUserResponse(int httpStatusCode, String status, String data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }
    public AccountUserResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}
