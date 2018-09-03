package Tests.StatusCfdi;

import org.junit.Assert;

import Services.StatusCfdi.StatusCfdiService;
import Utils.Responses.StatusCfdi.StatusCfdiResponse;
import junit.framework.TestCase;

public class StatusCfdiServiceTest extends TestCase{
	public void testStatusCancelationService_Real() throws Exception {
		StatusCfdiService app = new StatusCfdiService("https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", "http://tempuri.org/IConsultaCFDIService/Consulta");
		StatusCfdiResponse response = null;
		response = (StatusCfdiResponse) app.StatusCfdi("LSO1306189R5", "LSO1306189R5", "1.16", "E0AAE6B3-43CC-4B9C-B229-7E221000E2BB");
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
