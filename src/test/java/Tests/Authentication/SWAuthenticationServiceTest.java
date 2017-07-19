package Tests.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by asalvio on 18/07/2017.
 */
public class SWAuthenticationServiceTest extends TestCase {
    public void testAuth(){
        SWAuthenticationService auth = new SWAuthenticationService("demo","123456789","http://services.test.sw.com.mx");
        try {
            IResponse res = auth.Token();
            String expected = "success";
            System.out.println(res.token);
            Assert.assertTrue(expected.equalsIgnoreCase(res.Status));
        } catch (GeneralException e) {
            e.printStackTrace();
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }
}
