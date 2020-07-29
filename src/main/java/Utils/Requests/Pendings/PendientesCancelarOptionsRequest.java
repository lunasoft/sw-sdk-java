package Utils.Requests.Pendings;

import Utils.Constants;
import Utils.Requests.IRequest;

public class PendientesCancelarOptionsRequest extends IRequest {
	public PendientesCancelarOptionsRequest(String token, String URI, String rfc, String proxyHost, int proxyPort) {
        super(token, URI + Constants.PENDIENTES_CANCELAR_PATH + rfc, proxyHost, proxyPort);
    }
}
