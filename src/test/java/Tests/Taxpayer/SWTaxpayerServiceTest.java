package Tests.Taxpayer;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import Services.Taxpayer.SWTaxpayerService;
import Tests.Utils;
import Utils.Responses.Taxpayer.TaxpayerResponse;

public class SWTaxpayerServiceTest {
	public String rigthRfc = "ZNS1101105T3";
	public String wrongRfc = "ZNS1101105T4";	

	@Ignore
	public void SWTaxpayerServiceTestRigthRfc_authToken() throws Exception {
		SWTaxpayerService app = new SWTaxpayerService(Utils.tokenSW, Utils.urlSW);
		TaxpayerResponse response = null;
		response = (TaxpayerResponse) app.Taxpayer(rigthRfc);
		
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.rfc);
		System.out.println(response.nombre_Contribuyente);
		System.out.println(response.situacion_del_contribuyente);
		System.out.println(response.numero_y_fecha_oficio_global_presuncion);
		System.out.println(response.publicacion_pagina_SAT_presuntos);
		System.out.println(response.publicacion_DOF_presuntos);
		System.out.println(response.publicacion_pagina_SAT_desvirtuados);
		System.out.println(response.numero_fecha_oficio_global_contribuyentes_que_desvirtuaron);
		System.out.println(response.publicacion_DOF_desvirtuados);
		System.out.println(response.numero_fecha_oficio_global_definitivos);
		System.out.println(response.publicacion_pagina_SAT_definitivos);
		System.out.println(response.publicacion_DOF_definitivos);
		System.out.println(response.numero_fecha_oficio_global_sentencia_favorable);
		System.out.println(response.publicacion_pagina_SAT_sentencia_favorable);
		System.out.println(response.publicacion_DOF_sentencia_favorable);
		
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

	@Test
	public void SWTaxpayerServiceTestWrongRfc_authToken() throws Exception {
		SWTaxpayerService app = new SWTaxpayerService(Utils.tokenSW, Utils.urlSW);
		TaxpayerResponse response = null;
		response = (TaxpayerResponse) app.Taxpayer(wrongRfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void SWTaxpayerServiceTestWrongRfc_authCredentials() throws Exception {
		SWTaxpayerService app = new SWTaxpayerService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		TaxpayerResponse response = null;
		response = (TaxpayerResponse) app.Taxpayer(wrongRfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void SWTaxpayerServiceTestRigthRfc_authCredentials() throws Exception {
		SWTaxpayerService app = new SWTaxpayerService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		TaxpayerResponse response = null;
		response = (TaxpayerResponse) app.Taxpayer(rigthRfc);
		
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.rfc);
		System.out.println(response.nombre_Contribuyente);
		System.out.println(response.situacion_del_contribuyente);
		System.out.println(response.numero_y_fecha_oficio_global_presuncion);
		System.out.println(response.publicacion_pagina_SAT_presuntos);
		System.out.println(response.publicacion_DOF_presuntos);
		System.out.println(response.publicacion_pagina_SAT_desvirtuados);
		System.out.println(response.numero_fecha_oficio_global_contribuyentes_que_desvirtuaron);
		System.out.println(response.publicacion_DOF_desvirtuados);
		System.out.println(response.numero_fecha_oficio_global_definitivos);
		System.out.println(response.publicacion_pagina_SAT_definitivos);
		System.out.println(response.publicacion_DOF_definitivos);
		System.out.println(response.numero_fecha_oficio_global_sentencia_favorable);
		System.out.println(response.publicacion_pagina_SAT_sentencia_favorable);
		System.out.println(response.publicacion_DOF_sentencia_favorable);
		
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
}
