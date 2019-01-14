package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

public class StampOptionsRequest extends IRequest {
    private String xml;
    
    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, int proxyPort) {

        super(token, URI + Constants.STAMP_PATH + version + "/b64", version, isb64, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version, String proxyHost, int proxyPort) {
        super(token, URI + Constants.STAMP_PATH + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }
    
    public String getXml() {
        return xml;
    }
}