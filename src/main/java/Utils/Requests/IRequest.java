package Utils.Requests;

public abstract class IRequest {
    public String Token;
    public String URI;
    public String User;
    public String Password;
    public String version;
    public String xml;

    public IRequest(String URI, String user, String password, String version, boolean isb64) {
        this.URI = URI;
        User = user;
        Password = password;
        this.version = version;
        this.isb64 = isb64;
    }

    public IRequest(String token, String URI, String version, boolean isb64) {

        Token = token;
        this.URI = URI;
        this.version = version;
        this.isb64 = isb64;
    }

    public boolean isb64;

    public IRequest(String token, String URI, boolean isb64) {
        Token = token;
        this.URI = URI;
        this.isb64 = isb64;
    }

    public IRequest(String URI, String user, String password) {
        this.URI = URI;
        User = user;
        Password = password;
    }

    public IRequest(String token, String URI, String xml, String version) {
        Token = token;
        this.URI = URI;
        this.version = version;
        this.xml = xml;
    }
}
