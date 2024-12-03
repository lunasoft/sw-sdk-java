package Utils.Requests.Account.AccountUser;

import java.util.UUID;

import Utils.Constants;
import Utils.Requests.IRequest;

/**
 * La clase AccountUserOptionsRequest representa las opciones de solicitud para
 * operaciones relacionadas con usuarios de cuentas.
 * Extiende la clase IRequest para incluir información común de solicitud, 
 * como el token de autenticación, la URI de la API y la configuración de proxy.
 */
public class AccountUserOptionsRequest extends IRequest {
    private String name; // Nombre del usuario
    private String taxId; // RFC o identificación fiscal
    private String email; // Correo electrónico del usuario
    private int stamps; // Timbres asignados al usuario
    private boolean unlimited; // Indicador si el usuario tiene timbres ilimitados
    private String passwordUser; // Contraseña del usuario
    private String notificationEmail; // Correo para notificaciones
    private String phone; // Teléfono del usuario
    private UUID idUsuario; // Identificador único del usuario

    /**
     * Constructor privado para crear una instancia de AccountUserOptionsRequest.
     * Este constructor no puede ser invocado directamente fuera de la clase, ya que
     * está diseñado para ser utilizado por los métodos estáticos.
     *
     * @param token       Token de autenticación
     * @param URIAPI      URI base de la API
     * @param proxyHost   Host del proxy (si aplica)
     * @param proxyPort   Puerto del proxy (si aplica)
     */
    private AccountUserOptionsRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI, proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de creación de usuario.
     * Establece los parámetros necesarios para registrar un nuevo usuario en el sistema.
     *
     * @param token             Token de autenticación
     * @param URIAPI            URI base de la API
     * @param email             Correo electrónico del usuario
     * @param passwordUser      Contraseña del usuario
     * @param name              Nombre del usuario
     * @param taxId             RFC o identificación fiscal
     * @param stamps            Timbres asignados
     * @param phone             Teléfono del usuario
     * @param unlimited         Indicador si el usuario tiene timbres ilimitados
     * @param notificationEmail Correo para notificaciones
     * @param proxyHost         Host del proxy
     * @param proxyPort         Puerto del proxy
     * @return                  Instancia configurada de AccountUserOptionsRequest
     */
    public static AccountUserOptionsRequest crearUsuarioRequest(String token, String URIAPI, String email,
            String passwordUser, String name, String taxId, int stamps, String phone,
            boolean unlimited, String notificationEmail, String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOSV2, proxyHost, proxyPort)
                .crearUsuario(email, passwordUser, name, taxId, stamps, phone, unlimited, notificationEmail);
    }

    /**
     * Método estático para crear una solicitud de actualización de usuario.
     * Se utiliza para actualizar la información de un usuario existente.
     *
     * @param token             Token de autenticación
     * @param URIAPI            URI base de la API
     * @param idUsuario         Identificador único del usuario
     * @param name              Nuevo nombre del usuario
     * @param taxId             Nuevo RFC o identificación fiscal
     * @param unlimited         Indicador si el usuario tiene timbres ilimitados
     * @param phone             Nuevo teléfono del usuario
     * @param notificationEmail Nuevo correo para notificaciones
     * @param proxyHost         Host del proxy
     * @param proxyPort         Puerto del proxy
     * @return                  Instancia configurada de AccountUserOptionsRequest
     */
    public static AccountUserOptionsRequest actualizarUsuarioRequest(String token, String URIAPI, UUID idUsuario,
            String name, String taxId, boolean unlimited, String phone, String notificationEmail, String proxyHost,
            int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOSV2 + "/" + idUsuario.toString(),
                proxyHost,
                proxyPort)
                .actualizarUsuario(idUsuario, name, taxId, unlimited, phone, notificationEmail);
    }

    /**
     * Método estático para crear una solicitud de obtención y eliminación de usuario por ID.
     * Esta solicitud permite consultar o eliminar un usuario identificado por su UUID.
     *
     * @param token      Token de autenticación
     * @param URIAPI     URI base de la API
     * @param idUsuario  Identificador único del usuario
     * @param proxyHost  Host del proxy
     * @param proxyPort  Puerto del proxy
     * @return           Instancia configurada de AccountUserOptionsRequest
     */
    public static AccountUserOptionsRequest usuarioIdRequest(String token, String URIAPI, UUID idUsuario,
            String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOSV2 + "/" + idUsuario.toString(),
                proxyHost,
                proxyPort);
    }

    /**
     * Método estático para crear una solicitud de obtención de todos los usuarios.
     *
     * @param token      Token de autenticación
     * @param URIAPI     URI base de la API
     * @param proxyHost  Host del proxy
     * @param proxyPort  Puerto del proxy
     * @return           Instancia configurada de AccountUserOptionsRequest
     */
    public static AccountUserOptionsRequest obtenerUsuariosRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        return new AccountUserOptionsRequest(token,
                URIAPI + Constants.USUARIOSV2, proxyHost, proxyPort);
    }

    /**
     * Método estático para crear una solicitud de obtención de usuario por token.
     * Este método permite obtener la información del usuario autenticado mediante su token.
     *
     * @param token      Token de autenticación
     * @param URIAPI     URI base de la API
     * @param proxyHost  Host del proxy
     * @param proxyPort  Puerto del proxy
     * @return           Instancia configurada de AccountUserOptionsRequest
     */
    public static AccountUserOptionsRequest obtenerUsuarioPorTokenRequest(String token, String URIAPI, String proxyHost,
            int proxyPort) {
        return new AccountUserOptionsRequest(token, URIAPI + Constants.USUARIOSV2, proxyHost, proxyPort);
    }

    // Métodos privados para configurar los parámetros específicos de la solicitud.

    /**
     * Configura los parámetros para una solicitud de creación de usuario.
     */
    private AccountUserOptionsRequest crearUsuario(String email, String passwordUser, String name, String taxId,
            int stamps, String phone, boolean unlimited, String notificationEmail) {
        this.email = email;
        this.passwordUser = passwordUser;
        this.name = name;
        this.taxId = taxId;
        this.stamps = stamps;
        this.phone = phone;
        this.unlimited = unlimited;
        this.notificationEmail = notificationEmail;
        return this;
    }

    /**
     * Configura los parámetros para una solicitud de actualización de usuario.
     */
    private AccountUserOptionsRequest actualizarUsuario(UUID idUsuario, String name, String taxId, boolean unlimited,
            String phone, String notificationEmail) {
        this.idUsuario = idUsuario;
        this.name = name;
        this.taxId = taxId;
        this.unlimited = unlimited;
        this.notificationEmail = notificationEmail;
        this.phone = phone;
        return this;
    }

    // Métodos de acceso a los atributos de la solicitud.

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return passwordUser;
    }

    public String getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }

    public int getStamps() {
        return stamps;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public String getPhone() {
        return phone;
    }
}
