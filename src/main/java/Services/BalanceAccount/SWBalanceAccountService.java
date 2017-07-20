package Services.BalanceAccount;

//@author: Lupita Alvarado

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
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
    
    public IResponse GetBalanceAccount() throws AuthException, GeneralException {

        if (getToken()==null){

            generateToken();
        }
        
        //MAKE GET BALANCE ACCOUNT PROCESS, CUSTOMER ALREADY HAS TOKEN

        BalanceAcctOptionsRequest settings = new BalanceAcctOptionsRequest(getToken(),getURI());

        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);

    }
}
