package Utils.Requests.BalanceAccount;

import Utils.Constants;
import Utils.Requests.IRequest;

//@author: Lupita Alvarado

public class BalanceAcctOptionsRequest extends IRequest {
    
    public BalanceAcctOptionsRequest(String URI, String token) {
        super(URI+ Constants.BALANCE_ACCOUNT_PATH, token);
    }
    
}
