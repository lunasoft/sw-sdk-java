package Tests.Pdf;


import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Pdf.SWPdfService;
import Tests.Utils;
import Utils.Responses.Pdf.PdfResponse;
import junit.framework.TestCase;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Ignore;


public class SWPdfServiceTest extends TestCase {
    
    public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrSkhlVUY2aXpLNWZkaHZlcDFtMWFhWkJpdGFxSFpRMXZSWUp5QUZsalZWd2huVWx3NUM3dVF6aWxJaGJqUU9QbFdWZW0zMTQxTmd6dUJ3dHR0SDlvTjhLUzFoT3VsMnRXWFlTWGFOUDNaeEhmUklFOWVEZjB6OE9QVGhzWnZ1bjRzWkUyeWd3UXlFMUtENVRXQ1pxRjg9.6Us1aA5VkXQTHyEBeaq-98l_5NTADHRAayJXQPT9qIA";
    public String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?> <cfdi:Comprobante Certificado=\"MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=\" Fecha=\"2018-08-05T10:00:02\" Folio=\"8\" FormaPago=\"01\" LugarExpedicion=\"06300\" MetodoPago=\"PUE\" Moneda=\"MXN\" NoCertificado=\"20001000000300022815\" Sello=\"C8KrUzYBA9ggJgc2t75aVi9r4fCjP4FkN4KVI77Epca6UvnzICz1XLjhhA9p9B3BLCyuNC4JQeBNuPW2KqO5dr4nwWe5mq7G38VxSTCmW46i1RmMKeZtlINt12jDoXWJ5h4Km00WAfvXV984h5vwwKPVQXP18WdbrvW4Ters1FEFgqLnEEvMVWbt9qHKtytjMyls/541GcTjAT1vTJYdeg0MeqrT29jzu2Qx8N8EGnDnzt2/5wF9A0YzvL2H5HnlzVjIk2htHeiszcQSMz9QSOkke86mLj8J23Z86C0dbVC32sbffzQXlZdjxjR0kCgGuLuYQgVkxUqze8otMHsOLw==\" Serie=\"N\" SubTotal=\"1000.00\" TipoDeComprobante=\"E\" Total=\"1160.00\" Version=\"3.3\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\"> <cfdi:CfdiRelacionados TipoRelacion=\"01\"> <cfdi:CfdiRelacionado UUID=\"c846c65a-6371-4449-9db6-a7daca2f7207\"/> <cfdi:CfdiRelacionado UUID=\"f784a223-a73d-429c-aa10-3f64a3d86dee\"/> </cfdi:CfdiRelacionados> <cfdi:Emisor Nombre=\"CINDEMEX SA DE CV\" RegimenFiscal=\"601\" Rfc=\"LAN7008173R5\"/> <cfdi:Receptor Nombre=\"PRUEBAS\" Rfc=\"XAXX010101000\" UsoCFDI=\"G01\"/> <cfdi:Conceptos> <cfdi:Concepto Cantidad=\"1\" ClaveProdServ=\"84111506\" ClaveUnidad=\"E48\" Descripcion=\"SERVICIOS CONTABLES\" Importe=\"1000.00\" NoIdentificacion=\"500\" ValorUnitario=\"1000.00\"> <cfdi:Impuestos> <cfdi:Traslados> <cfdi:Traslado Base=\"1000\" Importe=\"160.00\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"/> </cfdi:Traslados> </cfdi:Impuestos> </cfdi:Concepto> </cfdi:Conceptos> <cfdi:Impuestos TotalImpuestosTrasladados=\"160.00\"> <cfdi:Traslados> <cfdi:Traslado Importe=\"160.00\" Impuesto=\"002\" TasaOCuota=\"0.160000\" TipoFactor=\"Tasa\"/> </cfdi:Traslados> </cfdi:Impuestos> <cfdi:Complemento> <tfd:TimbreFiscalDigital FechaTimbrado=\"2018-08-06T09:08:48\" NoCertificadoSAT=\"20001000000300022323\" RfcProvCertif=\"AAA010101AAA\" SelloCFD=\"C8KrUzYBA9ggJgc2t75aVi9r4fCjP4FkN4KVI77Epca6UvnzICz1XLjhhA9p9B3BLCyuNC4JQeBNuPW2KqO5dr4nwWe5mq7G38VxSTCmW46i1RmMKeZtlINt12jDoXWJ5h4Km00WAfvXV984h5vwwKPVQXP18WdbrvW4Ters1FEFgqLnEEvMVWbt9qHKtytjMyls/541GcTjAT1vTJYdeg0MeqrT29jzu2Qx8N8EGnDnzt2/5wF9A0YzvL2H5HnlzVjIk2htHeiszcQSMz9QSOkke86mLj8J23Z86C0dbVC32sbffzQXlZdjxjR0kCgGuLuYQgVkxUqze8otMHsOLw==\" SelloSAT=\"MoFlBZC9vmpH8pv+YXWoVOgVRRTgYx4/jquTgTgePogtj5m++gLR4Mo5qvNDOeXsEJNlxR1lzI71xnfQq2XZacvpWlNpM1mn+pYdItbgvLYpDEt7IVICj33i8Wb3+7Rj6EWPzjwW7KIpa8jihy1C446pLYV60i/gQTZZlrVqBk6ivduA9bYjFQoo8BVvGn0czl7wmfk0hbek33syH4omfJMywA4KL3dKnm6gM9C6zSIl6JYm/UCpjAsjSy1w3KB8GBU8h82PtEo9GCyJpgz8sxx6TamXOo7mivL9X+nsrEE9UO1mcI0oCBUgFlCDrPnrtOqgXDXacyHU1cnYyLgrvg==\" UUID=\"0b567f23-319c-49bf-9755-9f216ae66b70\" Version=\"1.1\" xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\"/> </cfdi:Complemento> </cfdi:Comprobante>";
    
    @Ignore
    public void testPdfService() throws AuthException, GeneralException, IOException {
        SWPdfService app = new SWPdfService(token, Utils.url_pruebas);
        PdfResponse response = null;
        response = (PdfResponse) app.GeneratePdf(xml);
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.contentB64);
        System.out.println(response.contentSizeBytes);
        System.out.println(response.uuid);
        System.out.println(response.serie);
        System.out.println(response.folio);
        System.out.println(response.stampDate);
        System.out.println(response.issuedDate);
        System.out.println(response.rfcIssuer);
        System.out.println(response.rfcReceptor);
        System.out.println(response.total);
        
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    @Ignore
    public void testPdfService_incorrectToken() throws Exception {
                
    	SWPdfService app = new SWPdfService("wrong this", Utils.url_pruebas);
        PdfResponse response = null;
        response = (PdfResponse) app.GeneratePdf(xml);
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_message = "Access Denied";
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
    }
    @Ignore
    public void testPdfService_emptyUserParams() throws AuthException, GeneralException {
    	SWPdfService app = new SWPdfService("", "", Utils.url_pruebas);
        PdfResponse response = null;
        try {
        	response = (PdfResponse) app.GeneratePdf(xml);
        } 
        catch(Exception e){
            System.out.println("Something bad happened");
            System.out.println(e.getMessage());
            Assert.assertNotNull("Something bad happened", e);
        }
    }
}
