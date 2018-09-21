package Tests.StatusCfdi;

import org.junit.Assert;

import Services.StatusCfdi.StatusCfdiService;
import Utils.Responses.StatusCfdi.StatusCfdiResponse;
import junit.framework.TestCase;

public class StatusCfdiServiceTest extends TestCase{
	public void testStatusCancelationService_Real() throws Exception {
		StatusCfdiService app = new StatusCfdiService("https://consultaqrfacturaelectronicatest.sw.com.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");
		StatusCfdiResponse response = null;
		response = (StatusCfdiResponse) app.StatusCfdi("LAN7008173R5", "XAXX010101000", "34800.00", "25312274-1cd6-4468-85f1-d150764779bf");
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
