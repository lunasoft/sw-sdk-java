package Services.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Requests.Stamp.StampRequestDummy;

import Utils.Responses.IResponse;
import org.json.JSONObject;


import java.nio.charset.Charset;



public class SWStampService extends SWService {

    public SWStampService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWStampService(String token, String URI) {
        super(token, URI);
    }

    public IResponse Stamp(String xml, String version) throws AuthException, GeneralException {



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
                JSONObject obj = new JSONObject(res.Data);
                setToken(obj.getString("token"));
            }
            else{
                //CUSTOMER HASN'T TOKEN, AND USER AND PASSWORD ARE BAD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(res.HttpStatusCode,res.Data);
            }


        }
        //MAKE STAMP PROCESS, CUSTOMER ALREADY HAS TOKEN

        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,version);
        String dum = settings.URI.split("-")[0];
        if (dum.equalsIgnoreCase("d")){
            StampRequestDummy req = new StampRequestDummy();
            return req.sendRequest(settings);
        }
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);

    }

    public IResponse Stamp(byte[] xmlFile, String version) throws AuthException, GeneralException {
        //BINARY XML


        String xmlProcess = new String(xmlFile,Charset.forName("UTF-8"));

        if (getToken()==null){

            if (getUser()==null || getPassword()==null){
                //CUSTOMER HASN'T TOKEN, USER AND PASSWORD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(500,"no existen elementos de autenticación");

            }

            //CUSTOMER HASN'T TOKEN, BUT HAS USER AND PASSWORD --> TRY AUTH,GENERATE TOKEN AND SET TOKEN IN GLOBAL SETTINGS
            AuthOptionsRequest settings = new AuthOptionsRequest(Constants.BASE_PATH,getUser(),getPassword());
            AuthRequest req = new AuthRequest();
            IResponse res =  req.sendRequest(settings);
            if (res.HttpStatusCode==200){
                JSONObject obj = new JSONObject(res.Data);
                setToken(obj.getString("token"));
            }
            else{
                //CUSTOMER HASN'T TOKEN, AND USER AND PASSWORD ARE BAD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
                throw new AuthException(res.HttpStatusCode,res.Data);
            }


        }

        //MAKE STAMP PROCESS, CUSTOMER ALREADY HAS TOKEN

        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xmlProcess,version);

        String dum = settings.URI.split("-")[0];
        if (dum.equalsIgnoreCase("d")){
            StampRequestDummy req = new StampRequestDummy();
            return req.sendRequest(settings);
        }
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);

    }
}
