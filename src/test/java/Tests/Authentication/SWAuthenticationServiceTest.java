package Tests.Authentication;

import Services.Authentication.SWAuthenticationService;
import Utils.Responses.Authentication.SuccessAuthResponse;

import org.junit.Assert;
import junit.framework.TestCase;

public class SWAuthenticationServiceTest extends TestCase {
    public void testAuth(){
        try {
        	SWAuthenticationService auth = new SWAuthenticationService("demo","123456789","http://services.test.sw.com.mx");
            SuccessAuthResponse res = (SuccessAuthResponse) auth.Token();
            String expected = "success";
            System.out.println(res.token);
            System.out.println(res.expires_in);
            System.out.println(res.message);
            System.out.println(res.messageDetail);
            Assert.assertTrue(expected.equalsIgnoreCase(res.Status));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
