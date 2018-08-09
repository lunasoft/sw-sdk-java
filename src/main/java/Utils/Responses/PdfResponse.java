package Utils.Responses;

public class PdfResponse extends IResponse{
    public String contentB64;
    public int contentSizeBytes;
    public String uuid;
    public String serie;
    public String folio;
    public String stampDate;
    public String issuedDate;
    public String rfcIssuer;
    public String rfcReceptor;
    public String total;
    public PdfResponse(int httpStatusCode, String status, String contentB64, int contentSizeBytes, String uuid, String serie, String folio, String stampDate, String issuedDate, String rfcIssuer,String rfcReceptor, String total, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.contentB64 = contentB64;
        this.contentSizeBytes = contentSizeBytes;
        this.uuid = uuid;
        this.serie = serie;
        this.folio = folio;
        this.stampDate = stampDate;
        this.issuedDate = issuedDate;
        this.rfcIssuer = rfcIssuer;
        this.rfcReceptor = rfcReceptor;
        this.total = total;
    }
    public PdfResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }
}
