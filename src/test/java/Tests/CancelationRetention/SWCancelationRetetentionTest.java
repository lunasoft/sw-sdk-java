package Tests.CancelationRetention;

import Services.CancelationRetention.SWCancelationRetentionService;
import Tests.Utils;
import Utils.Responses.Cancelation.CancelationResponse;
import org.junit.Assert;
import org.junit.Test;

public class SWCancelationRetetentionTest {
	@Test
	public void testCancelationRetentionCSD_authUser() throws Exception {
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.userSW, Utils.passwordSW,
				Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuidRetencion, Utils.passwordCsd, Utils.rfc,
				Utils.cerb64, Utils.keyb64, "01",
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
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64,
				Utils.keyb64, "01",
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
	public void testCancelationServiceCSD_incorrectParams() throws Exception {
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.tokenSW, Utils.urlSW);
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
	public void testCancelationRetentionXML_validXML() throws Exception {
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.userSW, Utils.passwordSW,
				Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.cancelacionXmlRet);
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
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.userSW, Utils.passwordSW,
				Utils.urlSW);
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
	public void testCancelationServicePfx_authToken() throws Exception {
		SWCancelationRetentionService app = new SWCancelationRetentionService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuidRetencion, Utils.passwordPfx, Utils.rfc,
				Utils.pfxb64, "01", Utils.folioSustitucionRet);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void testCancelationServicePfx_incorrectToken() throws Exception {
		SWCancelationRetentionService app = new SWCancelationRetentionService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64, "01",
				Utils.foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}

	@Test
	public void testCancelationServicePfx_emptyUserParams() throws Exception {
		try {
			SWCancelationRetentionService app = new SWCancelationRetentionService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64,
					"01", Utils.foliosustitucion);
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
}
