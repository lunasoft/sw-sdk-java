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

    public  void testTokenSUCCESS_RESPONSE() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","d-success");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }

    public  void testTokenFAILURE_RESPONSE() throws Exception {
        SWAuthenticationService auth;
        auth = new SWAuthenticationService("demo","12345678A","d-fail");
        IResponse response;
        response =  auth.Token();
        System.out.println(response.HttpStatusCode);
        System.out.println(response.Msg);
    }

    public  void testTokenERROR_RESPONSE() throws Exception {
        try{
            SWAuthenticationService auth;
            auth = new SWAuthenticationService("demo","12345678A","d-error");
            IResponse response;
            response =  auth.Token();
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Msg);
        }
        catch (GenaralException e){
            Assert.assertNotNull("some bad happend", e);
        }
    }

}