package Utils.Responses.Account.AccountUser;

import Utils.Responses.IResponse;

public class AccountUserResponse<T> extends IResponse {
    public T data;

    public AccountUserResponse(int httpStatusCode, String status, T data, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.data = data;
    }
}