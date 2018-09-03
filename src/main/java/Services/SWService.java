package Services;

import java.io.IOException;
import java.util.Date;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.Authentication.SuccessAuthResponse;

public abstract class SWService {
    private String Token = null;
    private String User = null;
    private String Password = null;
    private Date time = null;

    public String getToken() throws AuthException, GeneralException, IOException {
    	if(time.getTime()<new Date().getTime()) {
    		generateToken();
    	}
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
        try {
			generateToken();
		} catch (AuthException | GeneralException | IOException e) {
			e.printStackTrace();
		}
    }

    protected SWService(String token, String URI) {
        Token = token;
        this.URI = URI;
    }

    public void setToken(String token) {
        Token = token;
    }
    
    public void setTime(long time) {
    	this.time = new Date((long)time*1000);
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    public void generateToken() throws AuthException, GeneralException, IOException {
        if (User == null || Password == null) {
            throw new AuthException(400, "no existen elementos de autenticación");
        }
        AuthOptionsRequest settings = new AuthOptionsRequest(URI, getUser(), getPassword());
        AuthRequest req = new AuthRequest();
        SuccessAuthResponse res = (SuccessAuthResponse) req.sendRequest(settings);
        if (res.HttpStatusCode == 200) {
        	setTime(res.expires_in);
            setToken(res.token);
        } else {
            throw new AuthException(res.HttpStatusCode, res.message);
        }
    }
}
