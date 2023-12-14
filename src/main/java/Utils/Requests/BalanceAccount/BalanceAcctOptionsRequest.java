package Utils.Requests.BalanceAccount;

import java.util.UUID;

import Utils.Constants;
import Utils.Requests.IRequest;
import Utils.Helpers.EnumBalanceStamp.AccountBalanceAction;

public class BalanceAcctOptionsRequest extends IRequest {
    
    public BalanceAcctOptionsRequest(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_PATH, proxyHost, proxyPort);
    }

    public BalanceAcctOptionsRequest(String token, String URI, UUID idUser, int stamps, String comment, AccountBalanceAction action, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_MANAGEMENT_PATH, idUser, stamps, comment, action, proxyHost, proxyPort);
    }

}