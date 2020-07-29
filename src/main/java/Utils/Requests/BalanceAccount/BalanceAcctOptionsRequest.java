package Utils.Requests.BalanceAccount;

import Utils.Constants;
import Utils.Requests.IRequest;

public class BalanceAcctOptionsRequest extends IRequest {
    
    public BalanceAcctOptionsRequest(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_PATH, proxyHost, proxyPort);
    }
    
}
