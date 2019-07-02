package Tests.Stamp;

import Services.Stamp.SWStampServiceV2;
import Tests.Utils;
import Utils.Responses.*;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SWStampServiceV2Test {
	static Utils ut = new Utils();
	static String normalXml = ut.StringgenBasico();
	static String nominaXml = ut.genNomina10();
	static String comercioEXml = ut.genc10();
	static String pagosXml = ut.genPagos10();

	@Test
	public void testFirst() throws AuthException, GeneralException, IOException {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(normalXml, "v3");
		Assert.assertNotNull(response.cfdi);
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase(""));
		response = (SuccessV3Response) api.Stamp(nominaXml, "v3");
		Assert.assertNotNull(response.cfdi);
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase(""));
		response = (SuccessV3Response) api.Stamp(comercioEXml, "v3");
		Assert.assertNotNull(response.cfdi);
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase(""));
		response = (SuccessV3Response) api.Stamp(pagosXml, "v3");
		Assert.assertNotNull(response.cfdi);
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase(""));
	}

	// STANDARD XML
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(normalXml, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(normalXml, "v2");
		
		System.out.println(response.message);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(normalXml, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;

		response = (SuccessV4Response) api.Stamp(normalXml, "V4");
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
		System.out.println(response.messageDetail);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.uuid.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));

	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(ut.encodeBase64(normalXml), "v1", true);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.tfd) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(ut.encodeBase64(normalXml), "v2", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(cfdi_valid && tfd_valid && expect_error.equalsIgnoreCase((response.message)));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(ut.encodeBase64(normalXml), "v3", true);
		System.out.println(response.message);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.cfdi) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(ut.encodeBase64(normalXml), "V4", true);
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
		boolean valid_cfdi = Utils.isValidB64(response.cfdi), valid_qr = Utils.isValidB64(response.qrCode),
				valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
				valid_sellosat = Utils.isValidB64(response.selloSAT);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat
				&& expect_error.equalsIgnoreCase(response.message));
	}

	// cc10 XML
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(comercioEXml, "v1");
		System.out.println(response.message);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(comercioEXml, "v2");
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(comercioEXml, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(comercioEXml, "V4");
		System.out.println(response.Status);

		System.out.println(response.qrCode);
		System.out.println(response.cadenaOriginalSAT);
		System.out.println(response.selloCFDI);
		System.out.println(response.selloSAT);
		System.out.println(response.noCertificadoCFDI);
		System.out.println(response.noCertificadoSAT);
		System.out.println(response.fechaTimbrado);
		System.out.println(response.uuid);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.uuid.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(ut.encodeBase64(comercioEXml), "v1", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.tfd) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(ut.encodeBase64(comercioEXml), "v2", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(cfdi_valid && tfd_valid && expect_error.equalsIgnoreCase(response.message));

	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(ut.encodeBase64(comercioEXml), "v3", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.cfdi) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_CC10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(ut.encodeBase64(comercioEXml), "V4", true);
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
		boolean valid_cfdi = Utils.isValidB64(response.cfdi), valid_qr = Utils.isValidB64(response.qrCode),
				valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
				valid_sellosat = Utils.isValidB64(response.selloSAT);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat
				&& expect_error.equalsIgnoreCase(response.message));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////// PAGOS10/////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(pagosXml, "v1");
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(pagosXml, "v2");
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(pagosXml, "v3");
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);

		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(pagosXml, "V4");
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
		Assert.assertTrue(!response.uuid.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));

	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(ut.encodeBase64(pagosXml), "v1", true);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.tfd) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(ut.encodeBase64(pagosXml), "v2", true);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(cfdi_valid && tfd_valid && expect_error.equalsIgnoreCase(response.message));

	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(ut.encodeBase64(pagosXml), "v3", true);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.cfdi) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_PAGOS10() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(ut.encodeBase64(pagosXml), "V4", true);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.cfdi);

		System.out.println(response.qrCode);
		System.out.println(response.cadenaOriginalSAT);
		System.out.println(response.selloCFDI);
		System.out.println(response.selloSAT);
		System.out.println(response.noCertificadoCFDI);
		System.out.println(response.noCertificadoSAT);
		System.out.println(response.fechaTimbrado);
		System.out.println(response.uuid);
		boolean valid_cfdi = Utils.isValidB64(response.cfdi), valid_qr = Utils.isValidB64(response.qrCode),
				valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
				valid_sellosat = Utils.isValidB64(response.selloSAT);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat
				&& expect_error.equalsIgnoreCase(response.message));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////// NOMINA12/////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(nominaXml, "v1");
		System.out.println(response.Status);
		System.out.print(response.message);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(nominaXml, "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(nominaXml, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.cfdi.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(nominaXml, "V4");
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
		Assert.assertTrue(!response.uuid.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.Stamp(ut.encodeBase64(nominaXml), "v1", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.tfd);
		Assert.assertTrue(Utils.isValidB64(response.tfd));
		
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(!response.tfd.equalsIgnoreCase("") && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.Stamp(ut.encodeBase64(nominaXml), "v2", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(cfdi_valid && tfd_valid && expect_error.equalsIgnoreCase(response.message));

	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.Stamp(ut.encodeBase64(nominaXml), "v3", true);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.cfdi);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(Utils.isValidB64(response.cfdi) && expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64_NOMINA12() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.Stamp(ut.encodeBase64(nominaXml), "V4", true);
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
		boolean valid_cfdi = Utils.isValidB64(response.cfdi), valid_qr = Utils.isValidB64(response.qrCode),
				valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
				valid_sellosat = Utils.isValidB64(response.selloSAT);
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat
				&& expect_error.equalsIgnoreCase(response.message));
	}
	@Test
	public void testStampREAL_XML_STRING_EMPTY_PARAMS() throws Exception {

		try {
			SWStampServiceV2 api = new SWStampServiceV2("", "", "");
			IResponse response = null;
			response = api.Stamp(normalXml, "v1");
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);

		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);

		}

	}
	@Test
	public void testStampREAL_XML_STRING_INCORRECT_PARAMS() throws Exception {

		try {
			SWStampServiceV2 api = new SWStampServiceV2("USER_BAD", "PASSWORD_BAD", "BAD_URI");
			IResponse response = null;
			response = api.Stamp(normalXml, "v1");
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);

		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);

		}

	}
	@Test
	public void testStampTOKEN_EXPIRES_NOT_USER_NOT_PASSWORD() throws Exception {
		SWStampServiceV2 api = new SWStampServiceV2("demo", "123456789", Utils.url_pruebas);

		int request_number = 50;
		int revoke_token_step = request_number / 2;
		int counter;

		for (counter = 0; counter < request_number; counter++) {
			if (counter == revoke_token_step) {
				api.setToken(null);
				api.setUser(null);
				api.setPassword(null);
			}
			try {
				api.Stamp(normalXml, "v1");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Something bad happened");

				Assert.assertNotNull("Something bad happened", e);
				break;
			}
		}
	}
}