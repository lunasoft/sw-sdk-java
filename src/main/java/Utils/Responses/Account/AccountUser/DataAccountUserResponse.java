package Utils.Responses.Account.AccountUser;

import Utils.Responses.IResponse;

public class DataAccountUserResponse extends IResponse {
    public DataAccountUser data;
	
	public DataAccountUserResponse(int httpStatusCode, String status, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
	}
	public DataAccountUserResponse(int httpStatusCode, String status, DataAccountUser data, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
		this.data = data;
	}
    
}
