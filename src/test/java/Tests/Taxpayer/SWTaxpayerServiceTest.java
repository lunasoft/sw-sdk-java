package Tests.Taxpayer;

import org.junit.Assert;
import org.junit.Test;
import Services.Taxpayer.SWTaxpayerService;
import Tests.Utils;
import Utils.Responses.Taxpayer.TaxpayerResponse;

public class SWTaxpayerServiceTest {
	public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrUndzK0dHS2dpTittY1JmR3o2M3NqNkh4MW9KVXMvUHhZYzVLQS9UK2E1SVhEZFJKYWx4ZmlEWDFuSXlqc2ZRYXlUQk1ldlZkU2tEdU10NFVMdHZKUURLblBxakw0SDl5bUxabDFLNmNPbEp6b3Jtd2Q1V2htRHlTdDZ6eTFRdUNnYnVvK2tuVUdhMmwrVWRCZi9rQkU9.7k2gVCGSZKLzJK5Ky3Nr5tKxvGSJhL13Q8W-YhT0uIo";
	public String rigthRfc = "ZNS1101105T3";
	public String wrongRfc = "ZNS1101105T4";
	public String User = "demo";
	public String Pass = "123456789";
	

	@Test
	public void SWTaxpayerServiceTestRigthRfc_authToken() throws Exception {
		SWTaxpayerService app = new SWTaxpayerService(token, Utils.url_pruebas);
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
		SWTaxpayerService app = new SWTaxpayerService(token, Utils.url_pruebas);
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
		SWTaxpayerService app = new SWTaxpayerService(User,Pass, Utils.url_pruebas);
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
		SWTaxpayerService app = new SWTaxpayerService(User,Pass, Utils.url_pruebas);
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
