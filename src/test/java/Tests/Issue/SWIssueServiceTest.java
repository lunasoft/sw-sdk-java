package Tests.Issue;

import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;
import org.junit.Assert;
import org.junit.Test;

import Services.Issue.SWIssueService;

public class SWIssueServiceTest {
	static Utils ut = new Utils();
	@Test
	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String testJson = ut.JsonGenBasico();
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueJson(testJson, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String testJson = ut.JsonGenBasico();
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.IssueJson(testJson, "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String testJson = ut.JsonGenBasico();
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueJson(testJson, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_status = "success";
		Assert.assertTrue(
				expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String testJson = ut.JsonGenBasico();
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.IssueJson(testJson, "v4");
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
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String normalXml = ut.StringgenBasico();
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueXml(normalXml, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String normalXml = ut.genc10();
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.IssueXml(normalXml, "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String normalXml = ut.StringgenBasico();
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueXml(normalXml, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		String normalXml = ut.genPagos10();
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.IssueXml(normalXml, "v4");
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
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}