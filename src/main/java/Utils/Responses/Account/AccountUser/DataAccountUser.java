package Utils.Responses.Account.AccountUser;

/**
 * Clase que representa la información de una cuenta.
 */
public class DataAccountUser {
    // Atributos que representan la información de una cuenta
    public String idUsuario;
    public String idDealer;
    public String name;
    public String taxId;
    public String username;
    public String lastPasswordChange;
    public String email;
    public String notificationEmail;
    public boolean isAdmin;
    public int profile;
    public boolean isActive;
    public String registeredDate;
    public String accessToken;
    public String phone;
    public String stamps;
    public boolean isUnlimited;

    /**
     * Constructor que inicializa una instancia de DataAccountUser con la
     * información de la cuenta.
     */

    public DataAccountUser(String idUsuario, String idDealer, String name, String taxId, String username,
            String lastPasswordChange, String email, String notificationEmail, boolean isAdmin,
            int profile, boolean isActive, String registeredDate, String accessToken,
            String phone, String stamps, boolean isUnlimited) {
        this.idUsuario = idUsuario;
        this.idDealer = idDealer;
        this.name = name;
        this.taxId = taxId;
        this.username = username;
        this.lastPasswordChange = lastPasswordChange;
        this.email = email;
        this.notificationEmail = notificationEmail;
        this.isAdmin = isAdmin;
        this.profile = profile;
        this.isActive = isActive;
        this.registeredDate = registeredDate;
        this.accessToken = accessToken;
        this.phone = phone;
        this.stamps = stamps;
        this.isUnlimited = isUnlimited;
    }
}
