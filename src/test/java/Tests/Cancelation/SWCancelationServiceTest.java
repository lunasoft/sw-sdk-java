package Tests.Cancelation;

import Services.Cancelation.SWCancelationService;
import Tests.Utils;
import Utils.Responses.CancelationResponse;
import junit.framework.TestCase;
import org.junit.Assert;


public class SWCancelationServiceTest extends TestCase {
    
    public String uuid = "06a46e4b-b154-4c12-bb77-f9a63ed55ff2";
    public String password_csd = "12345678a";
    public String rfc = "LAN7008173R5";
    public String b64Cer = "MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=";
    public String b64Key = "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIAgEAAoIBAQACAggAMBQGCCqGSIb3DQMHBAgwggS9AgEAMASCBMh4EHl7aNSCaMDA1VlRoXCZ5UUmqErAbucRBAKNQXH8t1GNfLDIQejtcocS39VvWnpNXjZJeCg65Y2wI36UGn78gvnU0NOmyUkXksPVrkz7hqNtAVojPUtN65l+MVAsIRVD6OLJeKZ2bLx5z78zrx6Tp1zCGT/NpxL+CJSy5iY6TKqbJcK/9198noOvT2p8rKVqUUF3wLRvD6R/b3BC5wCon/exp3BUTZeiWJqGRRgaW4rn49ZbJPVIcDmUO8mojPesFHjJDSnA0nBnWaUvTYXi0srT+dLZOewsBR8d5GdSWh9ZkM29wJbjYHCMsXkObZjaap3YM8fU29zRyZ8KAqaCnBHCfYjbib56m+Lmnk+ScqMkQQ+S/+2pzn2LzauvBI4p/OjQgBDeblo22X7sX9OA9YaqB3q6CCjQ5tkDNrz3HOgTm+amh/kI8TEn9rcKf4Ru7mC1T7VMaFgBqpIS8YJNbcgegF0IF1FpCS05wjdU5CktYAnPnvC+Pj+MFDeH+184kIHBWqPNG6dAzALxRgtKTlGdJ1l5Do+4EWI+0mvKojREnKoDczFnDeCFnM51u3I9Vce3rkf0djRQKFomPVUnPDqxlR5lDAssYAYNcECAkvGxKcBDbjWi/6NHlwjS1r28+0Jhvfxjx9O6hi4AW82Q2/kBE5P/eOwln/jKSbLgi7Iyim1FFHxkQH1FY5kcKhAzFcIq85rGFlzHRfPF9OIQSmONI9kcWQCxkk8aG1u1zwbjZRYLTxlwmZvynOgaWRpTN8Y4ReBDIG1klhva7nqqoM416oXBG71IKaCtjAwRlE6pgaqnIz/WQAb2FR541pqynX6dB6DB1nIWnatsWZJZlu+Bnhf9DBlUsO9ZSAf9Fa9nJAzwFCzaKIsvGJIeKSZ/h+vInkjaO/rxswErVROTfZy1lO2CJ/xnAgzFGrpDxNJPliv3McO9TGwYy/zHhE6Pdo8Xu6NsMisNU6TB8Bc26uLNv/7kWhNmNnBA1qt5akln6hOHrPBXGBiTNUL0IoFVPNdCbS0834zAYXfgtZLDzVpeLqmeMpqXbIYK0/NXe9etxuOcN40O+B/fTHHmO7dMgBZ4vAApVQUPr7ilumVHsWSMRP/0p5R9q4qr1bDm9S5YCPevdyYWTSceGSrXHmjYzJLBtpc/s77mynNqZEYjhnKk2XRNp6kp/FYRu+QdsX9vaDJbLKR2EnSC4fU6UOTO03IZU15j3wOsg30QrXoKntSJ/beF99cvFHuPrQPWxCtws0lLwkkHNVOm6XNO948Moy1w1pL4i68CwmceYZaYrYhmHGdLuescFQrZQaULDWhpK2Stys8Vs/XwwxNi9MHAFSXpdy/b+Aro5n87w+0MHRcllF8ZKbtQ/ym4oG7aREuo7o71JXJQPjZKTOtVM1EQx/FLM/5brnDSoyvLtoYtv9/tTnIC+8gR6eErkzaGmn8pftPhGNuz6yzx8JeLFoMD7VWbGTefj46KS+yMweFJnpReHEqwnukXpEYq19EWVyQa/Sb7navtKt80y/vRs0aNZp3iL23AOs0u1kQ1CFNY2y12Gor1koaH2FUd5jAQnaSKmgarLy0H/QVvR2g8B3+Fh49QhKYrd8N6LvvI80cwbEoqYWn5DWA=";
    public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrUndzK0dHS2dpTittY1JmR3o2M3NqNkh4MW9KVXMvUHhZYzVLQS9UK2E1SVhEZFJKYWx4ZmlEWDFuSXlqc2ZRYXlUQk1ldlZkU2tEdU10NFVMdHZKUURLblBxakw0SDl5bUxabDFLNmNPbEp6b3Jtd2Q1V2htRHlTdDZ6eTFRdUNnYnVvK2tuVUdhMmwrVWRCZi9rQkU9.7k2gVCGSZKLzJK5Ky3Nr5tKxvGSJhL13Q8W-YhT0uIo";
    public String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Cancelacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" RfcEmisor=\"LAN7008173R5\" Fecha=\"2017-07-06T17:00:31\" xmlns=\"http://cancelacfd.sat.gob.mx\"><Folios><UUID>3eaeabc9-ea41-4627-9609-c6856b78e2b1</UUID></Folios><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>rs2ZcFnS9hbfmyJLmR3Mtnklt7g=</DigestValue></Reference></SignedInfo><SignatureValue>O/I7ILsU2y1fqeb2NBZSQKlQC3DpN/bgcDB5LWCMIYp4mFCLmLxEq/6ADz0xVQWUw49BqWDZ1GAI4ODIZLDQtafHSIE7BXKy8huvKD1dtpRLQ/39IfpxXsz1g6Q14mH3LxDOQugk/GhKMWILXZnIipyQosv3IbgLMZ/V/4btK7xrFX/KiOt0PcefChyaerj9A815dA3J4JgpBUNzbOz9VlhvdZMJskrHxzZ5riU1TAuSw/oi68dJfA7S+6XrTmeFDQzYxACHyOzj24RjLi/31+Fc/wiqQXNu9O6oWl8p5+GVoz2xtU4aRqLxVh73L6WAAef/WDeKDMfIge1BtMrxYw==</SignatureValue><KeyInfo><X509Data><X509IssuerSerial><X509IssuerName>OID.1.2.840.113549.1.9.2=Responsable: ACDMA, OID.2.5.4.45=SAT970701NN3, L=Coyoac?n, S=Distrito Federal, C=MX, PostalCode=06300, STREET=\"Av. Hidalgo 77, Col. Guerrero\", E=asisnet@pruebas.sat.gob.mx, OU=Administraci?n de Seguridad de la Informaci?n, O=Servicio de Administraci?n Tributaria, CN=A.C. 2 de pruebas(4096)</X509IssuerName><X509SerialNumber>3230303031303030303030333030303232383135</X509SerialNumber></X509IssuerSerial><X509Certificate>MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=</X509Certificate></X509Data></KeyInfo></Signature></Cancelacion>";
    
//CSD

    public void testCancelationServiceCSD_authUser() throws Exception {
        SWCancelationService app = new SWCancelationService("demo","123456789",Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);               
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.acuse);
        System.out.println(response.uuid);
        System.out.println(response.uuidStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    

    public void testCancelationServiceCSD_authToken() throws Exception {
        SWCancelationService app = new SWCancelationService(token,Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.acuse);
        System.out.println(response.uuid);
        System.out.println(response.uuidStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    
    
    public void testCancelationServiceCSD_incorrectParams() throws Exception {
        SWCancelationService app = new SWCancelationService(token,Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation("123456", "123456", "123456", "123456", "123456");
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_status = "error";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
   
    
    public void testCancelationServiceCSD_incorrectToken() throws Exception {
                
        SWCancelationService app = new SWCancelationService("wrong token",Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_message = "Unauthorized";
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
    }
        
    public void testCancelationServiceCSD_emptyUserParams() throws Exception {

        try{
            SWCancelationService app = new SWCancelationService("","","");
            CancelationResponse response = null;
            response = (CancelationResponse)app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            System.out.println(response.messageDetail);
        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);
        }
    }
    
    
    public void testCancelationServiceCSD_emptyCancelationParams() throws Exception {

        SWCancelationService app = new SWCancelationService("demo","123456789",Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse) app.Cancelation("", "", "", "", "");
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_message = "CACFDI33 - Parámetros incompletos";
        int expected_HttpStatusCode = 400;
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
        Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
        
    }
     
    
    
//XML
    
    public void testCancelationServiceXML_validXML() throws Exception {
        SWCancelationService app = new SWCancelationService("demo", "123456789", Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse) app.Cancelation(xml);

        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        System.out.println(response.acuse);
        System.out.println(response.uuid);
        System.out.println(response.uuidStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    
    
    public void testCancelationServiceXML_invalidXML() throws Exception {
        SWCancelationService app = new SWCancelationService("demo","123456789",Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation("wrong xml");
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_status = "error";
        String expect_message = "CA1000. El xml proporcionado está mal formado o es inválido.There is an error in XML document (1, 1).";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
    }
    
    public void testCancelationServiceXML_nullXML() throws Exception {
        SWCancelationService app = new SWCancelationService("demo","123456789",Utils.url_pruebas);
        CancelationResponse response = null;
        response = (CancelationResponse)app.Cancelation("");
        
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.messageDetail);
        String expect_status = "error";
        String expect_message = "CA1000. El xml proporcionado está mal formado o es inválido.There is an error in XML document (0, 0).";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
    }
}
