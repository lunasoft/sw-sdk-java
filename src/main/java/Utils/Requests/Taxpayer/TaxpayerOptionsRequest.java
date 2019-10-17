package Utils.Requests.Taxpayer;

import Utils.Constants;
import Utils.Requests.IRequest;

public class TaxpayerOptionsRequest extends IRequest { 
	public TaxpayerOptionsRequest(String token, String URI,String rfc, String proxyHost, int proxyPort) {
        super(token, URI + Constants.TAXPAYERS_PATH + rfc, proxyHost, proxyPort);    
    }
}
