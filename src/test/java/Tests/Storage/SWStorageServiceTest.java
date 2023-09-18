package Tests.Storage;
import java.io.IOException;
import java.util.UUID;
import org.junit.Test;
import Services.Storage.SWStorageService;
import Tests.Utils;
import Utils.Responses.Storage.StorageResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestName;
import Exceptions.AuthException;
import Exceptions.GeneralException;
public class SWStorageServiceTest {
        @Rule
        public TestName testName = new TestName();
        /**
         * Método de UT con token.
         */
        @Test
        public void testGetXmlToken_Success() throws AuthException, GeneralException, IOException {
                SWStorageService storage = new SWStorageService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                StorageResponse res = (StorageResponse) storage
                                .getXml(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"));
                Assert.assertNotNull(res);
                Assert.assertEquals(res.Status, "success");
                Assert.assertNotNull(res.getData());
        }
        /**
         * Método de UT con Authentication.
         */
        @Test
        public void testGetXmlAuth_Success() throws AuthException, GeneralException, IOException {
                SWStorageService storage = new SWStorageService(Utils.userSW, Utils.passwordSW, Utils.urlSW,
                                Utils.urlApiSW, null, 0);
                StorageResponse res = (StorageResponse) storage
                                .getXml(UUID.fromString("4714f6f7-ccb4-4eb5-8ba6-3a523092e2b4"));
                Assert.assertNotNull(res);
                Assert.assertEquals(res.Status, "success");
                // Assert.assertNotNull(res.getData());
                // Assert.assertTrue(res.getData().getRecords().size() > 0);
        }

        /**
         * Método de UT con token incorrecto.
         */
        @Test
        public void testGetXmlToken_Error() throws AuthException, GeneralException, IOException {
                SWStorageService storage = new SWStorageService("empty.token.sw", Utils.urlApiSW, null, 0);
                StorageResponse res = (StorageResponse) storage
                                .getXml(UUID.fromString("c75f84db-e058-4a7c-a902-e4c9c871e8c1"));
                Assert.assertNotNull(res);
                Assert.assertTrue(res.Status.equals("error"));
                Assert.assertTrue(!res.message.isEmpty());
        }

        /**
         * Método de UT con Authentication incorrecta pero Uuid correcto.
         */
        @Test
        public void testGetXmlAuthIncorrect_Error() {
                try {
                        SWStorageService storage = new SWStorageService("user", "pass", Utils.urlSW, Utils.urlApiSW,
                                        null, 0);
                        StorageResponse res = (StorageResponse) storage
                                        .getXml(UUID.fromString("c75f84db-e058-4a7c-a902-e4b9c871e8c1"));
                        Assert.assertNotNull(res);
                        Assert.assertTrue(res.Status.equals("error"));
                } catch (AuthException ex) {
                        Utils.showTestLog(testName, ex.getMessage());
                } catch (GeneralException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        /**
         * Método de UT con uuid incorrecto.
         */
        @Test
        public void testGetXmlUuid_Error() throws AuthException, GeneralException, IOException {
                SWStorageService storage = new SWStorageService(Utils.tokenSW, Utils.urlApiSW, null, 0);
                StorageResponse res = (StorageResponse) storage
                                .getXml(UUID.fromString("c75f84db-e058-4b7c-a902-e4b9c871e8c1"));
                Assert.assertNotNull(res);
                Assert.assertTrue(res.Status.equals("error"));
        }
}
