package Utils.Responses;

//@author Lupita Alvarado

public class BalanceAcctResponse extends IResponse{
    public BalanceAcctResponse(int httpStatusCode, String msg, String status) {
        super(httpStatusCode, msg, status, 1);
    }
}
