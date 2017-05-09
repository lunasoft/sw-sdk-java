package Utils.Responses;

/**
 * Created by asalvio on 09/05/2017.
 */
public class SuccessV4Response extends IResponse {
    public SuccessV4Response(int httpStatusCode, String status, String cfdi, String cadenaOriginalSAT, String noCertificadoSAT, String noCertificadoCFDI, String uuid, String selloSAT, String selloCFDI, String fechaTimbrado, String qrCode) {
        super(httpStatusCode, status, cfdi, cadenaOriginalSAT, noCertificadoSAT, noCertificadoCFDI, uuid, selloSAT, selloCFDI, fechaTimbrado, qrCode);
    }
}
