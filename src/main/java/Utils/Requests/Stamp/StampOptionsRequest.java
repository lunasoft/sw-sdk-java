package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private String version;

    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64) {

        super(token, URI+ Constants.STAMP_PATH+version+"/b64",version, isb64);
        this.xml = xml;
        this.version = version;
    }

    public String getXml() {
        return xml;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version) {
        super(token, URI+ Constants.STAMP_PATH+version, xml, version);
        this.xml = xml;
        this.version = version;
    }

}
