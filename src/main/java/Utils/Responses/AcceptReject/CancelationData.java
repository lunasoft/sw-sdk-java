package Utils.Responses.AcceptReject;

public class CancelationData {
	public String uuid;
	public String estatusUUID;
	public String respuesta;
	
	public CancelationData(String uuid, String string, String respuesta) {
		this.uuid = uuid;
		this.estatusUUID = string;
		this.respuesta = respuesta;
	}
}
