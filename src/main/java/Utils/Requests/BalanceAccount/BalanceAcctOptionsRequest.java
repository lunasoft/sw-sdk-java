package Utils.Requests.BalanceAccount;

import java.util.UUID;
import Utils.Constants;
import Utils.Requests.IRequest;
import Utils.Helpers.EnumBalanceStamp.AccountBalanceAction;

/**
 * La clase BalanceAcctOptionsRequest representa las opciones de solicitud para
 * operaciones relacionadas con el saldo de cuentas.
 * Extiende la clase IRequest para incluir información común de solicitud.
 */

public class BalanceAcctOptionsRequest extends IRequest {
    private UUID idUser;
    private int stamps;
    private String comment;
    private AccountBalanceAction action;

    /**
     * Constructor privado para crear una instancia de BalanceAcctOptionsRequest.
     * Utilizado por los métodos estáticos de creación de solicitudes.
     */
    private BalanceAcctOptionsRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de obtención de saldo de cuenta.
     */
    public static BalanceAcctOptionsRequest sendRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        return new BalanceAcctOptionsRequest(token, URIAPI + Constants.BALANCE_ACCOUNTV2_MANAGEMENT_PATH + "users/balance",
                proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de movimiento de saldo de cuenta.
     */
    public static BalanceAcctOptionsRequest balanceAccountStamp(String token, String URIAPI, UUID idUser, int stamps,
            String comment, AccountBalanceAction action, String proxyHost, int proxyPort) {
        return new BalanceAcctOptionsRequest(token,
                URIAPI + Constants.BALANCE_ACCOUNTV2_MANAGEMENT_PATH + "dealers/users" + "/" + idUser +  "/stamps",
                proxyHost, proxyPort);
    }

    /**
     * Métodos para obtener información de la solicitud.
     */
    public UUID getIdUser() {
        return idUser;
    }

    public int getStamps() {
        return stamps;
    }

    public String getComment() {
        return comment;
    }

    public AccountBalanceAction getAction() {
        return action;
    }
}
