package Tests.Stamp;

import java.text.Normalizer;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import Services.Stamp.SWStampServiceV4;
import Tests.Utils;
import Utils.Responses.Stamp.SuccessV1Response;
import Utils.Responses.Stamp.SuccessV2Response;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

public class SWStampServiceTestV4 {
    static Utils ut = new Utils();

    @Test
    public void testStampReal_ISSUEV4_USER_PASSWORD_V1() throws Exception {
        SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        SuccessV1Response response = null;
        String[] mails = { "prueba@test.com" };
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        response = (SuccessV1Response) api.Stamp(ut.StringgenBasico(false), "v1", mails, uuidString, true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.message);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    @Test
    public void testStampReal_ISSUEV4_USER_PASSWORD_V2() throws Exception {
        SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        SuccessV2Response response = null;
        String[] mails = { "prueba@test.com" };
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        response = (SuccessV2Response) api.Stamp(ut.StringgenBasico(false), "v2", mails, uuidString, true);
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    @Test
    public void testStampReal_ISSUEV4_USER_PASSWORD_V3() throws Exception {
        SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        SuccessV3Response response = null;
        String[] mails = { "prueba@test.com" };
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        response = (SuccessV3Response) api.Stamp(ut.StringgenBasico(false), "v3", mails, uuidString, true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        System.out.println(response.message);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    @Test
    public void testStampReal_ISSUEV4_USER_PASSWORD_V4() throws Exception {
        SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        SuccessV4Response response = null;
        String[] mails = { "prueba@test.com" };
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        response = (SuccessV4Response) api.Stamp(ut.StringgenBasico(false), "v4", mails, uuidString, true);
        System.out.println(response.message);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        System.out.println(response.message);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }

    @Test
    public void testStampReal_ISSUEV4_INVALID_EMAIL() throws Exception {
        try {
            SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            SuccessV1Response response = null;
            String[] mails = { "" };
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            response = (SuccessV1Response) api.Stamp(ut.StringgenBasico(false), "v1", mails, uuidString, true);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
        } catch (Exception e) {
            String messageResponse = normalizeString(e.getMessage());
            Assert.assertEquals(
                    "400 ----> El listado contiene mAs de 5 correos, estA vacAa o el formato es incorrecto.",
                    messageResponse);
        }
    }

    @Test
    public void testStampReal_ISSUEV4_NULL_EMAIL() throws Exception {
        try {
            SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            SuccessV1Response response = null;
            String[] mails = { "", null };
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            response = (SuccessV1Response) api.Stamp(ut.StringgenBasico(false), "v1", mails, uuidString, true);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
        } catch (Exception e) {
            String messageResponse = normalizeString(e.getMessage());
            Assert.assertEquals("400 ----> El listado contiene mAs de 5 correos, estA vacAa o el formato es incorrecto.",
                    messageResponse);
        }
    }

    @Test
    public void testStampReal_ISSUEV4_INVALID_CUSTOMID() throws Exception {
        try {
            SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            SuccessV1Response response = null;
            String[] mails = { "prueba@test.com" };
            Random random = new Random();
            StringBuilder randomString = new StringBuilder();
            for (int i = 0; i < 101; i++) {
                randomString.append(random.nextInt(10));
            }
            response = (SuccessV1Response) api.Stamp(ut.StringgenBasico(false), "v1", mails, randomString.toString(), true);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            Assert.assertEquals("error", response.Status);
        } catch (Exception e) {
            String messageResponse = normalizeString(e.getMessage());
            Assert.assertEquals("400 ----> El CustomId no es vAlido o es mayor a 100 caracteres.",
                    messageResponse);
        }
    }

    @Test
    public void testStampReal_ISSUEV4_NULL_CUSTOMID() throws Exception {
        try {
            SWStampServiceV4 api = new SWStampServiceV4(Utils.userSW, Utils.passwordSW, Utils.urlSW);
            SuccessV1Response response = null;
            String[] mails = { "prueba@test.com" };
            String randomString = " ";
            response = (SuccessV1Response) api.Stamp(ut.StringgenBasico(false), "v1", mails, randomString, true);
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.message);
            Assert.assertEquals("error", response.Status);
        } catch (Exception e) {
            String messageResponse = normalizeString(e.getMessage());
            Assert.assertEquals("400 ----> El CustomId viene vacAo.",
                    messageResponse);
        }
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
