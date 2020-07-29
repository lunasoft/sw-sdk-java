package Services.StatusCfdi;

import java.io.IOException;

import javax.xml.soap.SOAPException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.StatusCfdi.StatusCancelationOptionsRequest;
import Utils.Requests.StatusCfdi.StatusCancelationRequest;
import Utils.Responses.IResponse;

public class StatusCfdiService {
	private String URL = null;
	private String Action = null;
	public StatusCfdiService(String URL, String Action) {
        this.URL = URL;
        this.Action = Action;
    }
    
    public IResponse StatusCfdi(String rfcEmisor, String rfcReceptor, String total, String uuid) throws AuthException, GeneralException, IOException, SOAPException {
        StatusCancelationOptionsRequest settings = new StatusCancelationOptionsRequest(URL, Action, rfcEmisor, rfcReceptor, total, uuid, null, 0);
        StatusCancelationRequest req = new StatusCancelationRequest();
        return req.sendRequest(settings);
    }
}
