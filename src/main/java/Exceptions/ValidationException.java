package Exceptions;

public class ValidationException extends RuntimeException {

    private String msg;
    private String msgDetail;

    public ValidationException(String msg, String msgDetail) {
        super(msg);
        this.msg = msg;
        this.msgDetail = msgDetail;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgDetail() {
        return msgDetail;
    }
}
