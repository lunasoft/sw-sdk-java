package Utils.Responses.Csd;

import java.util.List;

import Utils.Responses.IResponse;

public class ListInfoCsdResponse extends IResponse {
	public List<InfoCsd> data;
	
	public ListInfoCsdResponse(int httpStatusCode, String status, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
	}

	public ListInfoCsdResponse(int httpStatusCode, String status, List<InfoCsd> lista, String message, String messageDetail) {
		super(httpStatusCode, status, message, messageDetail);
		data = lista;
	}
}
