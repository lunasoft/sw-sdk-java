package Tests.Authentication;

import Services.Authentication.SWAuthenticationService;
import Utils.Responses.SuccessAuthResponse;
import java.text.ParseException;
import org.junit.Assert;
import junit.framework.TestCase;

/**
 * Created by asalvio on 18/07/2017.
 */
public class SWAuthenticationServiceTest extends TestCase {
    public void testAuth() throws ParseException{
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
}
