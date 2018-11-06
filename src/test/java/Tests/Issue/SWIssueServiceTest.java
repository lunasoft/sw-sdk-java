package Tests.Issue;

import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;
import junit.framework.TestCase;
import org.junit.Assert;

import Services.Issue.SWIssueService;

public class SWIssueServiceTest extends TestCase {
	private String testJson = "{ \"RFCINC\": null, \"Rates\": null, \"ConfirmationStatus\": 0, \"HasNomina12\": false, \"HasPagos10\": false, \"HasComercioExterior11\": false, \"HasINE11\": false, \"HasECC11\": false, \"HasImpLocales\": false, \"HasComplemento\": false, \"CfdiRelacionados\": null, \"Emisor\": { \"Rfc\": \"LAN8507268IA\", \"Nombre\": \"MB IDEAS DIGITALES SC\", \"RegimenFiscal\": \"601\" }, \"Receptor\": { \"Rfc\": \"AAA010101AAA\", \"Nombre\": \"SW SMARTERWEB - ón\", \"ResidenciaFiscalSpecified\": false, \"NumRegIdTrib\": null, \"UsoCFDI\": \"G03\" }, \"Conceptos\": [ { \"Impuestos\": { \"Traslados\": [ { \"Base\": \"200.00\", \"Impuesto\": \"002\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"0.160000\", \"TasaOCuotaSpecified\": true, \"Importe\": \"32.00\", \"ImporteSpecified\": true }, { \"Base\": \"232.00\", \"Impuesto\": \"003\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"1.600000\", \"TasaOCuotaSpecified\": true, \"Importe\": \"371.20\", \"ImporteSpecified\": true } ], \"Retenciones\": null }, \"InformacionAduanera\": null, \"CuentaPredial\": null, \"ComplementoConcepto\": null, \"Parte\": null, \"ClaveProdServ\": \"50211503\", \"NoIdentificacion\": \"UT421511\", \"Cantidad\": 1, \"ClaveUnidad\": \"H87\", \"Unidad\": \"Pieza\", \"Descripcion\": \"Cigarros\", \"ValorUnitario\": \"200.00\", \"Importe\": \"200.00\", \"DescuentoSpecified\": false } ], \"Impuestos\": { \"Retenciones\": null, \"Traslados\": [ { \"Impuesto\": \"002\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"0.160000\", \"Importe\": \"32.00\" }, { \"Impuesto\": \"003\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"1.600000\", \"Importe\": \"371.20\" } ], \"TotalImpuestosRetenidosSpecified\": false, \"TotalImpuestosTrasladados\": \"403.20\", \"TotalImpuestosTrasladadosSpecified\": true }, \"Complemento\": null, \"Addenda\": null, \"Version\": \"3.3\", \"Serie\": \"RogueOne\", \"Folio\": \"HNFK231\", \"Fecha\": \"2018-11-01T09:44:54\", \"Sello\": \"\", \"FormaPago\": \"01\", \"FormaPagoSpecified\": true, \"NoCertificado\": \"\", \"Certificado\": \"\", \"CondicionesDePago\": null, \"SubTotal\": \"200.00\", \"DescuentoSpecified\": false, \"Moneda\": \"MXN\", \"TipoCambio\": \"1\", \"TipoCambioSpecified\": true, \"Total\": \"603.20\", \"TipoDeComprobante\": \"I\", \"MetodoPago\": \"PUE\", \"MetodoPagoSpecified\": true, \"LugarExpedicion\": \"06300\", \"Confirmacion\": null }";
	private String xmlIssue = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <cfdi:Comprobante Certificado=\"@\" Fecha=\"2018-11-01T05:55:55\" Folio=\"1111\" LugarExpedicion=\"45400\" Moneda=\"XXX\" NoCertificado=\"20001000000300022815\" Sello=\"@\" Serie=\"11\" SubTotal=\"0\" TipoDeComprobante=\"P\" Total=\"0\" Version=\"3.3\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/sitio_internet/cfd/Pagos/Pagos10.xsd\" xmlns:pago10=\"http://www.sat.gob.mx/Pagos\"> <cfdi:Emisor Nombre=\"PruebaPago\" RegimenFiscal=\"601\" Rfc=\"LAN7008173R5\"/> <cfdi:Receptor Nombre=\"Testón\" Rfc=\"LAN8507268IA\" UsoCFDI=\"P01\"/> <cfdi:Conceptos> <cfdi:Concepto Cantidad=\"1\" ClaveProdServ=\"84111506\" ClaveUnidad=\"ACT\" Descripcion=\"Pago\" Importe=\"0\" ValorUnitario=\"0\"/> </cfdi:Conceptos> <cfdi:Complemento> <pago10:Pagos Version=\"1.0\"> <pago10:Pago FechaPago=\"2018-08-02T01:00:02\" FormaDePagoP=\"01\" MonedaP=\"MXN\" Monto=\"1.00\"> <pago10:DoctoRelacionado Folio=\"1111111111\" IdDocumento=\"bfc36522-4b8e-45c4-8f14-d11b289f9eb7\" ImpPagado=\"1.00\" MetodoDePagoDR=\"PUE\" MonedaDR=\"MXN\" Serie=\"22\"/> </pago10:Pago> </pago10:Pagos> </cfdi:Complemento> </cfdi:Comprobante>";

	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueJson(testJson, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "success";
		String expect_error = "401 - El rango de la fecha de generación no debe de ser mayor a 72 horas para la emisión del timbre.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.IssueJson(testJson, "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		System.out.println(response.message);
		String expect_status = "success";
		String expect_error = "401 - El rango de la fecha de generación no debe de ser mayor a 72 horas para la emisión del timbre.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueJson(testJson, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_status = "success";
		String expect_error = "401 - El rango de la fecha de generación no debe de ser mayor a 72 horas para la emisión del timbre.";
		Assert.assertTrue(
				expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_JSON_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
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
		String expect_error = "401 - El rango de la fecha de generación no debe de ser mayor a 72 horas para la emisión del timbre.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}
	
	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueXml(xmlIssue, "v1");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.message);
		String expect_status = "success";
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV2Response response = null;
		response = (SuccessV2Response) api.IssueXml(xmlIssue, "v2");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		System.out.println(response.message);
		String expect_status = "success";
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueXml(xmlIssue, "v3");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.cfdi);
		String expect_status = "success";
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(
				expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV4Response response = null;
		response = (SuccessV4Response) api.IssueXml(xmlIssue, "v4");
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
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}
}