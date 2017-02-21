package Exceptions;

public class AuthException extends Exception {
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

    public AuthException(int httpStatusCode, String errorMSG) {
        super(errorMSG);
        HttpStatusCode = httpStatusCode;
        ErrorMSG = errorMSG;
    }
}
