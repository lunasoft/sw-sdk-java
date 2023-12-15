package Utils.Requests.Resend;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import Exceptions.ValidationException;
import Utils.Constants;
import Utils.Requests.IRequest;

public class ResendOptionsRequest extends IRequest {

    private UUID uuid;
    private String to;

    // Constructor privado utilizado por el método estático resendEmail
    private ResendOptionsRequest(String token, String URI, String proxyHost, int proxyPort) {
        super(token, URI, proxyHost, proxyPort);

    }

    // Método estático para crear una instancia de ResendOptionsRequest y realizar l
    //  validación del correo
    public static ResendOptionsRequest resendEmail(String token, String URIAPI, UUID uuid,
            String correos, String proxyHost, int proxyPort) {
        return new ResendOptionsRequest(token, URIAPI + Constants.RESEND, proxyHost, proxyPort)
                .resendEmail(uuid, correos);
    }

    // Método privado para realizar la validación del correo y asignar los valores c
    // rrespondientes
    private ResendOptionsRequest resendEmail(UUID uuid, String correos) throws ValidationException {
        if (correos == null || correos.isEmpty()) {
            throw new ValidationException("Cadena vacía.", "Las direcciones de correo no pueden ser nulas o vacías.");
        }
        if (correos.contains(" ")) {
            throw new ValidationException("No debe haber espacios entre los correos.",
                    "Recuerda separar con comas las cuentas de correos sin espacios entre ellos.");
        }
        List<String> correosList = Arrays.asList(correos.split(","));
        if (correosList.size() > 5) {
            throw new ValidationException("Excediste el máximo de correos, sólo se pueden agregar 5 destinatarios.",
                    "Intenta registrando menos direcciones de correos");
        }
        this.to = correos;
        this.uuid = uuid;

        return this;
    }
//Métodos getters
    public String getUuid() {
        return uuid.toString();
    }

    public String getEmails() {
        return to;
    }

}
