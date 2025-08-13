package Tests.StampRetention;
import org.junit.Assert;
import org.junit.Test;
import Tests.Utils;
import Utils.Responses.StampRetention.SuccessV3Response;
import Services.StampRetention.SWStampRetentionService;

public class SWStampRetentionTest {
    
    @Test
    public void testStampRetention_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
        SWStampRetentionService api = new SWStampRetentionService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        SuccessV3Response response = null;
        Utils ut = new Utils();
        response = (SuccessV3Response) api.StampRetention(ut.StringgenBasicoRetention(false), "v3");
        System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.retencion);
		System.out.println(response.message);
        String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        Assert.assertNotNull(response.retencion);
    }

    @Test
    public void testStampRetention_XML_STRING_TOKEN_AUTH_V3() throws Exception {
        SWStampRetentionService api = new SWStampRetentionService(Utils.tokenSW, Utils.urlSW);
        SuccessV3Response response = null;
        Utils ut = new Utils();
        response = (SuccessV3Response) api.StampRetention(ut.StringgenBasicoRetention(false), "v3");
        System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.retencion);
		System.out.println(response.message);
        String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        Assert.assertNotNull(response.retencion);
    }
}
