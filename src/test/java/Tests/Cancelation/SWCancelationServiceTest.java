package Tests.Cancelation;

import Services.Cancelation.SWCancelationService;
import Tests.Utils;
import Utils.Responses.Cancelation.CancelationResponse;
import org.junit.Assert;
import org.junit.Test;

public class SWCancelationServiceTest {

	public String uuid = "fe4e71b0-8959-4fb9-8091-f5ac4fb0fef8";
	public String password_csd = "12345678a";
	public String rfc = "EKU9003173C9";
	public String b64Cer = "MIIFsDCCA5igAwIBAgIUMzAwMDEwMDAwMDA1MDAwMDM0MTYwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWxpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMjMwNTE4MTE0MzUxWhcNMjcwNTE4MTE0MzUxWjCB1zEnMCUGA1UEAxMeRVNDVUVMQSBLRU1QRVIgVVJHQVRFIFNBIERFIENWMScwJQYDVQQpEx5FU0NVRUxBIEtFTVBFUiBVUkdBVEUgU0EgREUgQ1YxJzAlBgNVBAoTHkVTQ1VFTEEgS0VNUEVSIFVSR0FURSBTQSBERSBDVjElMCMGA1UELRMcRUtVOTAwMzE3M0M5IC8gVkFEQTgwMDkyN0RKMzEeMBwGA1UEBRMVIC8gVkFEQTgwMDkyN0hTUlNSTDA1MRMwEQYDVQQLEwpTdWN1cnNhbCAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmecO6n2GS0zL025gbHGQVxznPDICoXzR2uUngz4DqxVUC/w9cE6FxSiXm2ap8Gcjg7wmcZfm85EBaxCx/0J2u5CqnhzIoGCdhBPuhWQnIh5TLgj/X6uNquwZkKChbNe9aeFirU/JbyN7Egia9oKH9KZUsodiM/pWAH00PCtoKJ9OBcSHMq8Rqa3KKoBcfkg1ZrgueffwRLws9yOcRWLb02sDOPzGIm/jEFicVYt2Hw1qdRE5xmTZ7AGG0UHs+unkGjpCVeJ+BEBn0JPLWVvDKHZAQMj6s5Bku35+d/MyATkpOPsGT/VTnsouxekDfikJD1f7A1ZpJbqDpkJnss3vQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAFaUgj5PqgvJigNMgtrdXZnbPfVBbukAbW4OGnUhNrA7SRAAfv2BSGk16PI0nBOr7qF2mItmBnjgEwk+DTv8Zr7w5qp7vleC6dIsZFNJoa6ZndrE/f7KO1CYruLXr5gwEkIyGfJ9NwyIagvHHMszzyHiSZIA850fWtbqtythpAliJ2jF35M5pNS+YTkRB+T6L/c6m00ymN3q9lT1rB03YywxrLreRSFZOSrbwWfg34EJbHfbFXpCSVYdJRfiVdvHnewN0r5fUlPtR9stQHyuqewzdkyb5jTTw02D2cUfL57vlPStBj7SEi3uOWvLrsiDnnCIxRMYJ2UA2ktDKHk+zWnsDmaeleSzonv2CHW42yXYPCvWi88oE1DJNYLNkIjua7MxAnkNZbScNw01A6zbLsZ3y8G6eEYnxSTRfwjd8EP4kdiHNJftm7Z4iRU7HOVh79/lRWB+gd171s3d/mI9kte3MRy6V8MMEMCAnMboGpaooYwgAmwclI2XZCczNWXfhaWe0ZS5PmytD/GDpXzkX0oEgY9K/uYo5V77NdZbGAjmyi8cE2B2ogvyaN2XfIInrZPgEffJ4AB7kFA2mwesdLOCh0BLD9itmCve3A1FGR4+stO2ANUoiI3w3Tv2yQSg4bjeDlJ08lXaaFCLW2peEXMXjQUk7fmpb5MNuOUTW6BE=";
	public String b64Key = "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIAgEAAoIBAQACAggAMBQGCCqGSIb3DQMHBAgwggS/AgEAMASCBMh4EHl7aNSCaMDA1VlRoXCZ5UUmqErAbucoZQObOaLUEm+I+QZ7Y8Giupo+F1XWkLvAsdk/uZlJcTfKLJyJbJwsQYbSpLOCLataZ4O5MVnnmMbfG//NKJn9kSMvJQZhSwAwoGLYDm1ESGezrvZabgFJnoQv8Si1nAhVGTk9FkFBesxRzq07dmZYwFCnFSX4xt2fDHs1PMpQbeq83aL/PzLCce3kxbYSB5kQlzGtUYayiYXcu0cVRu228VwBLCD+2wTDDoCmRXtPesgrLKUR4WWWb5N2AqAU1mNDC+UEYsENAerOFXWnmwrcTAu5qyZ7GsBMTpipW4Dbou2yqQ0lpA/aB06n1kz1aL6mNqGPaJ+OqoFuc8Ugdhadd+MmjHfFzoI20SZ3b2geCsUMNCsAd6oXMsZdWm8lzjqCGWHFeol0ik/xHMQvuQkkeCsQ28PBxdnUgf7ZGer+TN+2ZLd2kvTBOk6pIVgy5yC6cZ+o1Tloql9hYGa6rT3xcMbXlW+9e5jM2MWXZliVW3ZhaPjptJFDbIfWxJPjz4QvKyJk0zok4muv13Iiwj2bCyefUTRz6psqI4cGaYm9JpscKO2RCJN8UluYGbbWmYQU+Int6LtZj/lv8p6xnVjWxYI+rBPdtkpfFYRp+MJiXjgPw5B6UGuoruv7+vHjOLHOotRo+RdjZt7NqL9dAJnl1Qb2jfW6+d7NYQSI/bAwxO0sk4taQIT6Gsu/8kfZOPC2xk9rphGqCSS/4q3Os0MMjA1bcJLyoWLp13pqhK6bmiiHw0BBXH4fbEp4xjSbpPx4tHXzbdn8oDsHKZkWh3pPC2J/nVl0k/yF1KDVowVtMDXE47k6TGVcBoqe8PDXCG9+vjRpzIidqNo5qebaUZu6riWMWzldz8x3Z/jLWXuDiM7/Yscn0Z2GIlfoeyz+GwP2eTdOw9EUedHjEQuJY32bq8LICimJ4Ht+zMJKUyhwVQyAER8byzQBwTYmYP5U0wdsyIFitphw+/IH8+v08Ia1iBLPQAeAvRfTTIFLCs8foyUrj5Zv2B/wTYIZy6ioUM+qADeXyo45uBLLqkN90Rf6kiTqDld78NxwsfyR5MxtJLVDFkmf2IMMJHTqSfhbi+7QJaC11OOUJTD0v9wo0X/oO5GvZhe0ZaGHnm9zqTopALuFEAxcaQlc4R81wjC4wrIrqWnbcl2dxiBtD73KW+wcC9ymsLf4I8BEmiN25lx/OUc1IHNyXZJYSFkEfaxCEZWKcnbiyf5sqFSSlEqZLc4lUPJFAoP6s1FHVcyO0odWqdadhRZLZC9RCzQgPlMRtji/OXy5phh7diOBZv5UYp5nb+MZ2NAB/eFXm2JLguxjvEstuvTDmZDUb6Uqv++RdhO5gvKf/AcwU38ifaHQ9uvRuDocYwVxZS2nr9rOwZ8nAh+P2o4e0tEXjxFKQGhxXYkn75H3hhfnFYjik/2qunHBBZfcdG148MaNP6DjX33M238T9Zw/GyGx00JMogr2pdP4JAErv9a5yt4YR41KGf8guSOUbOXVARw6+ybh7+meb7w4BeTlj3aZkv8tVGdfIt3lrwVnlbzhLjeQY6PplKp3/a5Kr5yM0T4wJoKQQ6v3vSNmrhpbuAtKxpMILe8CQoo=";
	public String xml = "<Cancelacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" Fecha=\"2023-07-18T14:08:11\" RfcEmisor=\"EKU9003173C9\" xmlns=\"http://cancelacfd.sat.gob.mx\"><Folios><Folio UUID=\"b6a15ce8-0fb8-401a-bfe7-8930983e182e\" Motivo=\"01\" FolioSustitucion=\"63187375-3433-4ae8-ad5a-3323872026fc\" /></Folios><Folios><Folio UUID=\"63187375-3433-4ae8-ad5a-3323872026fc\" Motivo=\"02\" FolioSustitucion=\"\" /></Folios><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\" /><DigestValue>fFUgI9QsXDrDhrdOzItm0PQKPi75eCBgg5ixjFUmAw0=</DigestValue></Reference></SignedInfo><SignatureValue>Q6Jb3f77e3be9QtQuAaoGkEsxmul91vreKyqMbQQzjNXonwgHcZ2L7fUZdDFMh2y6kwwN0Hh3/+wfNysnTFzkxNP+U+5KVgJtMkDs2knByylY+Sr16Nev1nWhaxJW8Z9pwAQ4Tly2Xc6a8C4uVcc7ZwpXp9eIPKpHBEK93wpUZjdK4PHCUElI8GkQ1Sq4Visyyx4HyBfB4vGP6u/7EgsVFWr7Ub6nyu1rvFyRMa5VhatOyVce9efRT0SnLHB7iWZH4EPbzqyx4tZuS641uKF4xtgwvfybNzpCQsKDi5hlNX9NtHHv61YdwmO2+VJ1+gn9Vf0sddEvj9o9F92zlFq5w==</SignatureValue><KeyInfo><X509Data><X509IssuerSerial><X509IssuerName>OID.1.2.840.113549.1.9.2=responsable: ACDMA-SAT, OID.2.5.4.45=2.5.4.45, L=COYOACAN, S=CIUDAD DE MEXICO, C=MX, PostalCode=06370, STREET=3ra cerrada de caliz, E=oscar.martinez@sat.gob.mx, OU=SAT-IES Authority, O=SERVICIO DE ADMINISTRACION TRIBUTARIA, CN=AC UAT</X509IssuerName><X509SerialNumber>292233162870206001759766198462772978647764840758</X509SerialNumber></X509IssuerSerial><X509Certificate>MIIFsDCCA5igAwIBAgIUMzAwMDEwMDAwMDA1MDAwMDM0MTYwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWxpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMjMwNTE4MTE0MzUxWhcNMjcwNTE4MTE0MzUxWjCB1zEnMCUGA1UEAxMeRVNDVUVMQSBLRU1QRVIgVVJHQVRFIFNBIERFIENWMScwJQYDVQQpEx5FU0NVRUxBIEtFTVBFUiBVUkdBVEUgU0EgREUgQ1YxJzAlBgNVBAoTHkVTQ1VFTEEgS0VNUEVSIFVSR0FURSBTQSBERSBDVjElMCMGA1UELRMcRUtVOTAwMzE3M0M5IC8gVkFEQTgwMDkyN0RKMzEeMBwGA1UEBRMVIC8gVkFEQTgwMDkyN0hTUlNSTDA1MRMwEQYDVQQLEwpTdWN1cnNhbCAxMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmecO6n2GS0zL025gbHGQVxznPDICoXzR2uUngz4DqxVUC/w9cE6FxSiXm2ap8Gcjg7wmcZfm85EBaxCx/0J2u5CqnhzIoGCdhBPuhWQnIh5TLgj/X6uNquwZkKChbNe9aeFirU/JbyN7Egia9oKH9KZUsodiM/pWAH00PCtoKJ9OBcSHMq8Rqa3KKoBcfkg1ZrgueffwRLws9yOcRWLb02sDOPzGIm/jEFicVYt2Hw1qdRE5xmTZ7AGG0UHs+unkGjpCVeJ+BEBn0JPLWVvDKHZAQMj6s5Bku35+d/MyATkpOPsGT/VTnsouxekDfikJD1f7A1ZpJbqDpkJnss3vQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAFaUgj5PqgvJigNMgtrdXZnbPfVBbukAbW4OGnUhNrA7SRAAfv2BSGk16PI0nBOr7qF2mItmBnjgEwk+DTv8Zr7w5qp7vleC6dIsZFNJoa6ZndrE/f7KO1CYruLXr5gwEkIyGfJ9NwyIagvHHMszzyHiSZIA850fWtbqtythpAliJ2jF35M5pNS+YTkRB+T6L/c6m00ymN3q9lT1rB03YywxrLreRSFZOSrbwWfg34EJbHfbFXpCSVYdJRfiVdvHnewN0r5fUlPtR9stQHyuqewzdkyb5jTTw02D2cUfL57vlPStBj7SEi3uOWvLrsiDnnCIxRMYJ2UA2ktDKHk+zWnsDmaeleSzonv2CHW42yXYPCvWi88oE1DJNYLNkIjua7MxAnkNZbScNw01A6zbLsZ3y8G6eEYnxSTRfwjd8EP4kdiHNJftm7Z4iRU7HOVh79/lRWB+gd171s3d/mI9kte3MRy6V8MMEMCAnMboGpaooYwgAmwclI2XZCczNWXfhaWe0ZS5PmytD/GDpXzkX0oEgY9K/uYo5V77NdZbGAjmyi8cE2B2ogvyaN2XfIInrZPgEffJ4AB7kFA2mwesdLOCh0BLD9itmCve3A1FGR4+stO2ANUoiI3w3Tv2yQSg4bjeDlJ08lXaaFCLW2peEXMXjQUk7fmpb5MNuOUTW6BE=</X509Certificate></X509Data></KeyInfo></Signature></Cancelacion>";
	public String b64Pfx = "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";
        public String motivo="01";
        public String foliosustitucion="0e4c30b8-11d8-40d8-894d-ef8b32eb4bdf";
	//csd
	@Test
	public void testCancelationServiceCSD_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW,  Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456", "123456", "123456","123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceCSD_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServiceCSD_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Cer, b64Key, motivo, foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServiceCSD_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW,  Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	//XML
	@Test
	public void testCancelationServiceXML_validXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW,  Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(xml);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceXML_invalidXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW,  Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("wrong xml");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}
	@Test
	public void testCancelationServiceXML_nullXML() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
		Assert.assertTrue(response.message.contains("CASD - Acuse"));
	}

	// Pfx
	@Test
	public void testCancelationServicePfx_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx,motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456", "123456", "123456","123456","123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServicePfx_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServicePfx_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, password_csd, rfc, b64Pfx, motivo, foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServicePfx_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 400;
		Assert.assertTrue(response.message.contains("CACFDI33"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}

	// Uuid
	@Test
	public void testCancelationServiceUuid_authUser() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.uuid);
		System.out.println(response.uuidStatusCode);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_authToken() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.acuse);
		System.out.println(response.messageDetail);
		System.out.println(response.message);
		String expect_status = "success";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_incorrectParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.tokenSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("123456", "123456","12345", "123456");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		String expect_status = "error";
		Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
	}
	@Test
	public void testCancelationServiceUuid_incorrectToken() throws Exception {
		SWCancelationService app = new SWCancelationService("wrong token", Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation(uuid, rfc, motivo, foliosustitucion);
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		Assert.assertTrue(response.HttpStatusCode == 401);
	}
	@Test
	public void testCancelationServiceUuid_emptyUserParams() throws Exception {
		try {
			SWCancelationService app = new SWCancelationService("", "", "");
			CancelationResponse response = null;
			response = (CancelationResponse) app.Cancelation(uuid, rfc, motivo, foliosustitucion);
			System.out.println(response.Status);
			System.out.println(response.HttpStatusCode);
			System.out.println(response.message);
			System.out.println(response.messageDetail);
		} catch (Exception e) {
			System.out.println("Something bad happened");
			System.out.println(e.getMessage());
			Assert.assertNotNull("Something bad happened", e);
		}
	}
	@Test
	public void testCancelationServiceUuid_emptyCancelationParams() throws Exception {
		SWCancelationService app = new SWCancelationService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
		CancelationResponse response = null;
		response = (CancelationResponse) app.Cancelation("", "", "", "");
		System.out.println(response.Status);
		System.out.println(response.HttpStatusCode);
		System.out.println(response.message);
		System.out.println(response.messageDetail);
		int expected_HttpStatusCode = 404;
		Assert.assertTrue(response.message.equalsIgnoreCase("Not Found"));
		Assert.assertTrue(expected_HttpStatusCode == response.HttpStatusCode);
	}
}
