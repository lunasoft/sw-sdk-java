package Tests.StatusCfdi;

import org.junit.Assert;

import Services.StatusCfdi.StatusCfdiService;
import Utils.Responses.StatusCfdi.StatusCfdiResponse;
import junit.framework.TestCase;

public class StatusCfdiServiceTest extends TestCase{
	public void testStatusCancelationService_Real() throws Exception {
		StatusCfdiService app = new StatusCfdiService("https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");
		StatusCfdiResponse response = null;
		response = (StatusCfdiResponse) app.StatusCfdi("LSO1306189R5", "GACJ940911ASA", "4999.99", "e7df3047-f8de-425d-b469-37abe5b4dabb");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.codigoEstatus);
		System.out.println(response.estado);
		System.out.println(response.esCancelable);
		System.out.println(response.estatusCancelacion);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	public void testStatusCancelationService_Test() throws Exception {
		StatusCfdiService app = new StatusCfdiService("https://pruebacfdiconsultaqr.cloudapp.net/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");
		StatusCfdiResponse response = null;
		response = (StatusCfdiResponse) app.StatusCfdi("LAN8507268IA", "LAN7008173R5", "5800.00", "6ab07bef-4446-43ea-a3fd-04a804309457");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.codigoEstatus);
		System.out.println(response.estado);
		System.out.println(response.esCancelable);
		System.out.println(response.estatusCancelacion);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
