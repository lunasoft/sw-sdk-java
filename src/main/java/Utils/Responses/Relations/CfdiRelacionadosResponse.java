package Utils.Responses.Relations;

import java.util.List;

import Utils.Responses.IResponse;

public class CfdiRelacionadosResponse extends IResponse{
	public String uuidConsultado;
	public String resultado;
	public List<RelacionData> uuidsRelacionadosPadres;
	public List<RelacionData> uuidsRelacionadosHijos;
	public CfdiRelacionadosResponse(int httpStatusCode, String status, String uuidConsultado, String resultado, List<RelacionData> uuidsRelacionadosPadres, List<RelacionData> uuidsRelacionadosHijos, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.uuidConsultado = uuidConsultado;
        this.resultado = resultado;
        this.uuidsRelacionadosPadres = uuidsRelacionadosPadres;
        this.uuidsRelacionadosHijos = uuidsRelacionadosHijos;
    }
    public CfdiRelacionadosResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}
