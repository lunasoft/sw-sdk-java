package Exceptions;

public class GeneralException extends Exception {
    public int HttpStatusCode;
    public String ErrorMSG;

    public int getHttpStatusCode() {
        return HttpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        HttpStatusCode = httpStatusCode;
    }

    public String getErrorMSG() {
        return ErrorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        ErrorMSG = errorMSG;
    }

    public GeneralException(int httpStatusCode, String errorMSG) {
        super(httpStatusCode+" ----> "+errorMSG);
        HttpStatusCode = httpStatusCode;
        ErrorMSG = errorMSG;
    }
}
