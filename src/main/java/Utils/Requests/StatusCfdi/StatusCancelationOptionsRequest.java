package Utils.Requests.StatusCfdi;

import Utils.Requests.IRequest;

public class StatusCancelationOptionsRequest extends IRequest{
	private String rfcEmisor;
	private String rfcReceptor;
	private String total;
	private String uuid;
	private String action;
	
	public StatusCancelationOptionsRequest(String URI, String action, String rfcEmisor, String rfcReceptor, String total, String uuid, String proxyHost, int proxyPort) {
		super("", URI, proxyHost, proxyPort);
        this.rfcEmisor = rfcEmisor;
        this.rfcReceptor = rfcReceptor;
        this.total = total;
        this.uuid = uuid;
        this.action = action;
    }
	
	public String getRfcEmisor() {
		return rfcEmisor;
	}
	public String getRfcReceptor() {
		return rfcReceptor;
	}
	public String getTotal() {
		return total;
	}
	public String getUuid() {
		return uuid;
	}
	public String getAction() {
		return action;
	}
}
