package Utils.Responses.Validate;

import java.util.List;

import Utils.Responses.IResponse;

public class ValidateXmlResponse extends IResponse{
    public String cadenaOriginalSAT;
    public String cadenaOriginalComprobante;
    public String uuid;
    public String statusSat;
    public String statusCodeSat;
    public List<DetailNode> detail;
    
    
    public ValidateXmlResponse(int httpStatusCode, String status, String cadenaOriginalSAT, String cadenaOriginalComprobante, String uuid, String statusSat, String statusCodeSat, List<DetailNode> detail, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.cadenaOriginalSAT = cadenaOriginalSAT;
        this.cadenaOriginalComprobante= cadenaOriginalComprobante;
        this.uuid=uuid;
        this.statusSat=statusSat;
        this.statusCodeSat= statusCodeSat;
        this.detail= detail;
    }
    public ValidateXmlResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
    
}
