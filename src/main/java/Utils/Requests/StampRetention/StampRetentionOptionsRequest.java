package Utils.Requests.StampRetention;
import Utils.Constants;
import Utils.Requests.IRequest;

public class StampRetentionOptionsRequest extends IRequest{
    private String xml;

    public StampRetentionOptionsRequest(String token, String URI, String xml, String version, String proxyHost, int proxyPort){
        super(token, URI + Constants.STAMP_RETENTION_PATH + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
    }

    public StampRetentionOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, int proxyPort){
        super(token, URI + Constants.STAMP_RETENTION_PATH + version + "/b64", xml, version, proxyHost, proxyPort);
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }
}
