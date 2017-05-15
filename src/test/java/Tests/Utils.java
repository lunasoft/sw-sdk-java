package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asalvio on 16/02/2017.
 */
public class Utils {
    public static String dummy_token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUTQyWFhnTUxGYjdKdG8xQTZWVjFrUDNiOTVrRkhiOGk3RHladHdMaEM0cS8rcklzaUhJOGozWjN0K2h6R3gwQzF0c0g5aGNBYUt6N2srR3VoMUw3amtvPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxyQ0IvSUh1bHYxaFMxa2xmb3ZIaHlqQlpZZGRmVlpuRDBHdHNweStrZmNPcWZjWktlcm5IZVFhelhRUDBQWXpHS0JmMGhURm9OTU5KUEJLeEZMeGpnR3hpOWFoakcvaXRVb2RSSVFFSVF2OFd5UExVT3JiSFo5RmcxOGJUWTlQSnJPdzAxdU5NdVRwWlR1azRQY0FQdTg9.YA-QpZRGEYqNpQz5Un_eFwY20-JijfDKJvX6sC5z-XE";

    public static String dummy_xml_string = "<?xml version=\"1.0\" encoding=\"utf-8\"?><cfdi:Comprobante xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/ComercioExterior11 http://www.sat.gob.mx/sitio_internet/cfd/ComercioExterior11/ComercioExterior11.xsd\" xmlns:cce11=\"http://www.sat.gob.mx/ComercioExterior11\" Version=\"3.3\" Serie=\"RogueOne\" Folio=\"HNFK231\" Fecha=\"2017-05-14T11:43:24\" Sello=\"N6kw6F+m43Yt8Qery42b4HHQpM6k40Uk1SmCLSL3oAHuI0oP4i/pKNJFBQfU2Lo/7Hoq+iqKRDayq7LnimDeFJtFtTGNvtpb7cWvlMgiW+jKXWpfWY+sYaEbAgDdjAVwCNAypVSjOh76dkL4af2PfE9f3Kg3Jy6mMp1guTUcalWKwfTLkjzkF7cOLVk6ViUYNLhClaFUak0+qiU211o1fBRWmY3Sw0ofQLI5jn9DCWzgw9pDQYxm3h7tWfUpvOMlR0F6TMO1K2PvDUnnzZtzRLuCuN/UW4GUPEGBOKJPnc9z59tDVaENbEt2c7S6GepGCVYRNY6pQrL8nRaZcdxbfg==\" FormaPago=\"01\" NoCertificado=\"20001000000300022815\" Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" SubTotal=\"200.00\" Moneda=\"MXN\" TipoCambio=\"1\" Total=\"603.28\" TipoDeComprobante=\"I\" MetodoPago=\"PUE\" LugarExpedicion=\"06300\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><cfdi:Emisor Rfc=\"LAN7008173R5\" Nombre=\"CINDEMEX SA DE CV\" RegimenFiscal=\"601\" /><cfdi:Receptor Rfc=\"AAA010101AAA\" Nombre=\"Rodolfo Carranza Ramos\" UsoCFDI=\"G03\" /><cfdi:Conceptos><cfdi:Concepto ClaveProdServ=\"50211503\" NoIdentificacion=\"UT421511\" Cantidad=\"1\" ClaveUnidad=\"H87\" Unidad=\"Pieza\" Descripcion=\"Cigarros\" ValorUnitario=\"200.00\" Importe=\"200.00\"><cfdi:Impuestos><cfdi:Traslados><cfdi:Traslado Base=\"200.00\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.08\" /><cfdi:Traslado Base=\"232.00\" Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos></cfdi:Concepto></cfdi:Conceptos><cfdi:Impuestos TotalImpuestosTrasladados=\"403.28\"><cfdi:Traslados><cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"32.08\" /><cfdi:Traslado Impuesto=\"003\" TipoFactor=\"Tasa\" TasaOCuota=\"1.600000\" Importe=\"371.20\" /></cfdi:Traslados></cfdi:Impuestos><cfdi:Complemento><cce11:ComercioExterior CertificadoOrigen=\"0\" ClaveDePedimento=\"A1\" Incoterm=\"FOB\" Subdivision=\"0\" TipoCambioUSD=\"19.4493\" TipoOperacion=\"2\" TotalUSD=\"5199.05\" Version=\"1.1\"><cce11:Emisor></cce11:Emisor><cce11:Receptor NumRegIdTrib=\"889010357\"></cce11:Receptor><cce11:Mercancias><cce11:Mercancia CantidadAduana=\"1200\" FraccionArancelaria=\"98010001\" NoIdentificacion=\"A-123LFM\" UnidadAduana=\"01\" ValorDolares=\"2220.46\" ValorUnitarioAduana=\"1.85\"></cce11:Mercancia><cce11:Mercancia CantidadAduana=\"958\" FraccionArancelaria=\"94059102\" NoIdentificacion=\"A-123JKL\" UnidadAduana=\"01\" ValorDolares=\"1519.43\" ValorUnitarioAduana=\"1.59\"></cce11:Mercancia><cce11:Mercancia CantidadAduana=\"1150\" FraccionArancelaria=\"94059102\" NoIdentificacion=\"A-123WHX\" UnidadAduana=\"01\" ValorDolares=\"1459.16\" ValorUnitarioAduana=\"1.27\"></cce11:Mercancia></cce11:Mercancias></cce11:ComercioExterior></cfdi:Complemento></cfdi:Comprobante>";
    public static String remove2(String input) {
        // Descomposición canónica
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Nos quedamos únicamente con los caracteres ASCII
        Pattern pattern = Pattern.compile("\\p{ASCII}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    public static boolean isNoAlphaNumeric(String s) {
        return s.matches("[ \\w]+");
    }

    public static byte[] fileToBytes(File file) {
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();

            return bytesArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static String removeBadChars(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if (Character.isHighSurrogate(s.charAt(i))) continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static StringBuffer removeUTFCharacters(String data){
        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {
            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
        }
        m.appendTail(buf);
        return buf;
    }


    }
