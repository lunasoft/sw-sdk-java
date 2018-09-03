package Utils.Requests.AcceptReject;
import java.util.Map;

import Utils.Constants;
import Utils.Requests.IRequest;

public class AceptarRechazarOptionsRequest extends IRequest{
    
    private String uuid;
    private Map <String, String> uuids;
    private String password_csd;
    private String rfc;
    private String b64Cer;
    private String b64key;
    private String b64Pfx;
    
    private String xml;
      

    public AceptarRechazarOptionsRequest(String token, String URI, Map <String, String> uuids, String password_csd, String rfc, String b64Cer, String b64Key) {
        super(token, URI+ Constants.ACEPTAR_RECHAZAR_CANCELACION_CSD_PATH);
        this.uuids = uuids;
        this.password_csd = password_csd;
        this.rfc = rfc;
        this.b64Cer = b64Cer;
        this.b64key = b64Key;
    }
    
    public AceptarRechazarOptionsRequest(String token, String URI, Map <String, String> uuids, String password_csd, String rfc, String b64Pfx) {
        super(token, URI+ Constants.ACEPTAR_RECHAZAR_CANCELACION_PFX_PATH);
        this.uuids = uuids;
        this.password_csd = password_csd;
        this.rfc = rfc;
        this.b64Pfx = b64Pfx;
    }
    
    public AceptarRechazarOptionsRequest(String token, String URI, String xml) {
        super(token, URI+ Constants.ACEPTAR_RECHAZAR_CANCELACION_XML_PATH);
        this.xml = xml;
    }
    public AceptarRechazarOptionsRequest(String token, String URI, String uuid, String rfc, String action) {
        super(token, URI+ Constants.ACEPTAR_RECHAZAR_CANCELACION_UUID_PATH+rfc+"/"+uuid+"/"+action);
        this.uuid = uuid;
        this.rfc = rfc;
    }

    public String getUuid() {
        return uuid;
    }
    
    public Map<String, String> getUuids() {
        return uuids;
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
    public String getB64Pfx() {
        return b64Pfx;
    }

}
