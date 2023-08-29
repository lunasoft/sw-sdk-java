package Utils.Requests.Stamp;

import Utils.Constants;
import Utils.Requests.IRequest;

public class StampOptionsRequest extends IRequest {
    private String xml;
    private byte[] xmlData; 
    private byte[] zipData; 

    
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

    public StampOptionsRequest(String token, String URI, byte[] xmlData, String version, String proxyHost, int proxyPort, boolean isV2) {
        super(token, URI + (isV2 ? Constants.STAMP_V2_PATH : Constants.STAMP_PATH) + version, xmlData, version, proxyHost, proxyPort);
        this.xmlData = xmlData;
        this.version = version;
    }

    public StampOptionsRequest(String token, String URI, byte[] zipData, String version, String proxyHost, int proxyPort, boolean isV2, boolean isZip) {
        super(token, URI + (isV2 ? Constants.STAMP_V2_PATH : Constants.STAMP_ZIP_PATH) + version, zipData, version, proxyHost, proxyPort);
        this.zipData = zipData;
    }
    

    public String getXml() {
        return xml;
    }

    public byte[] getXML() {
        return XML;
    }
    
    // Función para obtener los datos del archivo ZIP
    public byte[] getZipData() {
        return zipData;
    }
}