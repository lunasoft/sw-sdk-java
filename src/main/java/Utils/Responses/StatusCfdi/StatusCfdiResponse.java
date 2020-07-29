package Utils.Responses.StatusCfdi;

import Utils.Responses.IResponse;

public class StatusCfdiResponse extends IResponse {
    
	public String codigoEstatus;
    public String estado;
    public String esCancelable;
    public String estatusCancelacion;
    
    public StatusCfdiResponse(int httpStatusCode, String status, String codigoEstatus, String estado, String esCancelable, String estatusCancelacion, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.codigoEstatus = codigoEstatus;
        this.estado = estado;
        this.esCancelable = esCancelable;
        this.estatusCancelacion = estatusCancelacion;
    }
    public StatusCfdiResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}
