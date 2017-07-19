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

            if (getUser()==null || getPassword()==null){
                //CUSTOMER HASN'T TOKEN, USER AND PASSWORD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(500,"no existen elementos de autenticación");

            }

            //CUSTOMER HASN'T TOKEN, BUT HAS USER AND PASSWORD --> AUTH,GENERATE TOKEN AND SET TOKEN IN GLOBAL SETTINGS
            AuthOptionsRequest settings = new AuthOptionsRequest(Constants.BASE_PATH,getUser(),getPassword());
            AuthRequest req = new AuthRequest();
            IResponse res =  req.sendRequest(settings);
            if (res.HttpStatusCode==200){
                setToken(res.token);
            }
            else{
                //CUSTOMER HASN'T TOKEN, AND USER AND PASSWORD ARE BAD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(res.HttpStatusCode,res.Data);
            }
        }
        
        //MAKE GET BALANCE ACCOUNT PROCESS, CUSTOMER ALREADY HAS TOKEN

        BalanceAcctOptionsRequest settings = new BalanceAcctOptionsRequest(getToken(),getURI());

        BalanceAcctRequest req = new BalanceAcctRequest();
        return req.sendRequest(settings);

    }
}
