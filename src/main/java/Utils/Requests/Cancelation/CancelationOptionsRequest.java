package Utils.Requests.Cancelation;
import Utils.Constants;
import Utils.Requests.IRequest;

public class CancelationOptionsRequest extends IRequest{
    
    private String uuid;
    private String password;
    private String rfc;
    private String b64Cer;
    private String b64key;
    private String b64Pfx;
    private String xml;
    private String motivo;
    private String folioSustitucion;
      

    public CancelationOptionsRequest(String token, String URI, String uuid, String password, String rfc, String b64Cer, String b64Key, String motivo, String folioSustitucion, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.CANCELATION_CSD_PATH, proxyHost, proxyPort);
        this.uuid = uuid;
        this.password = password;
        this.rfc = rfc;
        this.b64Cer = b64Cer;
        this.b64key = b64Key;
        this.motivo = motivo;
        this.folioSustitucion = folioSustitucion;
    }
    
    public CancelationOptionsRequest(String token, String URI, String uuid, String password, String rfc, String b64Pfx, String motivo, String folioSustitucion, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.CANCELATION_PFX_PATH, proxyHost, proxyPort);
        this.uuid = uuid;
        this.password = password;
        this.rfc = rfc;
        this.b64Pfx = b64Pfx;
        this.motivo = motivo;
        this.folioSustitucion = folioSustitucion;
    }
    
    public CancelationOptionsRequest(String token, String URI, String xml, String proxyHost, int proxyPort) {
        super(token, URI+ Constants.CANCELATION_XML_PATH, proxyHost, proxyPort);
        this.xml = xml;
    }
    public CancelationOptionsRequest(String token, String URI, String uuid, String rfc, String motivo, String folioSustitucion, String proxyHost, int proxyPort) {
        //super(token, URI + Constants.CANCELATION_UUID_PATH + rfc + "/" + uuid + "/" + motivo + "/" + foliosustitucion, proxyHost, proxyPort);
        super(token, URI + Constants.CANCELATION_UUID_PATH + String.format("%s/%s/%s/%s", rfc, uuid,  motivo, folioSustitucion ), proxyHost, proxyPort);
        this.uuid = uuid;
        this.rfc = rfc;
        this.motivo = motivo;
        this.folioSustitucion = folioSustitucion;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
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
    public String getB64Pfx() {
        return b64Pfx;
    }
    public String getMotivo() {
        return motivo;
    }
    public String getFolioSustitucion() {
        return folioSustitucion;
    }

}
