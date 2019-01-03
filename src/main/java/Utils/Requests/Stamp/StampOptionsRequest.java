package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private String proxyHost;
    private String proxyPort;
    
    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, String proxyPort) {

        super(token, URI + Constants.STAMP_PATH + version + "/b64", version, isb64);
        this.xml = xml;
        this.version = version;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version, String proxyHost, String proxyPort) {
        super(token, URI + Constants.STAMP_PATH + version, xml, version);
        this.xml = xml;
        this.version = version;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }
    
    public String getXml() {
        return xml;
    }
    
    public String getProxyHost() {
    	return proxyHost;
    }
    
    public String getProxyPort() {
    	return proxyPort;
    }

}