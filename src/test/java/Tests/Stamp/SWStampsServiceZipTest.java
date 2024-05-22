package Tests.Stamp;

import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SWStampsServiceZipTest {

	@Ignore
	@Test
	public void testStamp_ZipXML() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		Utils ut = new Utils();
		String xml = ut.StringgenLongXML(false);
		byte[] byteXML = xml.getBytes(StandardCharsets.UTF_8);
		response = (SuccessV1Response) api.Stamp(byteXML, "v1");
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue("El campo tfd no debe estar vacío", response.tfd != null && !response.tfd.trim().isEmpty());
	}
	@Ignore
	@Test
	public void testStamp_ZipXML_String() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		Utils ut = new Utils();
		String xml = ut.StringgenLongXML(false);
		response = (SuccessV1Response) api.Stamp(xml, "v1");
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue("El campo tfd no debe estar vacío", response.tfd != null && !response.tfd.trim().isEmpty());
	}
	@Ignore
	@Test
	public void testStamp_ZipXML_NoSellado() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		byte[] zipData = Files.readAllBytes(Paths.get("src/test/resources/CFDI40/ZIP/155000conceptosError.xml"));
		response = (SuccessV1Response) api.Stamp(zipData, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		if (response.message == "CFDI40102 - El resultado de la digestión debe ser igual al resultado de la desencripción del sello."){ 
			String expect_status = "error";
			Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		}
	}
	@Ignore
	@Test
	public void testStamp_ZipXML_EstructuraIncorrecta() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		byte[] zipData = Files.readAllBytes(Paths.get("src/test/resources/CFDI40/ZIP/155000conceptosEstructError.xml"));
		response = (SuccessV1Response) api.Stamp(zipData, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		if (response.message == "301 - La estructura del comprobante es incorrecta.") {
			String expect_status = "error";
			Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		}
	}
}
