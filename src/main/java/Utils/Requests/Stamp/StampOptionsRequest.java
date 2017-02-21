package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

import java.io.File;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private String version;


    public String getXml() {
        return xml;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version) {
        super(token, URI+ Constants.STAMP_PATH+version);
        this.xml = xml;
        this.version = version;
    }

}
