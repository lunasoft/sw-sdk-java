package Utils.Requests.Cancelation;
import Utils.Constants;
import Utils.Requests.IRequest;


public class CancelationOptionsRequest extends IRequest{
    
    private String uuid;
    private String password_csd;
    private String rfc;
    private String b64Cer;
    private String b64key;
    
    private String xml;
      

    public CancelationOptionsRequest(String token, String URI, String uuid, String password_csd, String rfc, String b64Cer, String b64Key) {
        super(token, URI+ Constants.CANCELATION_CSD_PATH);
        this.uuid = uuid;
        this.password_csd = password_csd;
        this.rfc = rfc;
        this.b64Cer = b64Cer;
        this.b64key = b64Key;
    }
    
    public CancelationOptionsRequest(String token, String URI, String xml) {
        super(token, URI+ Constants.CANCELATION_XML_PATH);
        this.xml = xml;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword_csd() {
        return password_csd;
    }

    public String getRfc() {
        return rfc;
    }

    public String getB64Cer() {
        return b64Cer;
    }

    public String getB64key() {
        return b64key;
    }

    public String getXml() {
        return xml;
    }
    

}
