package Tests.Issue;

import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;
import java.io.IOException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Issue.SWIssueServiceV2;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SWIssueServiceV2Test {
	static Utils ut = new Utils();

	@Test
	public void testFirst() throws AuthException, GeneralException, IOException {
		SWIssueServiceV2 api = new SWIssueServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueXml(ut.StringgenBasico(false), "v3");
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.cfdi);
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase(""));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueServiceV2 api = new SWIssueServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueXml(ut.StringgenBasicoTimbrePrevio(false), "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWIssueServiceV2 api = new SWIssueServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.IssueXml(ut.StringgenBasicoTimbrePrevio(false), "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueServiceV2 api = new SWIssueServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueXml(ut.StringgenBasicoTimbrePrevio(false), "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(response.cfdi.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWIssueServiceV2 api = new SWIssueServiceV2(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.IssueXml(ut.StringgenBasicoTimbrePrevio(false), "v4");
		System.out.println(response.message);
		System.out.println(response.Status);
		System.out.println(response.cfdi);
		System.out.println(response.qrCode);
		System.out.println(response.cadenaOriginalSAT);
		System.out.println(response.selloCFDI);
		System.out.println(response.selloSAT);
		System.out.println(response.noCertificadoCFDI);
		System.out.println(response.noCertificadoSAT);
		System.out.println(response.fechaTimbrado);
		System.out.println(response.uuid);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(response.uuid.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
}