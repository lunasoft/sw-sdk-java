package Services.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;

import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.charset.Charset;


public class SWStampService extends SWService {

    public SWStampService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWStampService(String token, String URI) {
        super(token, URI);
    }

    public IResponse Stamp(String xml, String version) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,version);
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);
    }
    public IResponse Stamp(String xml, String version, boolean isb64) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
    if(isb64){
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,version,isb64);
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);
       }
       else{
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xml,version);
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);
        }
    }
    public IResponse Stamp(byte[] xmlFile, String version, boolean isb64) throws AuthException, GeneralException, IOException{
        String xmlProcess = new String(xmlFile,Charset.forName("UTF-8"));
        if (getToken()==null){
            generateToken();
        }
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xmlProcess,version);
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);
    }

    public IResponse Stamp(byte[] xmlFile, String version) throws AuthException, GeneralException, IOException {
        String xmlProcess = new String(xmlFile,Charset.forName("UTF-8"));
        if (getToken()==null){
            generateToken();
        }
        StampOptionsRequest settings = new StampOptionsRequest(getToken(),getURI(),xmlProcess,version);
        StampRequest req = new StampRequest();
        return req.sendRequest(settings);

    }
}
