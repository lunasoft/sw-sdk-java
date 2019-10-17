package Tests.Csd;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import Services.Csd.SWCsdService;
import Tests.Utils;
import Utils.Responses.Csd.CsdResponse;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.InfoCsdResponse;
import Utils.Responses.Csd.ListInfoCsdResponse;

public class SWCsdServiceTest {
	public String password_csd = "12345678a";
	public String rfc = "LAN7008173R5";
	public String b64Cer = "MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=";
	public String b64Key = "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIAgEAAoIBAQACAggAMBQGCCqGSIb3DQMHBAgwggS9AgEAMASCBMh4EHl7aNSCaMDA1VlRoXCZ5UUmqErAbucRBAKNQXH8t1GNfLDIQejtcocS39VvWnpNXjZJeCg65Y2wI36UGn78gvnU0NOmyUkXksPVrkz7hqNtAVojPUtN65l+MVAsIRVD6OLJeKZ2bLx5z78zrx6Tp1zCGT/NpxL+CJSy5iY6TKqbJcK/9198noOvT2p8rKVqUUF3wLRvD6R/b3BC5wCon/exp3BUTZeiWJqGRRgaW4rn49ZbJPVIcDmUO8mojPesFHjJDSnA0nBnWaUvTYXi0srT+dLZOewsBR8d5GdSWh9ZkM29wJbjYHCMsXkObZjaap3YM8fU29zRyZ8KAqaCnBHCfYjbib56m+Lmnk+ScqMkQQ+S/+2pzn2LzauvBI4p/OjQgBDeblo22X7sX9OA9YaqB3q6CCjQ5tkDNrz3HOgTm+amh/kI8TEn9rcKf4Ru7mC1T7VMaFgBqpIS8YJNbcgegF0IF1FpCS05wjdU5CktYAnPnvC+Pj+MFDeH+184kIHBWqPNG6dAzALxRgtKTlGdJ1l5Do+4EWI+0mvKojREnKoDczFnDeCFnM51u3I9Vce3rkf0djRQKFomPVUnPDqxlR5lDAssYAYNcECAkvGxKcBDbjWi/6NHlwjS1r28+0Jhvfxjx9O6hi4AW82Q2/kBE5P/eOwln/jKSbLgi7Iyim1FFHxkQH1FY5kcKhAzFcIq85rGFlzHRfPF9OIQSmONI9kcWQCxkk8aG1u1zwbjZRYLTxlwmZvynOgaWRpTN8Y4ReBDIG1klhva7nqqoM416oXBG71IKaCtjAwRlE6pgaqnIz/WQAb2FR541pqynX6dB6DB1nIWnatsWZJZlu+Bnhf9DBlUsO9ZSAf9Fa9nJAzwFCzaKIsvGJIeKSZ/h+vInkjaO/rxswErVROTfZy1lO2CJ/xnAgzFGrpDxNJPliv3McO9TGwYy/zHhE6Pdo8Xu6NsMisNU6TB8Bc26uLNv/7kWhNmNnBA1qt5akln6hOHrPBXGBiTNUL0IoFVPNdCbS0834zAYXfgtZLDzVpeLqmeMpqXbIYK0/NXe9etxuOcN40O+B/fTHHmO7dMgBZ4vAApVQUPr7ilumVHsWSMRP/0p5R9q4qr1bDm9S5YCPevdyYWTSceGSrXHmjYzJLBtpc/s77mynNqZEYjhnKk2XRNp6kp/FYRu+QdsX9vaDJbLKR2EnSC4fU6UOTO03IZU15j3wOsg30QrXoKntSJ/beF99cvFHuPrQPWxCtws0lLwkkHNVOm6XNO948Moy1w1pL4i68CwmceYZaYrYhmHGdLuescFQrZQaULDWhpK2Stys8Vs/XwwxNi9MHAFSXpdy/b+Aro5n87w+0MHRcllF8ZKbtQ/ym4oG7aREuo7o71JXJQPjZKTOtVM1EQx/FLM/5brnDSoyvLtoYtv9/tTnIC+8gR6eErkzaGmn8pftPhGNuz6yzx8JeLFoMD7VWbGTefj46KS+yMweFJnpReHEqwnukXpEYq19EWVyQa/Sb7navtKt80y/vRs0aNZp3iL23AOs0u1kQ1CFNY2y12Gor1koaH2FUd5jAQnaSKmgarLy0H/QVvR2g8B3+Fh49QhKYrd8N6LvvI80cwbEoqYWn5DWA=";
	public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrUndzK0dHS2dpTittY1JmR3o2M3NqNkh4MW9KVXMvUHhZYzVLQS9UK2E1SVhEZFJKYWx4ZmlEWDFuSXlqc2ZRYXlUQk1ldlZkU2tEdU10NFVMdHZKUURLblBxakw0SDl5bUxabDFLNmNPbEp6b3Jtd2Q1V2htRHlTdDZ6eTFRdUNnYnVvK2tuVUdhMmwrVWRCZi9rQkU9.7k2gVCGSZKLzJK5Ky3Nr5tKxvGSJhL13Q8W-YhT0uIo";
	@Test
	public void testCargaCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
		CsdResponse response = null;
		response = (CsdResponse) app.UploadMyCsd(b64Cer, b64Key, password_csd, "stamp", true);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCargaCsd_authToken() throws Exception {
		SWCsdService app = new SWCsdService(token, Utils.url_pruebas);
		CsdResponse response = null;
		response = (CsdResponse) app.UploadMyCsd(b64Cer, b64Key, password_csd, "stamp", true);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.data);
		System.out.println(response.Status);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testDisableCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
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
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
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
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
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
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
		ListInfoCsdResponse response = null;
		response = (ListInfoCsdResponse) app.GetListCsdByRfc("LAN8507268IA");
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
	public void testSearchMyCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
		InfoCsdResponse response = null;
		response = (InfoCsdResponse) app.SearchMyCsd("20001000000300022816");
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
	@Test
	public void testSearchActiveCsd_authUser() throws Exception {
		SWCsdService app = new SWCsdService("demo", "123456789", Utils.url_pruebas);
		InfoCsdResponse response = null;
		response = (InfoCsdResponse) app.SearchActiveCsd("LAN8507268IA", "stamp");
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
