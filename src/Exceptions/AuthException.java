package Exceptions;

/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthException extends Exception {
    public String HttpStatusCode;
    public String ErrorMSG;

    public String getHttpStatusCode() {
        return HttpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        HttpStatusCode = httpStatusCode;
    }

    public String getErrorMSG() {
        return ErrorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        ErrorMSG = errorMSG;
    }

    public AuthException(String httpStatusCode, String errorMSG) {
        super(errorMSG);
        HttpStatusCode = httpStatusCode;
        ErrorMSG = errorMSG;
    }
}
