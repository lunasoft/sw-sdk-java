package Utils.Requests.BalanceAccount;

import Utils.Constants;
import Utils.Requests.IRequest;

public class BalanceAcctOptionsRequest extends IRequest {
    
    public BalanceAcctOptionsRequest(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_PATH, proxyHost, proxyPort);
    }

    public BalanceAcctOptionsRequest(String token, String URI, String idUser, int stamps, String comment, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_MANAGEMENT_PATH, idUser, stamps, comment, proxyHost, proxyPort);
    }
    
}