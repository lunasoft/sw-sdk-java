package Utils;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

/**
 * Created by asalvio on 09/02/2017.
 */
public class Constants {
    public static String BASE_PATH = "http://swservicestest.azurewebsites.net";
    public static String AUTH_PATH = "/security/authenticate";
    public static String STAMP_PATH = "/cfdi33/stamp/";
    public static String auth_soap_envelope(String user, String pwd){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:suf=\"http://sufacturacion.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <suf:AutenticarBasico>\n" +
                "         <!--Optional:-->\n" +
                "         <suf:usuario>"+user+"</suf:usuario>\n" +
                "         <!--Optional:-->\n" +
                "         <suf:password>"+pwd+"</suf:password>\n" +
                "      </suf:AutenticarBasico>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }



}
