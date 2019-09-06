package Utils.Responses.Csd;

import Utils.Responses.IResponse;

public class InfoCsdResponse extends IResponse {
	public InfoCsd data;
	
	public InfoCsdResponse(int httpStatusCode, String status, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
	}
	public InfoCsdResponse(int httpStatusCode, String status, InfoCsd data, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
		this.data = data;
	}
}
