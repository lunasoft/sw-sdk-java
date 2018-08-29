package Utils.Responses;

public class ValidateLrfcResponse extends IResponse{
    public String contribuyenteRFC;
    public boolean sncf;
    public boolean subcontratacion;
    
    
    public ValidateLrfcResponse(int httpStatusCode, String status, String contribuyenteRFC, boolean sncf, boolean subcontratacion, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.contribuyenteRFC = contribuyenteRFC;
        this.sncf = sncf;
        this.subcontratacion = subcontratacion;
    }
    public ValidateLrfcResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
    
}
