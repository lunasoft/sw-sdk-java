package Services;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Constants;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.IResponse;
import Utils.Responses.SuccessAuthResponse;

public  abstract class SWService {
    private String Token = null;
    private String User = null;
    private String Password = null;
    private String ProxyHost = null;

    public SWService(String token, String proxyHost, String portHost, String URI) {
        Token = token;
        ProxyHost = proxyHost;
        PortHost = portHost;
        this.URI = URI;
    }

    public String getProxyHost() {

        return ProxyHost;
    }

    public void setProxyHost(String proxyHost) {
        ProxyHost = proxyHost;
    }

    public String getPortHost() {
        return PortHost;
    }

    public void setPortHost(String portHost) {
        PortHost = portHost;
    }

    private String PortHost = null;
    public String getToken() {
        return Token;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }

    public String getURI() {
        return URI;
    }

    private String URI;

    protected SWService(String user, String password, String URI) {
        User = user;
        Password = password;
        this.URI = URI;
    }

    protected SWService(String token, String URI) {

        Token = token;
        this.URI = URI;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    public void generateToken() throws AuthException, GeneralException {

        if (User == null || Password == null) {
            //CUSTOMER HASN'T TOKEN, USER AND PASSWORD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
            throw new AuthException(400, "no existen elementos de autenticación");
        }

        //CUSTOMER HASN'T TOKEN, BUT HAS USER AND PASSWORD --> AUTH,GENERATE TOKEN AND SET TOKEN IN GLOBAL SETTINGS
        AuthOptionsRequest settings = new AuthOptionsRequest(URI, getUser(), getPassword());
        AuthRequest req = new AuthRequest();
        SuccessAuthResponse res = (SuccessAuthResponse) req.sendRequest(settings);
        if (res.HttpStatusCode == 200) {
            setToken(res.token);
        } else {
            //CUSTOMER HASN'T TOKEN, AND USER AND PASSWORD ARE BAD--> WE CANT' DO ANYTHING --> THROW EXCEPTION
            throw new AuthException(res.HttpStatusCode, res.message);
        }
    }
}
