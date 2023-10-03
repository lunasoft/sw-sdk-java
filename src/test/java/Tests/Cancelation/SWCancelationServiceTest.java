package Tests.Cancelation;

import Services.Cancelation.SWCancelationService;
import Tests.Utils;
import Utils.Responses.Cancelation.CancelationResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SWCancelationServiceTest {
	// csd
	@Test
	public void testCancelationServiceCSD_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "01",
				Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceCSD_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "01",
				Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceCSD_FolioSustitionNull() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "02", null);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceCsd_FolioSustitionStringEmpty() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "02", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceCSD_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456", "123456", "123456",
				"123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceCSD_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "01",
				Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}

	@Test
	public void testCancelationServiceCSD_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64, "01",
				Utils.foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}

	@Test
	public void testCancelationServiceCSD_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	// XML
	@Test
	public void testCancelationServiceXML_validXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.cancelacionXml);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceXML_invalidXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("wrong xml");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}

	@Test
	public void testCancelationServiceXML_nullXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}

	// Pfx
	@Test
	public void testCancelationServicePfx_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_FolioSustitionNull() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "02", null);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_FolioSustitionStringEmpty() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "02", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456", "123456", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}

	@Test
	public void testCancelationServicePfx_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "01",Utils.foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}

	@Test
	public void testCancelationServicePfx_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	// Uuid
	@Test
	public void testCancelationServiceUuid_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.rfc, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceUuid_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.rfc, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.messageDetail);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceUuid_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "12345", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServiceUuid_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.rfc, "01", Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}

	@Test
	public void testCancelationServiceUuid_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.rfc, "01", Utils.foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}

	@Test
	public void testCancelationServiceUuid_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 404;
		Assert.assertTrue(response.message.equalsIgnoreCase("Not Found"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}
}
