package Tests.BalanceAccount;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.BalanceAccount.SWBalanceAccountService;
import Tests.Utils;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;


public class SWBalanceAccountServiceTest {
    @Test
    public void testBalanceAccountService() throws AuthException, GeneralException, IOException {
        SWBalanceAccountService app = new SWBalanceAccountService(Utils.userSW, Utils.passwordSW, Utils.urlSW);
        BalanceAcctResponse response = (BalanceAcctResponse) app.GetBalanceAccount();
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.idSaldoCliente);
        System.out.println(response.idClienteUsuario);
        System.out.println(response.saldoTimbres);
        System.out.println(response.timbresUtilizados);
        System.out.println(response.fechaExpiracion);
        System.out.println(response.unlimited);
        System.out.println(response.timbresAsignados);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    @Test
    public void testBalanceAccountService_authToken() throws Exception {
        SWBalanceAccountService app = new SWBalanceAccountService(Utils.tokenSW,Utils.urlSW);
        BalanceAcctResponse response = (BalanceAcctResponse) app.GetBalanceAccount();

        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.idSaldoCliente);
        System.out.println(response.idClienteUsuario);
        System.out.println(response.saldoTimbres);
        System.out.println(response.timbresUtilizados);
        System.out.println(response.fechaExpiracion);
        System.out.println(response.unlimited);
        System.out.println(response.timbresAsignados);
        String expect_status = "success";
        Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
    }
    @Test
    public void testBalanceAccountService_incorrectToken() {
    	try {
    		SWBalanceAccountService app = new SWBalanceAccountService("wrong token", Utils.urlSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.GetBalanceAccount();
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.idSaldoCliente);
            System.out.println(response.idClienteUsuario);
            System.out.println(response.saldoTimbres);
            System.out.println(response.timbresUtilizados);
            System.out.println(response.fechaExpiracion);
            System.out.println(response.unlimited);
            System.out.println(response.timbresAsignados);
            Assert.assertTrue(response.HttpStatusCode == 401);
    	}
    	catch(AuthException e) {
    		Assert.fail();
    	} catch (GeneralException e) {
    		Assert.fail();
		} catch (IOException e) {
			Assert.fail();
		}
    }
    @Test
    public void testBalanceAccountService_emptyUserParams(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("", "", "");
        	app.GetBalanceAccount();
        } 
        catch(AuthException e) {
    		System.out.println(e.getErrorMSG());
    		System.out.println(e.getHttpStatusCode());
    		Assert.assertTrue(true);
    	} catch (GeneralException e) {
    		Assert.fail();
		} catch (IOException e) {
			Assert.fail();
		}
    }

    @Test
    public void testAddStampsByToken_Sucess(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService(Utils.tokenSW, Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "success";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.fail();
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStampsByAuth_Sucess(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "success";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.fail();
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
        
    }

    @Test
    public void testAddStampsByToken_emptyToken(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStampsByAuth_emptyUser(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("", "", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStampsByToken_incorrectToken(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("empty.token.sw", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse)app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddStampsByAuth_incorrectUser(){
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("wronguser@mail.com", "12345678a", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.AddStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveStampsByToken_Sucess() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService(Utils.tokenSW, Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "success";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.fail();
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveStampsByAuth_Sucess() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "success";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.fail();
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }   
    }

    @Test
    public void testRemoveStampsByToken_emptyToken() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveStampsByAuth_emptyUser() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("", "", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveStampsByToken_incorrectToken() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("empty.token.sw", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveStampsByAuth_incorrectUser() {
        try {
        	SWBalanceAccountService app = new SWBalanceAccountService("wronguser@mail.com", "12345678a", Utils.urlApiSW);
            BalanceAcctResponse response = (BalanceAcctResponse) app.RemoveStamp(UUID.fromString("828f19b1-77dc-48bc-9cfa-d48b5cf7e30c"), 1, "PruebaJava16");
            Assert.assertNotNull(response);
            String expect_status = "error";
            Assert.assertTrue(expect_status.equalsIgnoreCase(response.Status));
        } catch (AuthException e) {
            Assert.assertTrue(true);
        } catch (GeneralException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }
}