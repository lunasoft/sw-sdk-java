package Tests.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.*;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Assert;




public class SWStampServiceTest extends TestCase {


//STANDARD XML
   public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;

        response = (SuccessV1Response) api.Stamp(Utils.xml_b,"v1");
        System.out.println(response.Status);

        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.dummy_xml_string,"v2");
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.dummy_xml_string,"v3");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.dummy_xml_string,"V4");
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.toBase64(Utils.dummy_xml_string),"v1",true);
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        Assert.assertTrue(Utils.isValidB64(response.tfd));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.toBase64(Utils.dummy_xml_string),"v2",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
        Assert.assertTrue(cfdi_valid && tfd_valid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.toBase64(Utils.dummy_xml_string),"v3",true);
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.cfdi);
        Assert.assertTrue(Utils.isValidB64(response.cfdi));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.toBase64(Utils.dummy_xml_string),"V4",true);
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        boolean valid_cfdi = Utils.isValidB64(response.cfdi),
                valid_qr = Utils.isValidB64(response.qrCode),
                valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
                valid_sellosat = Utils.isValidB64(response.selloSAT);
        Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat);
    }



    //cc10 XML

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.cc10,"v1");
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.cc10,"v2");
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.cc10,"v3");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.cc10,"V4");
        System.out.println(response.Status);

        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.toBase64(Utils.cc10),"v1",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        Assert.assertTrue(Utils.isValidB64(response.tfd));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.toBase64(Utils.cc10),"v2",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
        Assert.assertTrue(cfdi_valid && tfd_valid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.toBase64(Utils.cc10),"v3",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        Assert.assertTrue(Utils.isValidB64(response.cfdi));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_CC10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.toBase64(Utils.cc10),"V4",true);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        boolean valid_cfdi = Utils.isValidB64(response.cfdi),
                valid_qr = Utils.isValidB64(response.qrCode),
                valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
                valid_sellosat = Utils.isValidB64(response.selloSAT);
        Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////PAGOS10/////////////////////////////////////////////////////////////////////////////////////////


    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.pagos10,"v1");
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.pagos10,"v2");
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.pagos10,"v3");
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);

        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.pagos10,"V4");
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.toBase64(Utils.pagos10),"v1",true);
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        Assert.assertTrue(Utils.isValidB64(response.tfd));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.toBase64(Utils.pagos10),"v2",true);
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
        Assert.assertTrue(cfdi_valid && tfd_valid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.toBase64(Utils.pagos10),"v3",true);
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        Assert.assertTrue(Utils.isValidB64(response.cfdi));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_PAGOS10() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.toBase64(Utils.pagos10),"V4",true);
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.cfdi);

        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        boolean valid_cfdi = Utils.isValidB64(response.cfdi),
                valid_qr = Utils.isValidB64(response.qrCode),
                valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
                valid_sellosat = Utils.isValidB64(response.selloSAT);
        Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////NOMINA12/////////////////////////////////////////////////////////////////////////////////////////


    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.nomina10,"v1");
        System.out.println(response.Status);
        System.out.print(response.message);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.nomina10,"v2");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.nomina10,"v3");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);

        System.out.println(response.cfdi);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.nomina10,"V4");
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);


    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV1Response response = null;
        response = (SuccessV1Response) api.Stamp(Utils.toBase64(Utils.nomina10),"v1",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.message);
        System.out.println(response.tfd);
        Assert.assertTrue(Utils.isValidB64(response.tfd));
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV2Response response = null;
        response = (SuccessV2Response) api.Stamp(Utils.toBase64(Utils.nomina10),"v2",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
        Assert.assertTrue(cfdi_valid && tfd_valid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV3Response response = null;
        response = (SuccessV3Response) api.Stamp(Utils.toBase64(Utils.nomina10),"v3",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        Assert.assertTrue(Utils.isValidB64(response.cfdi));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_NOMINA12() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        SuccessV4Response response = null;
        response = (SuccessV4Response) api.Stamp(Utils.toBase64(Utils.nomina10),"V4",true);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        boolean valid_cfdi = Utils.isValidB64(response.cfdi),
                valid_qr = Utils.isValidB64(response.qrCode),
                valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
                valid_sellosat = Utils.isValidB64(response.selloSAT);
        Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat);
    }



    public void testStampREAL_XML_STRING_EMPTY_PARAMS() throws Exception {

        try{
            SWStampService api = new SWStampService("","","");
            IResponse response = null;
            response = api.Stamp(Utils.dummy_xml_string,"v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);

        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);

        }

    }

    public void testStampREAL_XML_STRING_INCORRECT_PARAMS() throws Exception {

        try{
            SWStampService api = new SWStampService("USER_BAD","PASSWORD_BAD","BAD_URI");
            IResponse response = null;
            response = api.Stamp(Utils.dummy_xml_string,"v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);

        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);

        }

    }

    public void testStampTOKEN_EXPIRES_NOT_USER_NOT_PASSWORD() throws Exception {
        SWStampService api = new SWStampService("demo","123456789",Utils.url_pruebas);
        IResponse response = null;

        int request_number = 50;
        int revoke_token_step = request_number/2;
        int counter;

        for (counter=0;counter<request_number;counter++){
            if (counter==revoke_token_step){
                api.setToken(null);
                api.setUser(null);
                api.setPassword(null);
            }
            try{
                response = api.Stamp(Utils.dummy_xml_string,"v1");


            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Correcto excepcion lanzada");

               Assert.assertNotNull("some bad happend", e);
               break;
            }
        }
    }



}