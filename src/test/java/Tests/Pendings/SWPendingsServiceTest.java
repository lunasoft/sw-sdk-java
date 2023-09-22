package Tests.Pendings;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;

import Services.Pendings.SWPendingsService;
import Tests.Utils;
import Utils.Responses.Pendings.PendientesCancelarResponse;

public class SWPendingsServiceTest {
	@Ignore
	public void testPendientesCancelarService() throws Exception {
		SWPendingsService app = new SWPendingsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		PendientesCancelarResponse response = null;
		response = (PendientesCancelarResponse) app.PendientesPorCancelar(Utils.rfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		List<String> uuids = (LinkedList<String>) response.UUIDS;
		if (uuids != null) {
			for (int i = 0; i < uuids.size(); i++) {
				String datos = uuids.get(i);
				System.out.println(datos);
			}
		}
		System.out.println(response.CodStatus);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
