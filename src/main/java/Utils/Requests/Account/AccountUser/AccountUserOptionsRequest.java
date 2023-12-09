package Utils.Requests.Account.AccountUser;

import java.util.UUID;


import Utils.Constants;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Requests.IRequest;

public class AccountUserOptionsRequest extends IRequest {
    private String email;
    private String passwordUser;
    private String name;
    private String rfc;
    private int stamps;
    AccountUserProfiles profile;
    private boolean unlimited;
    private boolean active;

    // CREAR USUARIO
    public AccountUserOptionsRequest(String token, String URIAPI, String email, String passwordUser, String name,
            String rfc, int stamps, AccountUserProfiles profile, boolean unlimited, boolean active, String proxyHost,
            int proxyPort) {
        super(token, URIAPI + Constants.USUARIOS, proxyHost, proxyPort);
        this.email = email;
        this.passwordUser = passwordUser;
        this.name = name;
        this.rfc = rfc;
        this.stamps = stamps;
        this.profile = profile;
        this.unlimited = unlimited;
        this.active = active;
    }

    // ACTUALIZAR USUARIO
    public AccountUserOptionsRequest(String token, String URIAPI, UUID idUsuario, String name, String rfc,
            boolean unlimited, boolean active, String proxyHost,
            int proxyPort) {
        super(token, URIAPI + Constants.USUARIOS_ID + idUsuario.toString(), proxyHost, proxyPort);
        this.name = name;
        this.rfc = rfc;
        this.unlimited = unlimited;
        this.active = active;
    }

    // ELIMINAR USUARIO Y OBTENER USUARIO POR ID
    public AccountUserOptionsRequest(String token, String URIAPI, UUID idUsuario, String type,
            String proxyHost,
            int proxyPort) {
        super(token, URIAPI + Constants.USUARIOS_ID + idUsuario.toString(), proxyHost, proxyPort);
    }
    
    //LISTA DE TODOS LOS USUARIOS Y PARA OBTENER USUARIO POR TOKEN
    public AccountUserOptionsRequest(String token, String URIAPI, String proxyHost, int proxyPort) {
        super(token, URIAPI + Constants.USUARIOS, proxyHost, proxyPort);
    }
    //

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