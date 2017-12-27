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

    public AuthOptionsRequest(String URI, String user, String password) {
        super(URI+ Constants.AUTH_PATH, user, password);
    }

    public String getProxyHost() {
        return ProxyHost;
    }

    public String getPortHost() {
        return PortHost;
    }

    public AuthOptionsRequest(String URI, String user, String password, String hostProxy, String portProxy) {

        super(URI+ Constants.AUTH_PATH, user, password);
        this.setPortHost(portProxy);
        this.setProxyHost(hostProxy);
    }
}
