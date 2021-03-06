package Services.BalanceAccount;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.BalanceAccount.BalanceAcctOptionsRequest;
import Utils.Requests.BalanceAccount.BalanceAcctRequest;
import Utils.Responses.IResponse;


public class SWBalanceAccountService extends SWService {
    
    public SWBalanceAccountService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWBalanceAccountService(String token, String URI) {
        super(token, URI);
    }
    
    public SWBalanceAccountService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWBalanceAccountService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public IResponse GetBalanceAccount() throws AuthException, GeneralException, IOException {
        BalanceAcctOptionsRequest settings = new BalanceAcctOptionsRequest(getToken(), getURI(), getProxyHost(), getProxyPort());
        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);
    }
}
