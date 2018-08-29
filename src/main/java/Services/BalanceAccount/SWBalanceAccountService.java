package Services.BalanceAccount;

import java.io.IOException;

//@author: Lupita Alvarado

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.BalanceAccount.BalanceAcctOptionsRequest;
import Utils.Requests.BalanceAccount.BalanceAcctRequest;
import Utils.Responses.IResponse;


public class SWBalanceAccountService extends SWService {
    
    public SWBalanceAccountService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWBalanceAccountService(String token, String URI) {
        super(token, URI);
    }
    
    public IResponse GetBalanceAccount() throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        BalanceAcctOptionsRequest settings = new BalanceAcctOptionsRequest(getToken(),getURI());
        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);
    }
}
