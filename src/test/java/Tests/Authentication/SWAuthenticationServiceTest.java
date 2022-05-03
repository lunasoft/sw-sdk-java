package Tests.Authentication;

import Services.Authentication.SWAuthenticationService;
import Utils.Responses.Authentication.SuccessAuthResponse;
import java.io.IOException;

import org.junit.Assert;
import Tests.Utils;
import org.junit.Test;
import Exceptions.AuthException;
import Exceptions.GeneralException;

public class SWAuthenticationServiceTest {
	@Test
    public void testAuth(){
        try {
        	SWAuthenticationService auth = new SWAuthenticationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            SuccessAuthResponse res = (SuccessAuthResponse) auth.Token();
            String expected = "success";
            System.out.println(res.token);
            System.out.println(res.expires_in);
            System.out.println(res.message);
            System.out.println(res.messageDetail);
            Assert.assertTrue(expected.equalsIgnoreCase(res.Status));
        } catch (Exception e) {
        	Assert.fail(e.getMessage());
        }
    }
	@Test
    public void testBadAuth(){
        try {
        	SWAuthenticationService auth = new SWAuthenticationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            auth.Token();
        } catch (AuthException e) {
            System.out.println(e.getErrorMSG());
            System.out.println(e.getHttpStatusCode());
            Assert.assertTrue(true);
        } catch (GeneralException e) {
        	Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
    }
}
