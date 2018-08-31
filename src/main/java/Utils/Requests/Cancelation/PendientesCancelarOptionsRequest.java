package Utils.Requests.Cancelation;

import Utils.Constants;
import Utils.Requests.IRequest;

public class PendientesCancelarOptionsRequest extends IRequest {
	public PendientesCancelarOptionsRequest(String token, String URI, String rfc) {
        super(token, URI+ Constants.PENDIENTES_CANCELAR_PATH+rfc);
    }
}
