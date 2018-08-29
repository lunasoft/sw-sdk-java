package Services.Cancelation;

import java.io.IOException;
import java.util.Map;

import javax.xml.soap.SOAPException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Cancelation.AceptarRechazarCancelationRequest;
import Utils.Requests.Cancelation.AceptarRechazarOptionsRequest;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Requests.Cancelation.CancelationRequest;
import Utils.Requests.Cancelation.CfdiRelacionadosOptionsRequest;
import Utils.Requests.Cancelation.CfdiRelacionadosRequest;
import Utils.Requests.Cancelation.PendientesCancelarOptionsRequest;
import Utils.Requests.Cancelation.PendientesCancelarRequest;
import Utils.Requests.Cancelation.StatusCancelationOptionsRequest;
import Utils.Requests.Cancelation.StatusCancelationRequest;
import Utils.Responses.IResponse;



public class SWCancelationService extends SWService {
    
    public SWCancelationService(String user, String password, String URI) {
        super(user, password, URI);
    }

    public SWCancelationService(String token, String URI) {
        super(token, URI);
    }
    
    public IResponse Cancelation(String uuid, String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),uuid, password_csd, rfc, b64Cer, b64Key);
        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse Cancelation(String xml) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),xml);
        CancelationRequest req = new CancelationRequest();
        return req.sendRequest(settings, true);
    }
    
    public IResponse Cancelation(String uuid, String password_csd, String rfc, String b64Pfx) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),uuid, password_csd, rfc, b64Pfx);
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestPfx(settings);
    }
    
    public IResponse Cancelation(String uuid, String rfc) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        CancelationOptionsRequest settings = new CancelationOptionsRequest(getToken(),getURI(),uuid, rfc);
        CancelationRequest req = new CancelationRequest();
        return req.sendRequestUuid(settings);
    }
    
    public IResponse StatusCancelation(String rfcEmisor, String rfcReceptor, String total, String uuid) throws AuthException, GeneralException, IOException, SOAPException {
        StatusCancelationOptionsRequest settings = new StatusCancelationOptionsRequest("https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta", rfcEmisor, rfcReceptor, total, uuid);
        StatusCancelationRequest req = new StatusCancelationRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse AceptarRechazarCancelacionCSD(Map<String,String> uuids,String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuids, password_csd, rfc, b64Cer, b64Key);
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse AceptarRechazarCancelacionPFX(Map<String,String> uuids,String password_csd, String rfc, String b64pfx) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuids, password_csd, rfc, b64pfx);
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequestPFX(settings);
    }
    
    public IResponse AceptarRechazarCancelacionXML(String xml) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(),xml);
        AceptarRechazarCancelationRequest req = new AceptarRechazarCancelationRequest();
        return req.sendRequestXML(settings);
    }
    
    public IResponse AceptarRechazarCancelacionUUID(String uuid, String rfc, String action) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        AceptarRechazarOptionsRequest settings = new AceptarRechazarOptionsRequest(getToken(),getURI(), uuid, rfc, action);
        AceptarRechazarCancelationRequest req = new  AceptarRechazarCancelationRequest();
        return req.sendRequestUUID(settings);
    }
    
    public IResponse CfdiRelacionadosCSD(String uuid,String password_csd, String rfc, String b64Cer, String b64Key) throws AuthException, GeneralException, IOException {
        if (getToken()==null){
            generateToken();
        }
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, password_csd, rfc, b64Cer, b64Key);
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequest(settings);
    }
    
    public IResponse CfdiRelacionadosPFX(String uuid,String password_csd, String rfc, String b64pfx) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, password_csd, rfc, b64pfx);
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestPFX(settings);
    }
    
    public IResponse CfdiRelacionadosXML(String xml) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), xml);
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestXML(settings);
    }
    
    public IResponse CfdiRelacionadosUUID(String uuid, String rfc) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        CfdiRelacionadosOptionsRequest settings = new CfdiRelacionadosOptionsRequest(getToken(),getURI(), uuid, rfc);
        CfdiRelacionadosRequest req = new  CfdiRelacionadosRequest();
        return req.sendRequestUUID(settings);
    }
    
    public IResponse PendientesPorCancelar(String rfc) throws AuthException, GeneralException, IOException, SOAPException {
        if (getToken()==null){
            generateToken();
        }
        PendientesCancelarOptionsRequest settings = new PendientesCancelarOptionsRequest(getToken(),getURI(), rfc);
        PendientesCancelarRequest req = new  PendientesCancelarRequest();
        return req.sendRequest(settings);
    }
    
}
