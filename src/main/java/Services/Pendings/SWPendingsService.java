package Services.Pendings;

import java.io.IOException;

import jakarta.xml.soap.SOAPException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Pendings.PendientesCancelarOptionsRequest;
import Utils.Requests.Pendings.PendientesCancelarRequest;
import Utils.Responses.IResponse;

public class SWPendingsService extends SWService{
	public SWPendingsService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWPendingsService(String token, String URI) {
        super(token, URI);
    }
    
    public SWPendingsService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWPendingsService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public IResponse PendientesPorCancelar(String rfc) throws AuthException, GeneralException, IOException, SOAPException {
        PendientesCancelarOptionsRequest settings = new PendientesCancelarOptionsRequest(getToken(),getURI(), rfc, getProxyHost(), getProxyPort());
        PendientesCancelarRequest req = new  PendientesCancelarRequest();
        return req.sendRequest(settings);
    }
}
