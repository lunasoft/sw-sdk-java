package Services;

/**
 * Created by asalvio on 08/02/2017.
 */
public  abstract class SWService {
    private String Token;
    private String User;
    private String Password;

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
}
