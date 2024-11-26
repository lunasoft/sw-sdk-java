package Utils.Requests.Authentication;

import Utils.Constants;
import Utils.Requests.IRequest;

public class AuthOptionsRequest extends IRequest {
    private String ProxyHost = null;
    private String PortHost = null;
    private String User;
	private String Password;

    public void setProxyHost(String proxyHost) {
        ProxyHost = proxyHost;
    }

    public void setPortHost(String portHost) {
        PortHost = portHost;
    }
    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public AuthOptionsRequest(String URI, String user, String password, String proxyHost, int proxyPort) {
        super(URI+ Constants.AUTH_PATH_V2, user, password, proxyHost, proxyPort);  // Llama al constructor de SWService para inicializar correctamente
        this.ProxyHost = proxyHost;
        this.PortHost = String.valueOf(proxyPort);  // Guarda el puerto como un string
        this.User = user;
        this.Password = password;
    }
    

    public String getProxyHost() {
        return ProxyHost;
    }

    public String getPortHost() {
        return PortHost;
    }
    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }
}
