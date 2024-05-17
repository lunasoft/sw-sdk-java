package Tests.Stamp;

import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;
import org.junit.Assert;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SWStampsServiceZipTest {

	@Test
	public void testStamp_ZipXML() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		Utils ut = new Utils();
		String xml = ut.StringgenZip(false);
		byte[] byteXML = xml.getBytes(StandardCharsets.UTF_8);
		response = (SuccessV1Response) api.Stamp(byteXML, "v1");
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue("El campo tfd no debe estar vacío", response.tfd != null && !response.tfd.trim().isEmpty());
	}

	@Test
	public void testStamp_ZipXML_NoSellado() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		byte[] zipData = Files.readAllBytes(Paths.get("src/test/resources/CFDI40/ZIP/120000conceptosError.xml"));
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

	@Test
	public void testStamp_ZipXML_EstructuraIncorrecta() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		byte[] zipData = Files.readAllBytes(Paths.get("src/test/resources/CFDI40/ZIP/120000conceptosEstructError.xml"));
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

	@Test
	public void testStamp_ZipXML_FueraRango() throws Exception {
		SWStampService api = new SWStampService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		SuccessV1Response response = null;
		byte[] zipData = Files.readAllBytes(Paths.get("src/test/resources/CFDI40/ZIP/Mas120000conceptos.xml"));
		response = (SuccessV1Response) api.Stamp(zipData, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		if (response.message == "En este path sólo es posible timbrar facturas que tengan entre 110000 y 120000 nodos cfdi:Concepto  por este path"){ 
			String expect_status = "error";
			Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		}
	}
}
