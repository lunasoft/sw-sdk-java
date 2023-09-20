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
 * 
 * @author Eduardo Mares
 * @version 0.0.0.2
 * @since 2022-04-30
 */
public class Utils {
    public static String urlSW = "http://services.test.sw.com.mx";
    public static String urlApiSW = "http://api.test.sw.com.mx";
    public static String userSW = System.getenv("SDKTEST_USER");
    public static String passwordSW = System.getenv("SDKTEST_PASSWORD");
    public static String tokenSW = System.getenv("SDKTEST_TOKEN");
    public static String b64Cer = "MIIFsDCCA5igAwIBAgIUMzAwMDEwMDAwMDA1MDAwMDM0MTYwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWxpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMjMwNTE4MTE0MzUxWhcNMjcwNTE4MTE0MzUxWjCB1zEnMCUGA1UEAxMeRVNDVUVMQSBLRU1QRVIgVVJHQVRFIFNBIERFIENWMScwJQYDVQQpEx5FU0NVRUxBIEtFTVBFUiBVUkdBVEUgU0EgREUgQ1YxJzAlBgNVBAoTHkVTQ1VFTEEgS0VNUEVSIFVSR0FURSBTQSBERSBDVjElMCMGA1UELRMcRUtVOTAwMzE3M0M5IC8gVkFEQTgwMDkyN0RKMzEeMBwGA1UEBRMVIC8gVkFEQTgwMDkyN0hTUlNSTDA1MRMwEQYDVQQLEwpTdWN1cnNhbCAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmecO6n2GS0zL025gbHGQVxznPDICoXzR2uUngz4DqxVUC/w9cE6FxSiXm2ap8Gcjg7wmcZfm85EBaxCx/0J2u5CqnhzIoGCdhBPuhWQnIh5TLgj/X6uNquwZkKChbNe9aeFirU/JbyN7Egia9oKH9KZUsodiM/pWAH00PCtoKJ9OBcSHMq8Rqa3KKoBcfkg1ZrgueffwRLws9yOcRWLb02sDOPzGIm/jEFicVYt2Hw1qdRE5xmTZ7AGG0UHs+unkGjpCVeJ+BEBn0JPLWVvDKHZAQMj6s5Bku35+d/MyATkpOPsGT/VTnsouxekDfikJD1f7A1ZpJbqDpkJnss3vQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAFaUgj5PqgvJigNMgtrdXZnbPfVBbukAbW4OGnUhNrA7SRAAfv2BSGk16PI0nBOr7qF2mItmBnjgEwk+DTv8Zr7w5qp7vleC6dIsZFNJoa6ZndrE/f7KO1CYruLXr5gwEkIyGfJ9NwyIagvHHMszzyHiSZIA850fWtbqtythpAliJ2jF35M5pNS+YTkRB+T6L/c6m00ymN3q9lT1rB03YywxrLreRSFZOSrbwWfg34EJbHfbFXpCSVYdJRfiVdvHnewN0r5fUlPtR9stQHyuqewzdkyb5jTTw02D2cUfL57vlPStBj7SEi3uOWvLrsiDnnCIxRMYJ2UA2ktDKHk+zWnsDmaeleSzonv2CHW42yXYPCvWi88oE1DJNYLNkIjua7MxAnkNZbScNw01A6zbLsZ3y8G6eEYnxSTRfwjd8EP4kdiHNJftm7Z4iRU7HOVh79/lRWB+gd171s3d/mI9kte3MRy6V8MMEMCAnMboGpaooYwgAmwclI2XZCczNWXfhaWe0ZS5PmytD/GDpXzkX0oEgY9K/uYo5V77NdZbGAjmyi8cE2B2ogvyaN2XfIInrZPgEffJ4AB7kFA2mwesdLOCh0BLD9itmCve3A1FGR4+stO2ANUoiI3w3Tv2yQSg4bjeDlJ08lXaaFCLW2peEXMXjQUk7fmpb5MNuOUTW6BE=";
	

    /**
     * Genera un CFDI especifico y lo sella en caso de indicarse.
     * 
     * @param fileName
     * @param signed
     * @param version
     * @param isBase64
     * @return String
     */
    public String getCFDI(String fileName, boolean signed, String version, boolean isBase64) {

        String xml = "";
        try {
            xml = new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cfdi = changeDateAndSign(xml, signed, version);

        if (isBase64) {
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
     * 
     * @param fileName
     * @param isBase64
     * @return String
     */
    public String getJsonCFDI(String fileName, boolean isBase64) {
        Gson gson = new Gson();
        String xml = "";
        try {
            xml = new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> data = gson.fromJson(xml, Map.class);
        if (data != null) {
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString().replace("-", "");
            data.put("Folio", randomUUIDString + "sdkjava");
            data.put("Fecha", getDateCFDI());
        }

        if (isBase64) {
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
     * 
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
            doc.getDocumentElement().setAttribute("Certificado", b64Cer);
            doc.getDocumentElement().setAttribute("NoCertificado", "30001000000500003416");
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
     * 
     * @return String
     */
    private String getDateCFDI() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1); // Restar una hora
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        String realDate = sdf.format(calendar.getTime());
        return realDate;
    }

    private String GenerateCadena(Document xml, String version)
            throws TransformerConfigurationException, TransformerException, URISyntaxException {

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

    public String genComercioExterior(boolean isBase64) {
        return getCFDI("src/test/resources/CFDI40/ComercioExterior11/CFDI40_ComercioExterior.xml", true, "4.0",
                isBase64);
    }

    public String genComercioExteriorTimbrePrevio(boolean isBase64) {
        return getCFDI("src/test/resources/Extras/ComercioExterior_Timbrado.xml", true, "4.0", isBase64);
    }

    public String genPagos20(boolean isBase64) {
        return getCFDI("src/test/resources/CFDI40/Pagos20/CFDI40_Pago.xml", true, "4.0", isBase64);
    }

    public String genPagos20TimbrePrevio(boolean isBase64) {
        return getCFDI("src/test/resources/Extras/Pagos20_Timbrado.xml", true, "4.0", isBase64);
    }

    public String genNomina12(boolean isBase64) {
        return getCFDI("src/test/resources/CFDI40/Nomina12/CFDI40_Nomina.xml", true, "4.0", isBase64);
    }

    public String genNomina12TimbrePrevio(boolean isBase64) {
        return getCFDI("src/test/resources/Extras/Nomina12_Timbrado.xml", true, "4.0", isBase64);
    }

    public String StringgenBasico(boolean isBase64) {
        return getCFDI("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso.xml", true, "4.0", isBase64);
    }

    public String StringgenBasicoTimbrePrevio(boolean isBase64) {
        return getCFDI("src/test/resources/Extras/CFDI40_Ingreso_Timbrado.xml", true, "4.0", isBase64);
    }

    public String JsonGenBasico(boolean isBase64) {
        return getJsonCFDI("src/test/resources/CFDI40/CFDI40/CFDI40_Ingreso.json", isBase64);
    }

    public static boolean isValidB64(String value) {
        return Base64.isBase64(value.getBytes());
    }

    public static String getCertificadoB64() {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/CertificadosDePrueba/CSD_EKU9003173C9.cer"));
            return new String(fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getLlaveB64() {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/CertificadosDePrueba/CSD_EKU9003173C9.key"));
            return new String(fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getResource(String fileName) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get("src/test/resources/Extras/" + fileName));
            return encodeBase64(new String(fileContent, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void showTestLog(TestName testName, String status) {
        System.out.println(testName.getMethodName());
        System.out.println(status + "\n");
    }
}
