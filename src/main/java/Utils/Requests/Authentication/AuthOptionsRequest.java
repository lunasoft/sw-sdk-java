package Utils.Requests.Authentication;

import Utils.Constants;
import Utils.Requests.IRequest;

public class AuthOptionsRequest extends IRequest {
    private String ProxyHost = null;
    private String PortHost = null;

    public void setProxyHost(String proxyHost) {
        ProxyHost = proxyHost;
    }

    public void setPortHost(String portHost) {
        PortHost = portHost;
    }

    public AuthOptionsRequest(String URI, String user, String password, String proxyHost, int proxyPort) {
        super(URI+ Constants.AUTH_PATH, user, password, proxyHost, proxyPort);
    }

    public String getProxyHost() {
        return ProxyHost;
    }

    public String getPortHost() {
        return PortHost;
    }
}
