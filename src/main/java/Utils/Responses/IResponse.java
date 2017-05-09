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



}
