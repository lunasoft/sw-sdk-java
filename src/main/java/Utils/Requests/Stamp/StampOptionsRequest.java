package Utils.Requests.Stamp;

import Exceptions.GeneralException;
import Utils.Constants;
import Utils.Helpers.Validations;
import Utils.Requests.IRequest;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private byte[] zipData;
    private String emails;
    private String customId;
    private boolean pdf; 

    
    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, int proxyPort, boolean isV2) {
        super(token, URI + (isV2 ? Constants.STAMP_V2_PATH : Constants.STAMP_PATH) + version + "/b64", version, isb64, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, String xml, String version, String proxyHost, int proxyPort, boolean isV2) {
        super(token, URI + (isV2 ? Constants.STAMP_V2_PATH : Constants.STAMP_PATH) + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, byte[] zipData, String version, String proxyHost, int proxyPort) {
        super(token, URI + (Constants.STAMP_ZIP_PATH) + version, zipData, version, proxyHost, proxyPort);
        this.zipData = zipData;
    }
    public StampOptionsRequest(String token, String URI, String xml, String version, String proxyHost, int proxyPort, String [] emails, String customId, boolean pdf) throws GeneralException {
        super(token, URI + Constants.STAMP_XMLV4_PATH + version, xml, version, proxyHost, proxyPort);
        this.xml = xml;
        if (emails != null) {
            this.emails = Validations.validateEmails(emails);
        }
        if (customId != null) {
            Validations.validateCustomId(customId);
            this.customId = customId;
        }
        this.pdf = pdf;
    }
    public StampOptionsRequest(String token, String URI, String xml, String version, boolean isb64, String proxyHost, int proxyPort, String [] emails, String customId, boolean pdf) throws GeneralException {
        super(token, URI + Constants.STAMP_XMLV4_PATH + version + "/b64", version, isb64, proxyHost, proxyPort);
        this.xml = xml;
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
 
    public byte[] getZipData() {
        return zipData;
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