package Tests.AcceptReject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Ignore;
import Services.AcceptReject.SWAcceptRejectService;
import Tests.Utils;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;

public class SWAcceptRejectServiceTest {
@Ignore
	public void testAcceptrejectCancelationService_CSD() throws Exception {
		SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		Map<String, String> uuids = new HashMap<String, String>();
		uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
		AceptarRechazarCancelationResponse response = null;
		response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionCSD(uuids, Utils.passwordCsd, Utils.rfc,
				Utils.cerb64, Utils.keyb64);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		LinkedList<CancelationData> folios = (LinkedList<CancelationData>) response.folios;
		if (folios != null) {
			for (int i = 0; i < folios.size(); i++) {
				CancelationData datos = folios.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.estatusUUID);
				System.out.println(datos.respuesta);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	
	@Ignore
	public void testAcceptrejectCancelationService_XML() throws Exception {
		SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		AceptarRechazarCancelationResponse response = null;
		response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionXML(Utils.aceptacionRechazoXml);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		List<CancelationData> folios = response.folios;
		if (folios != null) {
			for (int i = 0; i < folios.size(); i++) {
				CancelationData datos = folios.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.estatusUUID);
				System.out.println(datos.respuesta);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	
	@Ignore
	public void testAcceptrejectCancelationService_PFX() throws Exception {
		SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		Map<String, String> uuids = new HashMap<String, String>();
        uuids.put("06a46e4b-b154-4c12-bb77-f9a63ed55ff2", "Aceptacion");
		AceptarRechazarCancelationResponse response = null;
		response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionPFX(uuids, Utils.passwordPfx, Utils.rfc, Utils.pfxb64);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		LinkedList<CancelationData> folios = (LinkedList<CancelationData>) response.folios;
		if (folios != null) {
			for (int i = 0; i < folios.size(); i++) {
				CancelationData datos = folios.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.estatusUUID);
				System.out.println(datos.respuesta);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	
	@Ignore
	public void testAcceptrejectCancelationService_UUID() throws Exception {
		SWAcceptRejectService app = new SWAcceptRejectService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		String uuidC = "06a46e4b-b154-4c12-bb77-f9a63ed55ff2";
		AceptarRechazarCancelationResponse response = null;
		response = (AceptarRechazarCancelationResponse) app.AceptarRechazarCancelacionUUID(uuidC, Utils.rfc, "Aceptacion");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		LinkedList<CancelationData> folios = (LinkedList<CancelationData>) response.folios;
		if (folios != null) {
			for (int i = 0; i < folios.size(); i++) {
				CancelationData datos = folios.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.estatusUUID);
				System.out.println(datos.respuesta);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
