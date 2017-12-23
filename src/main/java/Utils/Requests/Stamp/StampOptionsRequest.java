package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

import java.io.File;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private String version;
    private String ProxyHost = null;
    private String PortHost = null;

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

    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64) {

        super(token, URI+ Constants.STAMP_PATH+version+"/b64",version, isb64);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, String portHost) {

        super(token, URI+ Constants.STAMP_PATH+version+"/b64",version, isb64);
        this.xml = xml;
        this.version = version;
        this.setPortHost(portHost);
        this.setProxyHost(proxyHost);
    }

    public String getXml() {
        return xml;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version) {
        super(token, URI+ Constants.STAMP_PATH+version, xml, version);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version,String proxyHost, String portHost) {
        super(token, URI+ Constants.STAMP_PATH+version, xml, version);
        this.xml = xml;
        this.version = version;
        this.setPortHost(portHost);
        this.setProxyHost(proxyHost);
    }

}
