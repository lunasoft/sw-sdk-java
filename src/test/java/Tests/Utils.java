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

    public static String dummy_xml_string = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\t<cfdi:Comprobante Certificado=\"MIIEYTCCA0mgAwIBAgIUMjAwMDEwMDAwMDAyMDAwMDE0MjgwDQYJKoZIhvcNAQEF BQAwggFcMRowGAYDVQQDDBFBLkMuIDIgZGUgcHJ1ZWJhczEvMC0GA1UECgwmU2Vy dmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0Fk bWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkw JwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UE CQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAw MQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UE BwwJQ295b2Fjw6FuMTQwMgYJKoZIhvcNAQkCDCVSZXNwb25zYWJsZTogQXJhY2Vs aSBHYW5kYXJhIEJhdXRpc3RhMB4XDTEzMDUwNzE2MDEyOVoXDTE3MDUwNzE2MDEy OVowgdsxKTAnBgNVBAMTIEFDQ0VNIFNFUlZJQ0lPUyBFTVBSRVNBUklBTEVTIFND MSkwJwYDVQQpEyBBQ0NFTSBTRVJWSUNJT1MgRU1QUkVTQVJJQUxFUyBTQzEpMCcG A1UEChMgQUNDRU0gU0VSVklDSU9TIEVNUFJFU0FSSUFMRVMgU0MxJTAjBgNVBC0T HEFBQTAxMDEwMUFBQSAvIEhFR1Q3NjEwMDM0UzIxHjAcBgNVBAUTFSAvIEhFR1Q3 NjEwMDNNREZOU1IwODERMA8GA1UECxMIcHJvZHVjdG8wgZ8wDQYJKoZIhvcNAQEB BQADgY0AMIGJAoGBAKS/beUVy6E3aODaNuLd2S3PXaQre0tGxmYTeUxa55x2t/79 19ttgOpKF6hPF5KvlYh4ztqQqP4yEV+HjH7yy/2d/+e7t+J61jTrbdLqT3WD0+s5 fCL6JOrF4hqy//EGdfvYftdGRNrZH+dAjWWml2S/hrN9aUxraS5qqO1b7btlAgMB AAGjHTAbMAwGA1UdEwEB/wQCMAAwCwYDVR0PBAQDAgbAMA0GCSqGSIb3DQEBBQUA A4IBAQACPXAWZX2DuKiZVv35RS1WFKgT2ubUO9C+byfZapV6ZzYNOiA4KmpkqHU/ bkZHqKjR+R59hoYhVdn+ClUIliZf2ChHh8s0a0vBRNJ3IHfA1akWdzocYZLXjz3m 0Er31BY+uS3qWUtPsONGVDyZL6IUBBUlFoecQhP9AO39er8zIbeU2b0MMBJxCt4v bDKFvT9i3V0Puoo+kmmkf15D2rBGR+drd8H8Yg8TDGFKf2zKmRsgT7nIeou6WpfY p570WIvLJQY+fsMp334D05Up5ykYSAxUGa30RdUzA4rxN5hT+W9whWVGD88TD33N w55uNRUcRO3ZUVHmdWRG+GjhlfsD\" Descuento=\"0.1\" Fecha=\"2017-05-02T11:05:12\" FormaPago=\"01\" LugarExpedicion=\"45100\" MetodoPago=\"PUE\" Moneda=\"MXN\" NoCertificado=\"20001000000200001428\" Sello=\"mXKUkG4xWUFXY4bXtpeTWu/gliHD0NGiPSbjbFMMWW0QJk4NLDBmTHh9QNx6krWAzAjR+SlDwLfQiK8Hlr7EMRfKH6A91mnDVAOVSKu2ZzerJ/LD8W3eqTdaBqUlCkOHeJnJz1gQx1LP08kFUOWO9OOWucImV3zJS1wLnjQoFQI=\" SubTotal=\"1\" TipoCambio=\"1\" TipoDeComprobante=\"I\" Total=\"1.06\" Version=\"3.3\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd \">\t<cfdi:Emisor Nombre=\"ACCEM SERVICIOS EMPRESARIALES SC\" RegimenFiscal=\"601\" Rfc=\"AAA010101AAA\"/>\t<cfdi:Receptor Nombre=\"cliente al publico\" Rfc=\"XEXX010101000\" UsoCFDI=\"P01\"/>\t<cfdi:Conceptos>\t<cfdi:Concepto Cantidad=\"1\" ClaveProdServ=\"10151701\" ClaveUnidad=\"KGM\" Descripcion=\"arroz\" Descuento=\"0.1\" Importe=\"1\" Unidad=\"kilos\" ValorUnitario=\"1\">\t<cfdi:Impuestos>\t<cfdi:Traslados>\t<cfdi:Traslado Base=\"1\" Importe=\"0.16\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"/>\t</cfdi:Traslados>\t</cfdi:Impuestos>\t</cfdi:Concepto>\t</cfdi:Conceptos>\t<cfdi:Impuestos TotalImpuestosTrasladados=\"0.16\">\t<cfdi:Traslados>\t<cfdi:Traslado Importe=\"0.16\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"/>\t</cfdi:Traslados>\t</cfdi:Impuestos>\t<cfdi:Complemento/>\t</cfdi:Comprobante>";
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
