package Tests.helpers;

import java.io.*;
import java.security.Signature;
import org.apache.commons.ssl.PKCS8Key;
import javax.xml.bind.DatatypeConverter;

/**
 * Sign Está clase permite realizar sellado y transformacion de CFDI.
 * Para ello solicita los recursos necesarios.
 * @author Eduardo Mares
 * @version 0.0.0.2
 * @since 2022-04-30
 */
public class Sign {
    /**
     * Este método calcula el sello digital del CFDI utilizando la cadena original,
     * llave privada y la contraseña de la misma.
     * @param cadena cadena original.
     * @param privateKey llave privada en bytes.
     * @param passwordPrivateKey password de llave privada.
     * @return String
     */
    public String getSign(String cadena, byte[] privateKey, String passwordPrivateKey) {
        try {
            PKCS8Key pkcs8 = new PKCS8Key(privateKey, passwordPrivateKey.toCharArray());
            java.security.PrivateKey pk = pkcs8.getPrivateKey();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(pk);
            signature.update(cadena.getBytes("UTF-8"));
            return DatatypeConverter.printBase64Binary(signature.sign());
        }catch (Exception e){            
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        }
    }
}
