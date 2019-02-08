package Services.Relations;

import java.io.IOException;

import javax.xml.soap.SOAPException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Relations.CfdiRelacionadosOptionsRequest;
import Utils.Requests.Relations.CfdiRelacionadosRequest;
import Utils.Responses.IResponse;

public class SWRelationsService extends SWService{
	public SWRelationsService(String user, String password, String URI) throws AuthException {
        super(user, password, URI);
    }

    public SWRelationsService(String token, String URI) {
        super(token, URI);
    }
    
    public SWRelationsService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
        super(user, password, URI, proxyHost, proxyPort);
    }

    public SWRelationsService(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);
    }
    
    public IResponse CfdiRelacionadosCSD(String uuid,String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException, IOException {
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, password_csd, rfc, b64Cer, b64Key, getProxyHost(), getProxyPort());
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse CfdiRelacionadosPFX(String uuid,String password_csd, String rfc, String b64pfx) throws AuthException, GeneralException, IOException, SOAPException {
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, password_csd, rfc, b64pfx, getProxyHost(), getProxyPort());
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestPFX(settings);
    }
    
    public IResponse CfdiRelacionadosXML(String xml) throws AuthException, GeneralException, IOException, SOAPException {
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), xml, getProxyHost(), getProxyPort());
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestXML(settings);
    }
    
    public IResponse CfdiRelacionadosUUID(String uuid, String rfc) throws AuthException, GeneralException, IOException, SOAPException {
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, rfc, getProxyHost(), getProxyPort());
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestUUID(settings);
    }
}
