package Utils.Requests.Account.AccountUser;

import java.util.UUID;

import Utils.Constants;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Requests.IRequest;

/**
 * La clase AccountUserOptionsRequest representa las opciones de solicitud para
 * operaciones relacionadas con usuarios de cuentas.
 * Extiende la clase IRequest para incluir información común de solicitud.
 */
public class AccountUserOptionsRequest extends IRequest {
    private String email;
    private String passwordUser;
    private String name;
    private String rfc;
    private int stamps;
    private AccountUserProfiles profile;
    private boolean unlimited;
    private boolean active;

    /**
     * Constructor privado para crear una instancia de AccountUserOptionsRequest.
     * Utilizado por los métodos estáticos de creación de solicitudes.
     */
    private AccountUserOptionsRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de creación de usuario.
     */
    public static AccountUserOptionsRequest crearUsuarioRequest(String token, String URIAPI, String email,
            String passwordUser, String name, String rfc, int stamps, AccountUserProfiles profile,
            boolean unlimited, boolean active, String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOS, proxyHost, proxyPort)
                .crearUsuario(email, passwordUser, name, rfc, stamps, profile, unlimited, active);
    }

    /**
     * Método estático para crear una solicitud de actualización de usuario.
     */
    public static AccountUserOptionsRequest actualizarUsuarioRequest(String token, String URIAPI, UUID idUsuario,
            String name, String rfc, boolean unlimited, boolean active, String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOS_ID + idUsuario.toString(), proxyHost,
                proxyPort)
                .actualizarUsuario(idUsuario, name, rfc, unlimited, active);
    }

    /**
     * Método estático para crear una solicitud de obtención y eliminación de us
     * ario por ID.
     */
    public static AccountUserOptionsRequest usuarioIdRequest(String token, String URIAPI, UUID idUsuario,
            String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOS_ID + idUsuario.toString(), proxyHost,
                proxyPort);
    }

    /**
     * Método estático para crear una solicitud paginada de obtención de todos lo
     *  usuarios.
     */
    public static AccountUserOptionsRequest obtenerUsuariosRequest(String token, String URIAPI, int page,
            int pageSize, String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token,
                URIAPI + Constants.USUARIOS + "?page=" + page + "&pageSize=" + pageSize, proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de obtención de usuario por to
     * en.
     */
    public static AccountUserOptionsRequest obtenerUsuarioPorTokenRequest(String token, String URIAPI, String proxyHost,
            int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOS, proxyHost, proxyPort);
    }

    // Métodos privados para configurar los parámetros específicos de la so
    // icitud.

    private AccountUserOptionsRequest crearUsuario(String email, String passwordUser, String name, String rfc,
            int stamps, AccountUserProfiles profile, boolean unlimited, boolean active) {
        this.email = email;
        this.passwordUser = passwordUser;
        this.name = name;
        this.rfc = rfc;
        this.stamps = stamps;
        this.profile = profile;
        this.unlimited = unlimited;
        this.active = active;
        return this;
    }

    private AccountUserOptionsRequest actualizarUsuario(UUID idUsuario, String name, String rfc, boolean unlimited,
            boolean active) {
        this.name = name;
        this.rfc = rfc;
        this.unlimited = unlimited;
        this.active = active;
        return this;
    }

    // Métodos de acceso a los atributos de la solicitud.

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return passwordUser;
    }

    public String getName() {
        return name;
    }

    public String getRfc() {
        return rfc;
    }

    public int getStamps() {
        return stamps;
    }

    public AccountUserProfiles getProfile() {
        return profile;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public boolean isActive() {
        return active;
    }
}