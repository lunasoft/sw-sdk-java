package Utils;

public class Constants {
    public static String BASE_PATH = "http://services.test.sw.com.mx";
    public static String AUTH_PATH = "/security/authenticate";
    public static String STAMP_PATH = "/cfdi33/stamp/";
    public static String CANCELATION_CSD_PATH = "/cfdi33/cancel/csd";
    public static String CANCELATION_XML_PATH = "/cfdi33/cancel/xml";
    public static String BALANCE_ACCOUNT_PATH = "/account/balance/";
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
