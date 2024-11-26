package Utils.Responses.BalanceAccount;

import Utils.Responses.IResponse;

public class BalanceAcctResponse extends IResponse {
    // Datos principales
    public String idUserBalance;
    public String idUser;
    public int stampsBalance;
    public int stampsUsed;
    public int stampsAssigned;
    public boolean isUnlimited;
    public String expirationDate;

    // Subestructura de la transacción
    public LastTransaction lastTransaction;

    // Constructor completo
    public BalanceAcctResponse(int httpStatusCode, String status, String idUserBalance, String idUser,
                                int stampsBalance, int stampsUsed, int stampsAssigned, boolean isUnlimited,
                                String expirationDate, LastTransaction lastTransaction,
                                String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
        this.idUserBalance = idUserBalance;
        this.idUser = idUser;
        this.stampsBalance = stampsBalance;
        this.stampsUsed = stampsUsed;
        this.stampsAssigned = stampsAssigned;
        this.isUnlimited = isUnlimited;
        this.expirationDate = expirationDate;
        this.lastTransaction = lastTransaction;
    }

    // Constructor sin los datos de la transacción
    public BalanceAcctResponse(int httpStatusCode, String status, String msg, String msgDetail) {
        super(httpStatusCode, status, msg, msgDetail);
    }

    // Clase interna para la transacción
    public static class LastTransaction {
        public int folio;
        public String idUser;
        public String idUserReceiver;
        public String nameReceiver;
        public Integer stampsIn;
        public Integer stampsOut;  // Usamos Integer para poder representar 'null' en el JSON
        public Integer stampsCurrent;
        public String comment;
        public String date;
        public boolean isEmailSent;

        // Constructor de la transacción
        public LastTransaction(int folio, String idUser, String idUserReceiver, String nameReceiver,
                               int stampsIn, Integer stampsOut, int stampsCurrent, String comment, String date,
                               boolean isEmailSent) {
            this.folio = folio;
            this.idUser = idUser;
            this.idUserReceiver = idUserReceiver;
            this.nameReceiver = nameReceiver;
            this.stampsIn = stampsIn;
            this.stampsOut = stampsOut;
            this.stampsCurrent = stampsCurrent;
            this.comment = comment;
            this.date = date;
            this.isEmailSent = isEmailSent;
        }
    }
}
