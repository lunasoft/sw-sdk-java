package Utils.Responses;

/**
 * Created by asalvio on 09/05/2017.
 */
public class SuccessV4Response extends IResponse {
    public String cadenaOriginalSAT;
    public String noCertificadoSAT;
    public String noCertificadoCFDI;
    public String uuid;
    public String selloSAT;
    public String selloCFDI;
    public String fechaTimbrado;
    public String qrCode;
    public String cfdi;

    public SuccessV4Response(int httpStatusCode, String status, String cfdi, String cadenaOriginalSAT, String noCertificadoSAT, String noCertificadoCFDI, String uuid, String selloSAT, String selloCFDI, String fechaTimbrado, String qrCode, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.cfdi = cfdi;
        this.cadenaOriginalSAT = cadenaOriginalSAT;
        this.noCertificadoSAT = noCertificadoSAT;
        this.noCertificadoCFDI = noCertificadoCFDI;
        this.uuid = uuid;
        this.selloSAT = selloSAT;
        this.selloCFDI = selloCFDI;
        this.fechaTimbrado = fechaTimbrado;
        this.qrCode = qrCode;

    }
}
