package Utils.Responses.Account.AccountUser;

/**
 * Clase que representa la información de una cuenta.
 */
public class DataAccountUser {
    // Atributos que representan la información de una cuenta
    public String email;
    public String password;
    public String name;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String username;
    public String fechaUltimoPassword;
    public String telefono;
    public boolean administrador;
    public String profileValue;
    public String idUsuario;
    public String idCliente;
    public String stamps;
    public boolean unlimited;
    public int profile;
    public boolean activo;
    public String registeredDate;
    public boolean eliminado;
    public String tokenAccess;
    public String tokenAccessHash;

    /**
     * Constructor que inicializa una instancia de DataAccountUser con la información de la cuenta.
     */

    public DataAccountUser(String email, String password, String name, String apellidoPaterno,
            String apellidoMaterno, String username, String fechaUltimoPassword, String telefono,
            boolean administrador, String profileValue, String idUsuario, String idCliente, String stamps,
            boolean unlimited, int profile, boolean activo, String registeredDate, boolean eliminado,
            String tokenAccess, String tokenAccessHash) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.username = username;
        this.fechaUltimoPassword = fechaUltimoPassword;
        this.telefono = telefono;
        this.administrador = administrador;
        this.profileValue = profileValue;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.stamps = stamps;
        this.unlimited = unlimited;
        this.profile = profile;
        this.activo = activo;
        this.registeredDate = registeredDate;
        this.eliminado = eliminado;
        this.tokenAccess = tokenAccess;
        this.tokenAccessHash = tokenAccessHash;
    }
}
