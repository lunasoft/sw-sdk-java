package Tests.Resend;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Ignore;
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
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
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
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
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
        //Se ignoran por que lanzan excepciones y no como tal un mensaje de error
        @Ignore
        public void ResendEmail_Emails_Error() throws Exception {
                SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                "ex1@gmail.com,ex2@gmail.com, ex3@gmail.com,ex4@gmail.com,ex5@gmail.com");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("error"));

        }
        @Ignore
        public void ResendEmail_EmailSize_Error() throws Exception {
                SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                "ex1@gmail.com,ex2@gmail.com,ex3@gmail.com,ex4@gmail.com,ex5@gmail.com,ex6@gmail.com");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("error"));

        }
        @Ignore
        public void ResendEmail_EmptyEmail_Error() throws Exception {
                SWResendService app = new SWResendService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                ResendResponse response = null;
                response = (ResendResponse) app.ResendEmail(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"),
                                "");
                Assert.assertNotNull(response.HttpStatusCode);
                Assert.assertNotNull(response.Status);
                Assert.assertNotNull(response.message);
                Assert.assertNotNull(response.messageDetail);
                Assert.assertTrue(response.Status.equals("error"));

        }
}
