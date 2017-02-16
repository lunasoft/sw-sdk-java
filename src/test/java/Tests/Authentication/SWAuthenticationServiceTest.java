package Tests.Authentication;

import Exceptions.GenaralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by asalvio on 15/02/2017.
 */
public class SWAuthenticationServiceTest extends TestCase {

    public  void testToken() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","http://swservicestest.azurewebsites.net");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }
    public  void testTokenSUCCESS_RESPONSE() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","d-success");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public  void testTokenFAILURE_RESPONSE() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","d-fail");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Data);
    }

    public  void testTokenERROR_RESPONSE() throws Exception {
        try{
            SWAuthenticationService auth;
            auth = new SWAuthenticationService("demo","12345678A","d-error");
            IResponse response;
            response =  auth.Token();
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
        }
        catch (GenaralException e){
            Assert.assertNotNull("some bad happend", e);
        }
    }

}