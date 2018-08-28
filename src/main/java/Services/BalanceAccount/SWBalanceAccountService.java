package Services.BalanceAccount;

import java.io.IOException;

//@author: Lupita Alvarado

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.BalanceAccount.BalanceAcctOptionsRequest;
import Utils.Requests.BalanceAccount.BalanceAcctRequest;
import Utils.Responses.IResponse;
import java.text.ParseException;


public class SWBalanceAccountService extends SWService {
    
    public SWBalanceAccountService(String URI, String user, String password) throws ParseException, GeneralException, AuthException, IOException {
        super(URI, user, password);
        SetupRequest();
    }

    public SWBalanceAccountService(String URI, String token) {
        super(URI, token);
    }
    
    public IResponse GetBalanceAccount() throws AuthException, GeneralException, IOException, ParseException {

        BalanceAcctOptionsRequest settings = new BalanceAcctOptionsRequest(getURI(), getToken());
        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);

    }
}
