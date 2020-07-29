package Utils.Responses.Validate;

import Utils.Responses.IResponse;

public class ValidateLcoResponse extends IResponse{
    public String noCertificado;
    public String rfc;
    public String validezObligaciones;
    public String estatusCertificado;
    public String fechaInicio;
    public String fechaFinal;
    
    public ValidateLcoResponse(int httpStatusCode, String status,  String noCertificado, String rfc, String validezObligaciones, String estatusCertificado, String fechaInicio, String fechaFinal, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.noCertificado = noCertificado;
        this.rfc = rfc;
        this.validezObligaciones = validezObligaciones;
        this.estatusCertificado = estatusCertificado;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }
    public ValidateLcoResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
    
}
