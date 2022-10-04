package Tests;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;
import javax.xml.transform.*;
import com.google.gson.Gson;
import sw.CadenaOriginalCfdi;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import Tests.helpers.Sign;
import java.text.SimpleDateFormat;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import org.apache.commons.codec.binary.Base64;
import org.junit.rules.TestName;

import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
* Utils
* Clase auxiliar de UT con datos comunes.
* @author  Eduardo Mares
* @version 0.0.0.2
* @since   2022-04-30
*/
public class Utils {
    public static String urlSW = "http://services.test.sw.com.mx";
    public static String urlApiSW = "http://api.test.sw.com.mx";
    public static String userSW = System.getenv("SDKTEST_USER");
    public static String passwordSW = System.getenv("SDKTEST_PASSWORD");
    public static String tokenSW = System.getenv("SDKTEST_TOKEN");
           
    /**
    * Genera un CFDI especifico y lo sella en caso de indicarse.
    * @param fileName
    * @param signed
    * @param version
    * @param isBase64
    * @return String
    */
    public String getCFDI(String fileName, boolean signed, String version, boolean isBase64){
        
        String xml = "";
        try {
            xml = new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
        }  catch (IOException e) {
            e.printStackTrace();
        }
        
        String cfdi = changeDateAndSign(xml, signed, version);

        if(isBase64){
            try {
                return encodeBase64(cfdi);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return cfdi;
    }
    
    /**
    * Genera un CFDI especifico.
    * @param fileName
    * @param isBase64
    * @return String
    */
    public String getJsonCFDI(String fileName, boolean isBase64) {        
        Gson gson = new Gson();
        String xml = "";
        try {
            xml = new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
        }  catch (IOException e) {
            e.printStackTrace();
        }
                
        Map<String, Object> data = gson.fromJson(xml, Map.class);
        if (data != null) {
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString().replace("-", "");
            data.put("Folio", randomUUIDString + "sdk-java");
            data.put("Fecha", getDateCFDI());
        }
        
        if(isBase64){
            try {
                return encodeBase64(gson.toJson(data));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return gson.toJson(data);
    }

    /**
    * Genera un CFDI Ãºnico y lo sella en caso de indicarse.
    * @param xml
    * @param signed
    * @return String
    */
    private String changeDateAndSign(String xml, boolean signed, String version) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString().replace("-", "");
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().setAttribute("Fecha", getDateCFDI());
            doc.getDocumentElement().setAttribute("Folio", randomUUIDString + "sdk-java");
            if (signed) {
                Sign sign = new Sign();
                String cadena = GenerateCadena(doc, version);
                String sello = sign.getSign(cadena,
                        Files.readAllBytes(Paths.get("src/test/resources/CertificadosDePrueba/CSD_EKU9003173C9.key")),
                        "12345678a");
                doc.getDocumentElement().setAttribute("Sello", sello);
            }
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * Obtiene la fecha actual en formato necesario para CFDI.
    * @return String
    */
    private String getDateCFDI() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return date.format(new Date());
    }

    private String GenerateCadena(Document xml, String version) throws TransformerConfigurationException, TransformerException, URISyntaxException {
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        transformer = transformerFactory.newTransformer();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(bout);
        DOMSource source = new DOMSource(xml);
        transformer.transform(source, result);

        return CadenaOriginalCfdi.getCadenaOriginal(bout.toByteArray(), version);
    }

    private String encodeBase64(String text) throws UnsupportedEncodingException {
    	byte[] bytesEncoded = Base64.encodeBase64(text.getBytes("UTF-8"));
        return new String(bytesEncoded);
    }

    public String genComercioExterior(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/ComercioExterior11.xml", true, "3.3", isBase64);
    }

    public String genComercioExteriorTimbrePrevio(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/ComercioExterior11TimbrePrevio.xml", true, "3.3", isBase64);
    }

    public String genPagos10(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/Pago10.xml", true, "3.3", isBase64);
    }

    public String genPagos10TimbrePrevio(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/Pago10TimbrePrevio.xml", true, "3.3", isBase64);
    }
    
    public String genNomina12(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/Nomina12.xml", true, "3.3", isBase64);
    }

    public String genNomina12TimbrePrevio(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/Nomina12TimbrePrevio.xml", true, "3.3", isBase64);
    }

    public String StringgenBasico(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/CFDI33.xml", true, "3.3", isBase64);
    }
    
    public String StringgenBasicoTimbrePrevio(boolean isBase64){
        return getCFDI("src/test/resources/CFDI33/CFDI33TimbrePrevio.xml", true, "3.3", isBase64);
    }
    
    public String JsonGenBasico(boolean isBase64) {
        return getJsonCFDI("src/test/resources/CFDI33/CFDI33.json", isBase64);
    }

    public static boolean isValidB64(String value){
        return Base64.isBase64(value.getBytes());
    }

    public static String getCertificadoB64(){
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/CertificadosDePrueba/CSD_EKU9003173C9.key"));
            return new String(fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    } 

    public static String getLlaveB64(){
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/CertificadosDePrueba/CSD_EKU9003173C9.key"));
            return new String(fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";        
    }

    public String getResource(String fileName){
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/Extras/" + fileName));
            return encodeBase64(new String(fileContent, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";   
    }
    public static void showTestLog(TestName testName, String status){
        System.out.println(testName.getMethodName());
        System.out.println(status + "\n");
    }
}
