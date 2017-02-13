package Utils.Requests.Stamp;

import Utils.Requests.IRequest;

import java.io.File;

/**
 * Created by asalvio on 09/02/2017.
 */
public class StampOptionsRequest extends IRequest {
    private String xml;
    private byte xml_byte;
    private String version;

    public String getVersion() {
        return version;
    }

    public String getXml() {
        return xml;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version) {
        super(token, URI);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, File xml, String version) {
        super(token, URI);
        //this.xml_byte = xml;
        this.version = version;
    }
}
