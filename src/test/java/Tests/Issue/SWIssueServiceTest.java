package Tests.Issue;

import Tests.Utils;
import Utils.Responses.*;
import junit.framework.TestCase;
import org.junit.Assert;

import Services.Issue.SWIssueService;

public class SWIssueServiceTest extends TestCase {
	private String testJson = "{ \"RFCINC\": null, \"Rates\": null, \"ConfirmationStatus\": 0, \"HasNomina12\": false, \"HasPagos10\": false, \"HasComercioExterior11\": false, \"HasINE11\": false, \"HasECC11\": false, \"HasImpLocales\": false, \"HasComplemento\": false, \"CfdiRelacionados\": null, \"Emisor\": { \"Rfc\": \"LAN8507268IA\", \"Nombre\": \"MB IDEAS DIGITALES SC\", \"RegimenFiscal\": \"601\" }, \"Receptor\": { \"Rfc\": \"AAA010101AAA\", \"Nombre\": \"SW SMARTERWEB\", \"ResidenciaFiscalSpecified\": false, \"NumRegIdTrib\": null, \"UsoCFDI\": \"G03\" }, \"Conceptos\": [ { \"Impuestos\": { \"Traslados\": [ { \"Base\": \"200.00\", \"Impuesto\": \"002\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"0.160000\", \"TasaOCuotaSpecified\": true, \"Importe\": \"32.00\", \"ImporteSpecified\": true }, { \"Base\": \"232.00\", \"Impuesto\": \"003\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"1.600000\", \"TasaOCuotaSpecified\": true, \"Importe\": \"371.20\", \"ImporteSpecified\": true } ], \"Retenciones\": null }, \"InformacionAduanera\": null, \"CuentaPredial\": null, \"ComplementoConcepto\": null, \"Parte\": null, \"ClaveProdServ\": \"50211503\", \"NoIdentificacion\": \"UT421511\", \"Cantidad\": 1, \"ClaveUnidad\": \"H87\", \"Unidad\": \"Pieza\", \"Descripcion\": \"Cigarros\", \"ValorUnitario\": \"200.00\", \"Importe\": \"200.00\", \"DescuentoSpecified\": false } ], \"Impuestos\": { \"Retenciones\": null, \"Traslados\": [ { \"Impuesto\": \"002\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"0.160000\", \"Importe\": \"32.00\" }, { \"Impuesto\": \"003\", \"TipoFactor\": \"Tasa\", \"TasaOCuota\": \"1.600000\", \"Importe\": \"371.20\" } ], \"TotalImpuestosRetenidosSpecified\": false, \"TotalImpuestosTrasladados\": \"403.20\", \"TotalImpuestosTrasladadosSpecified\": true }, \"Complemento\": null, \"Addenda\": null, \"Version\": \"3.3\", \"Serie\": \"RogueOne\", \"Folio\": \"HNFK231\", \"Fecha\": \"2018-08-13T10:44:54\", \"Sello\": \"Yd9/eWGA30vUKYywlCWBgC/Rogvc0/yv12n9SNyLuWSzCBSlBj0RNhZ/zW491lRnbCjAM10ImFSQS/qw83NB1qKpT6wfhErt9R67Bcvmgpc2hPROtJh3hL6SRa0TROOeIGYH7kFyvJ4q770R8v5kPUUAWG3uxaNvj+7uQ9ZCO+hAub+MKihCwBenbzacRXCn1Sfd2R8OD9qgIlHi5fur2ouZ6cfGeDWjPoptpEn5TmUQGYR1/zoP6ahxqA6IXyfxwRxHoQVTmrQVEN/4cMAMVDzc43YKEnFWl5xi1WmqvkuzaW5GfnGRkvOE+Tc5r8zFEn9qZxvEIQR16f7tFdzpiA==\", \"FormaPago\": \"01\", \"FormaPagoSpecified\": true, \"NoCertificado\": \"20001000000300022816\", \"Certificado\": \"MIIF0TCCA7mgAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTYwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTU0MTlaFw0yMDEwMjUyMTU0MTlaMIG9MR4wHAYDVQQDExVNQiBJREVBUyBESUdJVEFMRVMgU0MxHjAcBgNVBCkTFU1CIElERUFTIERJR0lUQUxFUyBTQzEeMBwGA1UEChMVTUIgSURFQVMgRElHSVRBTEVTIFNDMSUwIwYDVQQtExxMQU44NTA3MjY4SUEgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjHr4KeoEx3BdkQP93AuN4fKo0rCZQsd9RJGBzQFvhmPJjGaVP81OUORM+lCRllxZxATZCAIFPOT3jl5wYgtolGYWWrt1HoAiuja1LKDGKrYgph0qWYKYeuew10fTyV+AeSbx1jTKz1PAAak06hx4M0rvmdiGO/Kg00/0wKz5/L3ZIMXEj+Hgr0IGh/yUIy8m5aKf+9jwuNttm/xDoeW3A8pxuidPU1Z1vliaZs75n89hC9LNwshhoaF3AvXIsgLDeuh9WoMGSm0HrilP9umFnm3nGUESiJa15Ep7LbG4CIhZrrknSm4fyrPk9KAigqLYMJhRsRwfp2qncAnAA+FuSQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAd7t48tgawC9aczrGYt+4GFRcjj1LVKV3NElG+VH2s51KPkKPLj2Sw6OiEOGd+49spxHj1VR5MFvJo/pEJLY3EuLTifC9YZZYC8pHNDiA/eSvKqW5JNzp5/rgs3qAG1GrfdNGuSD3FkqhDdB6tJYqzTc12IC7xEAhKXrWZYCqa+zb9ogtzrUVL3vRRLMpnGEHK2yx8dhvG35qjHEfXyuoBsWILrVmnPpDCFO/CCLQB1OuMti1mlir6voBN0L1EbFK30w2bEuVihAeVLX8vVfMq4ZPI7UTLnblGnN11CCqiZkWhhehYrMdCjb5thMkEA+CMlIaFJYp7pNkLxQd4Y5+r8pTrdxxyvpA51DIWdoxvwaOiz1bzZk6ElVY2rfxwyZaJ17cJ1jmS4Yb5P4h8+5zkmZnPmRqfmaVO3nsApLWP6A38ZBrwwss429PJMSpfeXKGysPsqwF0yP3blsM7Cw53393LSHGKNm2GgG0kcrHnbbku6z6fjBdXMQQ5vjPuMNyw/pe3PzQLVoNOrD5AOoZmSG2TI3DtY4edLdiGmNQjo3MmAMMq4s7lr4AELPWAZRbnOlD1nEWGLdRp1mViteDvXwBL9E98EB4K9xK21DvgJ6rzw/D9rX6epeANfoXazWC0iCYcBNXiPikApcW73a/Jl/WjkEwEdkL/jLj0KCep58=\", \"CondicionesDePago\": null, \"SubTotal\": \"200.00\", \"DescuentoSpecified\": false, \"Moneda\": \"MXN\", \"TipoCambio\": \"1\", \"TipoCambioSpecified\": true, \"Total\": \"603.20\", \"TipoDeComprobante\": \"I\", \"MetodoPago\": \"PUE\", \"MetodoPagoSpecified\": true, \"LugarExpedicion\": \"06300\", \"Confirmacion\": null }";

	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV1Response response = null;
		response = (SuccessV1Response) api.IssueJson(testJson, "v1");
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
		response = (SuccessV2Response) api.IssueJson(testJson, "v2");

		System.out.println(response.message);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.tfd);
		System.out.println(response.cfdi);
		String expect_status = "success";
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}

	public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
		SWIssueService api = new SWIssueService("demo", "123456789", Utils.url_pruebas);
		SuccessV3Response response = null;
		response = (SuccessV3Response) api.IssueJson(testJson, "v3");
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
		String expect_error = "307. El comprobante contiene un timbre previo.";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status) || expect_error.equalsIgnoreCase(response.message));
	}
}