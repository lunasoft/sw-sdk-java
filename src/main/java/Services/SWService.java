package Services;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.SuccessAuthResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public  abstract class SWService {
    private String Token = null;
    private String User = null;
    private String Password = null;
    private int TimeSession = 2;
    private Date expirationDate = null;
    private String URI;

    public String token(){ return Token; }

    public String uri(){ return URI; }

    public String user(){ return User; }

    public  String password(){ return Password; }

    public  Date expiration_Date(){ return expirationDate; }
    
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

    public void setToken(String token) {
        Token = token;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    protected  SWService(SWService swService){
    }
    
    protected SWService(String URI, String user, String password) throws ParseException {
        User = user;
        Password = password;
        this.URI = URI;
    }

    protected SWService(String URI, String token ) {
        Token = token;
        this.URI = URI;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, TimeSession);
        expirationDate =  cal.getTime();
    }

    public void generateToken() throws AuthException, GeneralException, IOException {

        AuthOptionsRequest settings = new AuthOptionsRequest(URI, getUser(), getPassword());
        AuthRequest req = new AuthRequest();
        SuccessAuthResponse res = (SuccessAuthResponse) req.sendRequest(settings);
        if (res.HttpStatusCode == 200) {
            setToken(res.token);
        } else {
            throw new AuthException(res.HttpStatusCode, res.message);
        }
    }
    
    public SWService SetupRequest() throws GeneralException, AuthException, IOException, ParseException{

        if(Token == null || expirationDate == null){
            SWAuthenticationService auth = new SWAuthenticationService(getURI(), getUser(), getPassword());
            SuccessAuthResponse response = (SuccessAuthResponse) auth.Token();
            
            System.out.println(response);
            if("success".equals(response.Status)){
                Token = response.token;
                expirationDate = new SimpleDateFormat("yyyy-MM-dd mm:ss").parse(response.Expires_in);
            }
        }
        return this;
    }
    
}
