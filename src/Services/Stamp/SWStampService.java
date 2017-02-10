package Services.Stamp;

import Exceptions.AuthException;
import Services.SWService;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Responses.AuthResponse;
import Utils.Responses.IResponse;

/**
 * Created by asalvio on 09/02/2017.
 */
public class SWStampService extends SWService {

    protected SWStampService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWStampService(String token, String URI) {
        super(token, URI);
    }

    public IResponse Stamp(String xml, String version) throws AuthException {

        if (getToken().isEmpty()){

            if (getUser().isEmpty() || getPassword().isEmpty()){
                //CUSTOMER HASN'T TOKEN, USER AND PASSWORD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(500,"No se cuenta con token, usuario y password");

            }

            //CUSTOMER HASN'T TOKEN, BUT HAS USER AND PASSWORD --> AUTH,GENERATE TOKEN AND SET TOKEN IN GLOBAL SETTINGS
            AuthOptionsRequest settings = new AuthOptionsRequest(Constants.BASE_PATH+Constants.AUTH_PATH,getUser(),getPassword());
            AuthRequest req = new AuthRequest();
            AuthResponse res = (AuthResponse) req.sendRequest(settings);
            if (res.HttpStatusCode==200){
                setToken(res.Msg);
            }
            throw new AuthException(res.HttpStatusCode,res.Msg);

        }
        //MAKE STAMP PROCESS, CUSTOMER ALREADY HAS TOKEN
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,"v1");
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);

    }

    public IResponse Stamp(byte xml, String version) throws AuthException {

        if (getToken().isEmpty()){

            if (getUser().isEmpty() || getPassword().isEmpty()){
                //CUSTOMER HASN'T TOKEN, USER AND PASSWORD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(500,"No se cuenta con token, usuario y password");

            }

            //CUSTOMER HASN'T TOKEN, BUT HAS USER AND PASSWORD --> AUTH,GENERATE TOKEN AND SET TOKEN IN GLOBAL SETTINGS
            AuthOptionsRequest settings = new AuthOptionsRequest(Constants.BASE_PATH+Constants.AUTH_PATH,getUser(),getPassword());
            AuthRequest req = new AuthRequest();
            AuthResponse res = (AuthResponse) req.sendRequest(settings);
            if (res.HttpStatusCode==200){
                setToken(res.Msg);
            }
            throw new AuthException(res.HttpStatusCode,res.Msg);

        }
        //MAKE STAMP PROCESS, CUSTOMER ALREADY HAS TOKEN
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,"v1");
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);

    }
}
