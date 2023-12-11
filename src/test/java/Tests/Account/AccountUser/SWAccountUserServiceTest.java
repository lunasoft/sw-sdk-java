package Tests.Account.AccountUser;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import Services.Account.AccountUser.SWAccountUserService;
import Tests.Utils;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;
import Utils.Responses.Account.AccountUser.DataAccountUserResponse;
import Utils.Responses.Account.AccountUser.ListDataAccountUserResponse;

public class SWAccountUserServiceTest {

	@Test
	public void CrearUsuario_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16_" + Utils.userSW.toString(),
				Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, AccountUserProfiles.Hijo, false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success") || response.message.equals("AU1001Usuario ya existe."));
	}

	@Test
	public void CrearUsuario_Success_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__" + Utils.userSW.toString(),
				Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, AccountUserProfiles.Hijo, false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success") || response.message.equals("AU1001Usuario ya existe."));
	}

	@Test
	public void CrearUsuario_Error_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__" + Utils.userSW.toString(),
				Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, AccountUserProfiles.Hijo, false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void CrearUsuario_Error_Contra() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.CrearUsuario("hijoJava16__" + Utils.userSW.toString(), Utils.passwordSW,
				"Prueba SW Java 1.6", Utils.rfc, 1, AccountUserProfiles.Hijo, false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void ActualizarUsuario_Error_UsuarioDiferente() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.ActualizarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"),
				"prueba actualizada nombre", "XXXE0101010", false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void ActualizarUsuario_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService("hijoJava16__" + Utils.userSW, Utils.passwordSW + "$",
				Utils.urlSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.ActualizarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"),
				"prueba actualizada nombre", "XXXE0101010", false, true);
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.data);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void EliminarUsuario_Error_NoEncontrado() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.EliminarUsuario(UUID.fromString("dec88273-6587-4f1e-9673-317b30e07aab"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void EliminarUsuario_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.EliminarUsuario(UUID.fromString("dec88273-6587-4f1e-9673-317b30e07aab"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(
				response.Status.equals("success") || response.message.equals("No se encuentra registro de usuario"));
	}

	@Test
	public void EliminarUsuario_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse response = null;
		response = (AccountUserResponse) app.EliminarUsuario(UUID.fromString("dec88273-6587-4f1e-9673-317b30e07aab"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(
				response.Status.equals("success") || response.message.equals("No se encuentra registro de usuario"));
	}

	@Test
	public void ListaUsuarios_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		ListDataAccountUserResponse response = null;
		response = (ListDataAccountUserResponse) app.ObtenerUsuarios(1, 10);
		System.out.println(response.HttpStatusCode);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Password: " + dato.password);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.apellidoPaterno);
				System.out.println("Ilimitado: " + dato.unlimited);
				System.out.println("Activo: " + dato.activo + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void ListaUsuarios_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		ListDataAccountUserResponse response = null;
		response = (ListDataAccountUserResponse) app.ObtenerUsuarios(1, 10);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Password: " + dato.password);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.apellidoPaterno);
				System.out.println("Ilimitado: " + dato.unlimited);
				System.out.println("Activo: " + dato.activo + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}
	@Test
	public void ListaUsuarios_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		ListDataAccountUserResponse response = null;
		response = (ListDataAccountUserResponse) app.ObtenerUsuarios(1, 10);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Password: " + dato.password);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.apellidoPaterno);
				System.out.println("Ilimitado: " + dato.unlimited);
				System.out.println("Activo: " + dato.activo + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
	@Test
	public void ObtenerUsuario_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuario();
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}
	@Test
	public void ObtenerUsuario_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuario();
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}
	@Test
	public void ObtenerUsuario_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuario();
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
	@Test
	public void ObtenerUsuarioId_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuarioId(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"));
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}
	@Test
	public void ObtenerUsuarioId_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuarioId(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"));
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}
	@Test
	public void ObtenerUsuarioId_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		DataAccountUserResponse response = null;
		response = (DataAccountUserResponse) app.ObtenerInfoUsuarioId(UUID.fromString("be2a859c-cd5f-42e6-b35d-f065b3a9aac4"));
		DataAccountUser usuario = response.data;
		if (usuario != null) {
			System.out.println("Email: " + usuario.email);
			System.out.println("Password: " + usuario.password);
			System.out.println("Nombre: " + usuario.name);
			System.out.println("Perfil: " + usuario.profile);
			System.out.println("Stamps: " + usuario.stamps);
			System.out.println("idUsuario: " + usuario.idUsuario);
			System.out.println("Rfc: " + usuario.apellidoPaterno);
			System.out.println("Ilimitado: " + usuario.unlimited);
			System.out.println("Activo: " + usuario.activo + "\n");
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}
	
}
