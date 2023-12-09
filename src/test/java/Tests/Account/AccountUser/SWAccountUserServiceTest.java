package Tests.Account.AccountUser;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import Services.Account.AccountUser.SWAccountUserService;
import Tests.Utils;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Responses.Account.AccountUser.AccountUserResponse;

public class SWAccountUserServiceTest {

    @Test
	public void CreateUser_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16_"+Utils.userSW.toString(),Utils.passwordSW+"$", "Prueba SW Java 1.6",Utils.rfc,1,AccountUserProfiles.Hijo, false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.data);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success")||response.message.equals("AU1001Usuario ya existe."));
	}
     @Test
	public void CreateUser_Success_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__"+Utils.userSW.toString(),Utils.passwordSW+"$", "Prueba SW Java 1.6",Utils.rfc,1,AccountUserProfiles.Hijo, false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.data);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success")||response.message.equals("AU1001Usuario ya existe."));
	}
     @Test
	public void CreateUser_Error_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__"+Utils.userSW.toString(),Utils.passwordSW+"$", "Prueba SW Java 1.6",Utils.rfc,1,AccountUserProfiles.Hijo, false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
      @Test
	public void CreateUser_Error_BadPassword() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__"+Utils.userSW.toString(),Utils.passwordSW, "Prueba SW Java 1.6",Utils.rfc,1,AccountUserProfiles.Hijo, false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
	@Test
	public void UpdateUser_Error_TokenDiferente() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.ActualizarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"), "prueba actualizada nombre","XXXE0101010", false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
	@Test
	public void UpdateUser_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService("hijoJava16__"+Utils.userSW, Utils.passwordSW+"$", Utils.urlSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.ActualizarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"), "prueba actualizada nombre","XXXE0101010", false, true);
        Assert.assertNotNull(response.HttpStatusCode);
        Assert.assertNotNull(response.data);
        Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success")||response.message.equals("AU1001Usuario ya existe."));
	}





}
