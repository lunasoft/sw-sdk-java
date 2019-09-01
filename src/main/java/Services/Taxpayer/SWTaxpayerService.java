package Services.Taxpayer;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Requests.Cancelation.CancelationRequest;
import Utils.Requests.Taxpayer.TaxpayerOptionsRequest;
import Utils.Requests.Taxpayer.TaxpayerRequest;
import Utils.Responses.IResponse;

public class SWTaxpayerService extends SWService{
    public SWTaxpayerService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWTaxpayerService(String token, String URI) {
        super(token, URI);
    }
    
    public SWTaxpayerService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWTaxpayerService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    public IResponse Taxpayer(String rfc) throws AuthException, GeneralException, IOException {
        TaxpayerOptionsRequest settings = new TaxpayerOptionsRequest(getToken(), getURI(), rfc, getProxyHost(), getProxyPort());
        TaxpayerRequest req = new TaxpayerRequest();
        return req.sendRequestrfc(settings);
    }
}
