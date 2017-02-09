package Services;

/**
 * Created by asalvio on 08/02/2017.
 */
public  abstract class SWService {
    public String Token;
    public String User;
    public String Password;
    public String URI;

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
}
