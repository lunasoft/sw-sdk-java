package Utils.Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.GeneralException;

public class Validations {

    public static String validateEmails(String[] emails) throws GeneralException {

        if (emails.length > 5 || emails.length <= 0 || validateFormat(emails) != true) {
            throw new GeneralException(400,
                    "El listado contiene más de 5 correos, está vacía o el formato es incorrecto.");
        } else {
            StringBuilder result = new StringBuilder();
            // Iterar sobre los elementos, aplicar trim y concatenar con ","
            for (String str : emails) {
                result.append(str.trim()).append(",");
            }
            // Eliminar la última coma si la cadena no está vacía
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            return result.toString();
        }
    }

    public static boolean validateFormat(String[] emails) {
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regx);

        for (String email : emails) {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public static void validateCustomId(String customId) throws GeneralException {

        if (customId.length() > 100) {
            throw new GeneralException(400, "El CustomId no es válido o es mayor a 100 caracteres.");
        } else if (customId.trim().equals("")) {
            throw new GeneralException(400, "El CustomId viene vacío.");
        }
    }

}
