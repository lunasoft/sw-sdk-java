package Tests.BalanceAccount;

//@author Lupita Alvarado

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Tests.Utils;
import Utils.Responses.IResponse;
import junit.framework.TestCase;
import org.junit.Assert;


public class SWBalanceAccountServiceTest extends TestCase {
    
    public void testBalanceAccountService() throws AuthException, GeneralException {
        SWBalanceAccountService app = new SWBalanceAccountService("demo","123456789",Utils.url_pruebas);
        IResponse response = null;
        response = app.GetBalanceAccount();
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    
    public void testBalanceAccountService_emptyUserParams() throws AuthException, GeneralException {
        SWBalanceAccountService app = new SWBalanceAccountService("", "", "");
        IResponse response = null;
        try {
            response = app.GetBalanceAccount();
        } catch (GeneralException e) {
            e.printStackTrace();
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }
}
