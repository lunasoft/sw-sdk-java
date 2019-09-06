package Utils.Requests.Issue;

import Utils.Constants;
import Utils.Requests.IRequest;

public class IssueOptionsRequest extends IRequest {
    private String xml;

    public String getXml() {
        return xml;
    }

    public IssueOptionsRequest(String token, String URI, String xml, String version, String proxyHost, int proxyPort) {
        super(token, URI + Constants.ISSUE_JSON_PATH + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }
    public IssueOptionsRequest(String token, String URI, String xml, String version, boolean isXml, String proxyHost, int proxyPort, boolean isV2) {
        super(token, URI + (isV2 ? Constants.ISSUE_XML_V2_PATH : Constants.ISSUE_XML_PATH) + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }
}
