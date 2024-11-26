package Tests.Resend;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import Services.Resend.SWResendService;
import Tests.Utils;
import Utils.Responses.Resend.ResendResponse;

public class SWResendServiceTest {
        @Test
        public void ResendEmail_Success() throws Exception {
                SWResendService app = new SWResendService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
                                null,
                                0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("da3b7571-1cfd-4fb7-8bcd-123ef1cba77f"),
                                "ex1@gmail.com,ex2@gmail.com,ex3@gmail.com,ex4@gmail.com,ex5@gmail.com");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.data);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("success"));

        }

        @Test
        public void ResendEmail_Token_Success() throws Exception {
                SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("da3b7571-1cfd-4fb7-8bcd-123ef1cba77f"),
                                "ex1@gmail.com,ex2@gmail.com,ex3@gmail.com,ex4@gmail.com,ex5@gmail.com");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.data);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("success"));
        }

        @Test
        public void ResendEmail_Token_Error() throws Exception {
                SWResendService app = new SWResendService("fakeToken...", Utils.urlApiSW, null, 0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                "ex1@gmail.com,ex2@gmail.com,ex3@gmail.com,ex4@gmail.com,ex5@gmail.com");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("error"));

        }

        @Test
        public void ResendEmail_Emails_Error() throws Exception {
                try {
                        SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                        ResendResponse response = null;
                        response = (ResendResponse) app.ResendEmail(
                                        UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                        "ex1@gmail.com,ex2@gmail.com, ex3@gmail.com,ex4@gmail.com,ex5@gmail.com");
                        Assert.fail("Se esperaba una ValidationException");
                } catch (Exception e) {
                        Assert.assertEquals("No debe haber espacios entre los correos.", e.getMessage());

                } catch (Throwable e) {
                        Assert.fail("Se esperaba una ValidationException.");
                }
        }

        @Test
        public void ResendEmail_EmailSize_Error() throws Exception {
                try {
                        SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                        ResendResponse response = null;
                        response = (ResendResponse) app.ResendEmail(
                                        UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                        "ex1@gmail.com,ex2@gmail.com,ex3@gmail.com,ex4@gmail.com,ex5@gmail.com,ex6@gmail.com");
                        Assert.fail("Se esperaba una ValidationException");
                } catch (Exception e) {
                        Assert.assertEquals("Excediste el máximo de correos, sólo se pueden agregar 5 destinatarios.", e.getMessage());

                } catch (Throwable e) {
                        Assert.fail("Se esperaba una ValidationException.");
                }
        }

        @Test
        public void ResendEmail_EmptyEmail_Error() throws Exception {
                try {
                        SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                        ResendResponse response = null;
                        response = (ResendResponse) app.ResendEmail(
                                        UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                        "");
                        Assert.fail("Se esperaba una ValidationException");
                } catch (Exception e) {
                        Assert.assertEquals("Cadena vacía.", e.getMessage());

                } catch (Throwable e) {
                        Assert.fail("Se esperaba una ValidationException.");
                }
        }
}
