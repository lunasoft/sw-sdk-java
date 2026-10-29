package Tests.StatusCfdi;

import org.junit.Assert;
import org.junit.Test;

import Services.StatusCfdi.StatusCfdiService;
import Utils.Responses.StatusCfdi.StatusCfdiResponse;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConnectionFactory;

/**
 * Verifica que el stack SOAP de la consulta de estatus carga y funciona en
 * Java 8. Con versiones de jakarta.xml.soap-api o saaj-impl compiladas para
 * Java 11 falla con UnsupportedClassVersionError.
 */
public class StatusCfdiSoapTest {
	@Test
	public void testSoapStackLoads() throws Exception {
		Assert.assertNotNull(SOAPConnectionFactory.newInstance());
		Assert.assertNotNull(MessageFactory.newInstance().createMessage());
	}

	@Test
	public void testStatusCfdiServiceTestEndpoint() throws Exception {
		StatusCfdiService app = new StatusCfdiService("https://api.test.sw.com.mx/ConsultaCFDIService.svc",
				"http://tempuri.org/IConsultaCFDIService/Consulta");
		StatusCfdiResponse response = (StatusCfdiResponse) app.StatusCfdi("EKU9003173C9", "XEXX010101000", "11.48",
				"3eeb3400-4c53-454c-91c7-caf05a7f111b", "kRLGkg==");
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.codigoEstatus);
		Assert.assertFalse(response.codigoEstatus.isEmpty());
	}
}
