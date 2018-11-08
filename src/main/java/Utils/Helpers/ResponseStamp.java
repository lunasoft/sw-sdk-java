package Utils.Helpers;

import org.apache.http.StatusLine;

import Utils.Responses.IResponse;

public abstract class ResponseStamp {
	int status;
	String response;
	StatusLine reason;
	public void setResponse(String response) {
		this.response = response;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setReason(StatusLine reason) {
		this.reason = reason;
	}
	
	public abstract IResponse getResponse(); 
}
