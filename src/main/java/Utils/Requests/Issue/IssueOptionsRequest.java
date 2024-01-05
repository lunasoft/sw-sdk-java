package Utils.Requests.Issue;

import Exceptions.GeneralException;
import Utils.Constants;
import Utils.Helpers.Validations;
import Utils.Requests.IRequest;

public class IssueOptionsRequest extends IRequest {
    private String xml;
    private String emails;
    private String customId;
    private boolean pdf;

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
    
    public IssueOptionsRequest(String token, String URI, String xml, String version,
            boolean isXml, String proxyHost, int proxyPort, String[] emails, String customId, boolean pdf)
            throws GeneralException {
        super(token, URI + (isXml ? Constants.ISSUE_XMLV4_PATH : Constants.ISSUE_JSON_XMLV4_PATH) + version, xml,
                version, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
        if (emails != null) {
            this.emails = Validations.validateEmails(emails);
        }
        if (customId != null) {
            Validations.validateCustomId(customId);
            this.customId = customId;
        }
        this.pdf = pdf;
    }

    public String getXml() {
        return xml;
    }

    public String getEmails(){
        return emails;
    }

    public String  getCustomId(){
        return customId;
    }

    public boolean getPfd(){
        return pdf;
    }
}
