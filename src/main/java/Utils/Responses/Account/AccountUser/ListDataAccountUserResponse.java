package Utils.Responses.Account.AccountUser;

import java.util.List;

import Utils.Responses.IResponse;

public class ListDataAccountUserResponse extends IResponse {
    public List<DataAccountUser> data;
	
	public ListDataAccountUserResponse(int httpStatusCode, String status, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
	}

	public ListDataAccountUserResponse(int httpStatusCode, String status, List<DataAccountUser> lista, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
		data = lista;
	}
}
