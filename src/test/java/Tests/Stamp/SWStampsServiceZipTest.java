package Tests.Stamp;

import Services.Stamp.SWStampServiceZip;
import Tests.Utils;
import Utils.Responses.*;
import Utils.Responses.Stamp.SuccessV1Response;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SWStampsServiceZipTest {
    //STANDARD XML
    @Test
	public void testStamp_ZipXML() throws Exception {
        SWStampServiceZip api = new SWStampServiceZip(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp("src/test/resources/CFDI40/ZIP/120000conceptos.zip","v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStamp_ZipXML_Error() throws Exception {
        SWStampServiceZip api = new SWStampServiceZip(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp("src/test/resources/CFDI40/ZIP/120000conceptosError.zip","v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
