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
    
    public String token = "T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbXB3YVZxTHdOdHAwVXY2NTdJb1hkREtXTzE3dk9pMmdMdkFDR2xFWFVPUXpTUm9mTG1ySXdZbFNja3FRa0RlYURqbzdzdlI2UUx1WGJiKzViUWY2dnZGbFloUDJ6RjhFTGF4M1BySnJ4cHF0YjUvbmRyWWpjTkVLN3ppd3RxL0dJPQ.T2lYQ0t4L0RHVkR4dHZ5Nkk1VHNEakZ3Y0J4Nk9GODZuRyt4cE1wVm5tbFlVcU92YUJTZWlHU3pER1kySnlXRTF4alNUS0ZWcUlVS0NhelhqaXdnWTRncklVSWVvZlFZMWNyUjVxYUFxMWFxcStUL1IzdGpHRTJqdS9Zakw2UGRiMTFPRlV3a2kyOWI5WUZHWk85ODJtU0M2UlJEUkFTVXhYTDNKZVdhOXIySE1tUVlFdm1jN3kvRStBQlpLRi9NeWJrd0R3clhpYWJrVUMwV0Mwd3FhUXdpUFF5NW5PN3J5cklMb0FETHlxVFRtRW16UW5ZVjAwUjdCa2g0Yk1iTExCeXJkVDRhMGMxOUZ1YWlIUWRRVC8yalFTNUczZXdvWlF0cSt2UW0waFZKY2gyaW5jeElydXN3clNPUDNvU1J2dm9weHBTSlZYNU9aaGsvalpQMUxrUndzK0dHS2dpTittY1JmR3o2M3NqNkh4MW9KVXMvUHhZYzVLQS9UK2E1SVhEZFJKYWx4ZmlEWDFuSXlqc2ZRYXlUQk1ldlZkU2tEdU10NFVMdHZKUURLblBxakw0SDl5bUxabDFLNmNPbEp6b3Jtd2Q1V2htRHlTdDZ6eTFRdUNnYnVvK2tuVUdhMmwrVWRCZi9rQkU9.7k2gVCGSZKLzJK5Ky3Nr5tKxvGSJhL13Q8W-YhT0uIo";

    public void testBalanceAccountService() throws AuthException, GeneralException {
        SWBalanceAccountService app = new SWBalanceAccountService("demo","123456789",Utils.url_pruebas);
        IResponse response = null;
        response = app.GetBalanceAccount();
        System.out.println(response.Status);
        System.out.println(response.Data);
        System.out.println(response.HttpStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    
    public void testBalanceAccountService_authToken() throws Exception {
        SWBalanceAccountService app = new SWBalanceAccountService(token,Utils.url_pruebas);
        IResponse response = null;
        
        response = app.GetBalanceAccount();
        System.out.println(response.Status);
        System.out.println(response.Data);
        System.out.println(response.HttpStatusCode);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    
    public void testBalanceAccountService_incorrectToken() throws Exception {
                
        SWBalanceAccountService app = new SWBalanceAccountService("wrong token",Utils.url_pruebas);
        IResponse response = null;
               
        response = app.GetBalanceAccount();
        System.out.println(response.Status);
        System.out.println(response.message);
        System.out.println(response.HttpStatusCode);
        String expect_message = "Unauthorized";
        Assert.assertTrue(expect_message.equalsIgnoreCase(response.message));
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
