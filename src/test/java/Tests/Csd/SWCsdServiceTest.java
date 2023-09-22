package Tests.Csd;

import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import Services.Csd.SWCsdService;
import Tests.Utils;
import Utils.Responses.Csd.CsdResponse;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.InfoCsdResponse;
import Utils.Responses.Csd.ListInfoCsdResponse;

public class SWCsdServiceTest {
	@Ignore
	public void testCargaCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CsdResponse response = null;
		response = (CsdResponse) app.UploadMyCsd(Utils.getCertificadoB64(), Utils.getLlaveB64(), Utils.passwordCsd, "stamp", true);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testCargaCsd_authToken() throws Exception {
		SWCsdService app = new SWCsdService(Utils.tokenSW, Utils.urlSW);
		CsdResponse response = null;
		response = (CsdResponse) app.UploadMyCsd(Utils.cerb64, Utils.keyb64, Utils.passwordCsd, "stamp", true);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testDisableCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CsdResponse response = null;
		response = (CsdResponse) app.DisableMyCsd("20001000000300022763");
		System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testGetListCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		ListInfoCsdResponse response = null;
		response = (ListInfoCsdResponse) app.GetListCsd();
		System.out.println(response.HttpStatusCode);
		List<InfoCsd> lista = response.data;
		if(lista != null) {
			for(int i=0; i<lista.size(); i++) {
				InfoCsd dato = lista.get(i);
				System.out.println("Número certificado: " + dato.certificateNumber);
				System.out.println("Tipo certificado: " + dato.certificateType);
				System.out.println("Está activo?: " + dato.isActive);
				System.out.println("Razón social: " + dato.issuerBussinesName);
				System.out.println("Rfc: " + dato.issuerRfc);
				System.out.println("Valido desde: " + dato.validFrom);
				System.out.println("Valido hasta: " + dato.validTo + "\n");
			}
		}
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testGetListCsdByType_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		ListInfoCsdResponse response = null;
		response = (ListInfoCsdResponse) app.GetListCsdByType("stamp");
		System.out.println(response.HttpStatusCode);
		List<InfoCsd> lista = response.data;
		if(lista != null) {
			for(int i=0; i<lista.size(); i++) {
				InfoCsd dato = lista.get(i);
				System.out.println("Número certificado: " + dato.certificateNumber);
				System.out.println("Tipo certificado: " + dato.certificateType);
				System.out.println("Está activo?: " + dato.isActive);
				System.out.println("Razón social: " + dato.issuerBussinesName);
				System.out.println("Rfc: " + dato.issuerRfc);
				System.out.println("Valido desde: " + dato.validFrom);
				System.out.println("Valido hasta: " + dato.validTo + "\n");
			}
		}
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testGetListCsdByRfc_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		ListInfoCsdResponse response = null;
		response = (ListInfoCsdResponse) app.GetListCsdByRfc(Utils.rfc);
		System.out.println(response.HttpStatusCode);
		List<InfoCsd> lista = response.data;
		if(lista != null) {
			for(int i=0; i<lista.size(); i++) {
				InfoCsd dato = lista.get(i);
				System.out.println("Número certificado: " + dato.certificateNumber);
				System.out.println("Tipo certificado: " + dato.certificateType);
				System.out.println("Está activo?: " + dato.isActive);
				System.out.println("Razón social: " + dato.issuerBussinesName);
				System.out.println("Rfc: " + dato.issuerRfc);
				System.out.println("Valido desde: " + dato.validFrom);
				System.out.println("Valido hasta: " + dato.validTo + "\n");
			}
		}
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testSearchMyCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		InfoCsdResponse response = null;
		response = (InfoCsdResponse) app.SearchMyCsd(Utils.noCer);
		InfoCsd dato = response.data;
		System.out.println(response.HttpStatusCode);
		System.out.println("Número certificado: " + dato.certificateNumber);
		System.out.println("Tipo certificado: " + dato.certificateType);
		System.out.println("Está activo?: " + dato.isActive);
		System.out.println("Razón social: " + dato.issuerBussinesName);
		System.out.println("Rfc: " + dato.issuerRfc);
		System.out.println("Valido desde: " + dato.validFrom);
		System.out.println("Valido hasta: " + dato.validTo);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testSearchActiveCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		InfoCsdResponse response = null;
		response = (InfoCsdResponse) app.SearchActiveCsd(Utils.rfc, "stamp");
		InfoCsd dato = response.data;
		System.out.println(response.HttpStatusCode);
		System.out.println("Número certificado: " + dato.certificateNumber);
		System.out.println("Tipo certificado: " + dato.certificateType);
		System.out.println("Está activo?: " + dato.isActive);
		System.out.println("Razón social: " + dato.issuerBussinesName);
		System.out.println("Rfc: " + dato.issuerRfc);
		System.out.println("Valido desde: " + dato.validFrom);
		System.out.println("Valido hasta: " + dato.validTo);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
