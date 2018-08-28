package Utils.Responses;

public abstract class IResponse {
    public int HttpStatusCode ;
    public String Status;
    public String message = null;
    public String messageDetail = null;
    public String Expires_in = null;

    public boolean isAuth;

    public IResponse(int httpStatusCode, String status, boolean isAuth) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        this.isAuth = isAuth;
    }

    //Status error
    public IResponse(int httpStatusCode, String status, String message, String messageDetail) {
        HttpStatusCode = httpStatusCode;
        Status = status;
        this.message = message;
        this.messageDetail = messageDetail;
    }
    
    //Status success general
    public IResponse(int httpStatusCode, String status) {
        HttpStatusCode = httpStatusCode;
        Status = status;
    }

}
