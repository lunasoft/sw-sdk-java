package Utils.Responses;

public abstract class IResponse {
    public int HttpStatusCode ;
    public String Data = null;
    public String Status;
    public String message = null;
    public String messageDetail = null;
    public String tfd = null;
    public String cfdi = null;
    public String token;
    public boolean isAuth;
    public String cadenaOriginalSAT;
    public String noCertificadoSAT;
    public String noCertificadoCFDI;
    public String uuid;
    public String selloSAT;
    public String selloCFDI;
    public String fechaTimbrado;
    public String qrCode;

    //V4 Response
    public IResponse(int httpStatusCode, String status, String cfdi, String cadenaOriginalSAT, String noCertificadoSAT, String noCertificadoCFDI, String uuid, String selloSAT, String selloCFDI, String fechaTimbrado, String qrCode) {
        HttpStatusCode = httpStatusCode;
        Status = status;
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

    public IResponse(int httpStatusCode, String status, String token, boolean isAuth) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        this.token = token;
        this.isAuth = isAuth;
    }

    //Status error
    public IResponse(int httpStatusCode, String status, String message, String messageDetail) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        this.message = message;
        this.messageDetail = messageDetail;
    }



    //Status success V1
    public IResponse(int httpStatusCode, String tfd, String status) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        tfd = tfd;

    }
    //Status success V2, V3, V4
    public IResponse(int httpStatusCode, String data, String status, String tfd, String cfdi) {
        HttpStatusCode = httpStatusCode;
        Data = data;
        Status = status;
        this.tfd = tfd;
        this.cfdi = cfdi;
    }
    
    public IResponse(int httpStatusCode, String data, String status, int cancelation) {
        HttpStatusCode = httpStatusCode;
        Data = data;
        Status = status;
    }

}
