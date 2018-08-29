package Utils.Requests.Issue;

import Utils.Constants;
import Utils.Requests.IRequest;

public class IssueOptionsRequest extends IRequest {
    private String xml;
    private String version;

    public String getXml() {
        return xml;
    }

    public IssueOptionsRequest(String token, String URI, String xml, String version) {
        super(token, URI+ Constants.ISSUE_JSON_PATH+version, xml, version);
        this.xml = xml;
        this.version = version;
    }

}
