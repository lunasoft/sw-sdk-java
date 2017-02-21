package Tests.Stamp;

import Exceptions.GenaralException;
import Services.Authentication.SWAuthenticationService;
import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.IResponse;
import Utils.Responses.StampResponse;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Assert;



public class SWStampServiceTest extends TestCase {

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public void testStampREAL_XML_STRING_TOKEN_AUTH() throws Exception {
        SWAuthenticationService auth = new SWAuthenticationService("demo","123456789","http://services.test.sw.com.mx");
        JSONObject obj = new JSONObject(auth.Token().Data);
        String tkn = obj.getString("token");
        SWStampService api = new SWStampService(tkn,"http://services.test.sw.com.mx");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public void testStampREAL_XML_STRING_EMPTY_PARAMS() throws Exception {

        try{
            SWStampService api = new SWStampService("","","");
            IResponse response = null;
            response = api.Stamp(Utils.dummy_xml_string,"v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
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
            System.out.println(response.Data);
        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);

        }

    }

    public void testStampTOKEN_EXPIRES_NOT_USER_NOT_PASSWORD() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
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

    public void testStampRenewToken() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
        IResponse response = null;

        int request_number = 50;
        int revoke_token_step = request_number/2;
        int counter;
        int success_request =0;
        int fail_request = 0;
        for (counter=0;counter<request_number;counter++){
            if (counter==revoke_token_step){
                api.setToken(null);
            }
            try{
                response = api.Stamp(Utils.dummy_xml_string,"v1");

                success_request++;
            }catch (Exception e){
                fail_request++;
            }
        }
        System.out.println("Peticiones realizadas: "+request_number);
        System.out.println("Peticiones exitosas: "+success_request);
        System.out.println("Peticiones fallidas: "+fail_request);
        Assert.assertTrue(fail_request==0 && success_request==request_number);

    }


    public void testStampDUMMY_SUCCESS() throws Exception {
        SWStampService api = new SWStampService("tetete","d-success");
        IResponse response = null;
        response = api.Stamp("XML EN STRING","v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public void testStampDUMMY_FAILURE() throws Exception {
        SWStampService api = new SWStampService("tetete","d-fail");
        IResponse response = null;
        response =  api.Stamp("XML EN STRING","v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }
    public void testStampDUMMY_ERROR() throws Exception {
        try{
            SWStampService api = new SWStampService("tetete","d-error");
            StampResponse response = null;
            response = (StampResponse) api.Stamp("XML EN STRING","v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
        }
        catch(GenaralException e){
            System.out.println("Correcto excepcion lanzada");
            Assert.assertNotNull("some bad happend", e);
        }

    }

}