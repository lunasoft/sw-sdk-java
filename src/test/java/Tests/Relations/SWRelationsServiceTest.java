package Tests.Relations;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Ignore;

import Services.Relations.SWRelationsService;
import Tests.Utils;
import Utils.Responses.Relations.CfdiRelacionadosResponse;
import Utils.Responses.Relations.RelacionData;

public class SWRelationsServiceTest {
@Ignore
	public void testCfdiRelacionadosService_CSD() throws Exception {
		SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CfdiRelacionadosResponse response = null;
		response = (CfdiRelacionadosResponse) app.CfdiRelacionadosCSD(Utils.uuid, Utils.passwordCsd, Utils.rfc, Utils.cerb64, Utils.keyb64);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.resultado);
		LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
		if (padres != null) {
			for (int i = 0; i < padres.size(); i++) {
				RelacionData datos = padres.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
		if (hijos != null) {
			for (int i = 0; i < hijos.size(); i++) {
				RelacionData datos = hijos.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testCfdiRelacionadosService_Pfx() throws Exception {
		SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CfdiRelacionadosResponse response = null;
		response = (CfdiRelacionadosResponse) app.CfdiRelacionadosPFX(Utils.uuid, Utils.passwordPfx, Utils.rfc, Utils.pfxb64);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.resultado);
		LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
		if (padres != null) {
			for (int i = 0; i < padres.size(); i++) {
				RelacionData datos = padres.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
		if (hijos != null) {
			for (int i = 0; i < hijos.size(); i++) {
				RelacionData datos = hijos.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testCfdiRelacionadosService_Xml() throws Exception {
		SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CfdiRelacionadosResponse response = null;
		String xmlR = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <PeticionConsultaRelacionados xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Uuid=\"51BADE4D-8285-4597-A092-7DB1D50E5EFD\" RfcReceptor=\"LAN7008173R5\" RfcPacEnviaSolicitud=\"DAL050601L35\" xmlns=\"http://cancelacfd.sat.gob.mx\"> <Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"> <SignedInfo> <CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /> <SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /> <Reference URI=\"\"> <Transforms> <Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /> </Transforms> <DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /> <DigestValue>yYGkb9DCJgiGl2O4vCf5B3gXTTI=</DigestValue> </Reference> </SignedInfo> <SignatureValue>VBBjMXJgS/oCb4iTazKrPmhWSICGT5wbeTf8G4tW2UuqnKBLS1NWD7Uf37kAX8+GBB04So7YlTcEw3I/X2JkHDadSxCiZ940YksNIVddmCqllJL6giMHVQJoXcTH8WQ9pO/4TbREQZ8/jxPqIvxCXrOn963PKFrZFB8eo5RQxLUa12WMi5RWgh8dSUwQxS2W3dm1XXP8bqXPOjy7GtZc3ObeTLMcXo/YoLyEAobVCnP+igOEXLxKEN2HZPzHGtA2g/5ONxuhu3UTxix9D/5ItjXdH9nk7VL0A58Xgw3qv6Q0vjmlxyu7RO0E2O3D2tLejfExt3WvsjZ8xvEKXSFp+A==</SignatureValue> <KeyInfo> <X509Data> <X509IssuerSerial> <X509IssuerName>OID.1.2.840.113549.1.9.2=Responsable: ACDMA, OID.2.5.4.45=SAT970701NN3, L=Coyoacán, S=Distrito Federal, C=MX, PostalCode=06300, STREET=\"Av. Hidalgo 77, Col. Guerrero\", E=asisnet@pruebas.sat.gob.mx, OU=Administración de Seguridad de la Información, O=Servicio de Administración Tributaria, CN=A.C. 2 de pruebas(4096)</X509IssuerName> <X509SerialNumber>3230303031303030303030333030303232383135</X509SerialNumber> </X509IssuerSerial> <X509Certificate>MIIFxTCCA62gAwIBAgIUMjAwMDEwMDAwMDAzMDAwMjI4MTUwDQYJKoZIhvcNAQELBQAwggFmMSAwHgYDVQQDDBdBLkMuIDIgZGUgcHJ1ZWJhcyg0MDk2KTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMSkwJwYJKoZIhvcNAQkBFhphc2lzbmV0QHBydWViYXMuc2F0LmdvYi5teDEmMCQGA1UECQwdQXYuIEhpZGFsZ28gNzcsIENvbC4gR3VlcnJlcm8xDjAMBgNVBBEMBTA2MzAwMQswCQYDVQQGEwJNWDEZMBcGA1UECAwQRGlzdHJpdG8gRmVkZXJhbDESMBAGA1UEBwwJQ295b2Fjw6FuMRUwEwYDVQQtEwxTQVQ5NzA3MDFOTjMxITAfBgkqhkiG9w0BCQIMElJlc3BvbnNhYmxlOiBBQ0RNQTAeFw0xNjEwMjUyMTUyMTFaFw0yMDEwMjUyMTUyMTFaMIGxMRowGAYDVQQDExFDSU5ERU1FWCBTQSBERSBDVjEaMBgGA1UEKRMRQ0lOREVNRVggU0EgREUgQ1YxGjAYBgNVBAoTEUNJTkRFTUVYIFNBIERFIENWMSUwIwYDVQQtExxMQU43MDA4MTczUjUgLyBGVUFCNzcwMTE3QlhBMR4wHAYDVQQFExUgLyBGVUFCNzcwMTE3TURGUk5OMDkxFDASBgNVBAsUC1BydWViYV9DRkRJMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgvvCiCFDFVaYX7xdVRhp/38ULWto/LKDSZy1yrXKpaqFXqERJWF78YHKf3N5GBoXgzwFPuDX+5kvY5wtYNxx/Owu2shNZqFFh6EKsysQMeP5rz6kE1gFYenaPEUP9zj+h0bL3xR5aqoTsqGF24mKBLoiaK44pXBzGzgsxZishVJVM6XbzNJVonEUNbI25DhgWAd86f2aU3BmOH2K1RZx41dtTT56UsszJls4tPFODr/caWuZEuUvLp1M3nj7Dyu88mhD2f+1fA/g7kzcU/1tcpFXF/rIy93APvkU72jwvkrnprzs+SnG81+/F16ahuGsb2EZ88dKHwqxEkwzhMyTbQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAJ/xkL8I+fpilZP+9aO8n93+20XxVomLJjeSL+Ng2ErL2GgatpLuN5JknFBkZAhxVIgMaTS23zzk1RLtRaYvH83lBH5E+M+kEjFGp14Fne1iV2Pm3vL4jeLmzHgY1Kf5HmeVrrp4PU7WQg16VpyHaJ/eonPNiEBUjcyQ1iFfkzJmnSJvDGtfQK2TiEolDJApYv0OWdm4is9Bsfi9j6lI9/T6MNZ+/LM2L/t72Vau4r7m94JDEzaO3A0wHAtQ97fjBfBiO5M8AEISAV7eZidIl3iaJJHkQbBYiiW2gikreUZKPUX0HmlnIqqQcBJhWKRu6Nqk6aZBTETLLpGrvF9OArV1JSsbdw/ZH+P88RAt5em5/gjwwtFlNHyiKG5w+UFpaZOK3gZP0su0sa6dlPeQ9EL4JlFkGqQCgSQ+NOsXqaOavgoP5VLykLwuGnwIUnuhBTVeDbzpgrg9LuF5dYp/zs+Y9ScJqe5VMAagLSYTShNtN8luV7LvxF9pgWwZdcM7lUwqJmUddCiZqdngg3vzTactMToG16gZA4CWnMgbU4E+r541+FNMpgAZNvs2CiW/eApfaaQojsZEAHDsDv4L5n3M1CC7fYjE/d61aSng1LaO6T1mh+dEfPvLzp7zyzz+UgWMhi5Cs4pcXx1eic5r7uxPoBwcCTt3YI1jKVVnV7/w=</X509Certificate> </X509Data> </KeyInfo> </Signature> </PeticionConsultaRelacionados>";
		response = (CfdiRelacionadosResponse) app.CfdiRelacionadosXML(xmlR);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.resultado);
		LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
		if (padres != null) {
			for (int i = 0; i < padres.size(); i++) {
				RelacionData datos = padres.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
		if (hijos != null) {
			for (int i = 0; i < hijos.size(); i++) {
				RelacionData datos = hijos.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Ignore
	public void testCfdiRelacionadosService_UUID() throws Exception {
		SWRelationsService app = new SWRelationsService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CfdiRelacionadosResponse response = null;
		response = (CfdiRelacionadosResponse) app.CfdiRelacionadosUUID(Utils.uuid, Utils.rfc);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.resultado);
		LinkedList<RelacionData> padres = (LinkedList<RelacionData>) response.uuidsRelacionadosPadres;
		if (padres != null) {
			for (int i = 0; i < padres.size(); i++) {
				RelacionData datos = padres.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		LinkedList<RelacionData> hijos = (LinkedList<RelacionData>) response.uuidsRelacionadosHijos;
		if (hijos != null) {
			for (int i = 0; i < hijos.size(); i++) {
				RelacionData datos = hijos.get(i);
				System.out.println(datos.uuid);
				System.out.println(datos.rfcEmisor);
				System.out.println(datos.rfcReceptor);
			}
		}
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}

}
