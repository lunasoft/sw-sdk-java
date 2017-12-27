package Tests.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;
import Utils.Responses.SuccessAuthResponse;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by asalvio on 18/07/2017.
 */
public class SWAuthenticationServiceTest extends TestCase {
    public void testAuth(){
        SWAuthenticationService auth = new SWAuthenticationService("demo","123456789","http://services.test.sw.com.mx");
        try {
            SuccessAuthResponse res = (SuccessAuthResponse) auth.Token();
            String expected = "success";
            System.out.println(res.token);
            System.out.println(res.message);
            System.out.println(res.messageDetail);
            Assert.assertTrue(expected.equalsIgnoreCase(res.Status));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void testAuthProxy(){
        SWAuthenticationService auth = new SWAuthenticationService("demo","123456789","http://services.test.sw.com.mx","127.0.0.1","8888");
        try {
            SuccessAuthResponse res = (SuccessAuthResponse) auth.Token();
            String expected = "success";
            System.out.println(res.token);
            System.out.println(res.message);
            System.out.println(res.messageDetail);
            Assert.assertTrue(expected.equalsIgnoreCase(res.Status));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
