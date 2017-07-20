package Utils.Requests.BalanceAccount;

import Utils.Constants;
import Utils.Requests.IRequest;

//@author: Lupita Alvarado

public class BalanceAcctOptionsRequest extends IRequest {
    
    public BalanceAcctOptionsRequest(String token, String URI) {
        super(token, URI+ Constants.BALANCE_ACCOUNT_PATH);
    }
    
}
