package Tests.Account.AccountUser;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.Test;

import Services.Account.AccountUser.SWAccountUserService;
import Tests.Utils;
import Utils.Responses.Account.AccountUser.AccountUserResponse;
import Utils.Responses.Account.AccountUser.DataAccountUser;

public class SWAccountUserServiceTest {

	@Test
	public void CrearUsuario_Success() throws Exception {
		try {
			SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
			AccountUserResponse<String> response = null;
			String newUser = "hijoJava16__" + Utils.userSW.toString();
			response = (AccountUserResponse<String>) app.CrearUsuario(newUser,
					Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, "3920000000", false,
					"notification@email.com");
			Assert.assertNotNull(response.HttpStatusCode);
			Assert.assertNotNull(response.Status);
			Assert.assertNotNull(response.message);
			Assert.assertNotNull(response.messageDetail);
			Assert.assertTrue(response.Status.equals("success")
					|| response.message.equals("El email '" + newUser + "' ya esta en uso."));
		} catch (Exception e) {
		}
	}

	@Test
	public void CrearUsuario_Success_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<String> response = null;
		String newUser = "hijoJava16__" + Utils.userSW.toString();
		response = (AccountUserResponse<String>) app.CrearUsuario(newUser,
				Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, "3920000000", false,
				"notification@email.com");
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success")
				|| response.message.equals("El email '" + newUser + "' ya esta en uso."));
	}

	@Test
	public void CrearUsuario_Error_Token() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app.CrearUsuario("hijoJava16__" + Utils.userSW.toString(),
				Utils.passwordSW + "$", "Prueba SW Java 1.6", Utils.rfc, 1, "3920000000", false,
				"notification@email.com");
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void CrearUsuario_Error_Contra() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app.CrearUsuario("hijoJava16__" + Utils.userSW.toString(),
				Utils.passwordSW,
				"Prueba SW Java 1.6", Utils.rfc, 1, "3920000000", false, "notification@email.com");
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
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app.ActualizarUsuario(
				UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"),
				"prueba actualizada nombre", "XXXE0101010", false, "3920000000", "correo@email.com");
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void ActualizarUsuario_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW,
				Utils.urlSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app.ActualizarUsuario(
				UUID.fromString("85B28EA4-0970-4B53-A6C4-D0F57216E934"),
				"prueba actualizada nombre", "EKU9003173C9", false, "3120000000", "correo1@email.com");
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success") || response.message
				.equals("No es posible actualizar, los datos enviados son identicos a los actuales"));
	}

	@Test
	public void EliminarUsuario_Error_NoEncontrado() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app
				.EliminarUsuario(UUID.fromString("dec88273-6587-4f1e-9673-317b30e07aab"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void EliminarUsuario_Error_NoPertenecealDealer() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app
				.EliminarUsuario(UUID.fromString("2c4ff198-0512-4128-a85d-41e8aedaa0d6"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void EliminarUsuario_Error_NoSePuedeEliminarPorqueTieneSaldo() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app
				.EliminarUsuario(UUID.fromString("be2a859c-cd5f-42b5-b35d-f065b3a9aac4"));
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
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app
				.EliminarUsuario(UUID.fromString("4522ae3c-681b-4129-9dea-516178ca4f84"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(
				response.Status.equals("success")
						|| response.message.equals("El usuario ya ha sido previamente removido"));
	}

	@Test
	public void EliminarUsuario_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<String> response = null;
		response = (AccountUserResponse<String>) app
				.EliminarUsuario(UUID.fromString("dec88273-6587-4f1e-9673-317b30e07aab"));
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(
				response.Status.equals("success")
						|| response.message.equals("El usuario ya ha sido previamente removido"));
	}

	@Test
	public void TodosUsuariosHijo_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.userSW, Utils.passwordSW, Utils.urlSW, Utils.urlApiSW,
				null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuariosHijo();
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void TodosUsuariosHijo_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuariosHijo();
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void TodosUsuariosHijo_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("Token...", Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuariosHijo();
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void UsuariosPorRFC_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuarioPorRfc("EKU9003173C9");
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void UsuariosPorRFC_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("WrongToken...", Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuarioPorRfc("EKU9003173C9");
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

	@Test
	public void UsuariosActivos_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuariosActivos(true);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void UsuariosNoActivos_Token_Success() throws Exception {
		SWAccountUserService app = new SWAccountUserService(Utils.tokenSW, Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuariosActivos(false);
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("success"));
	}

	@Test
	public void UsuariosActivos_Token_Error() throws Exception {
		SWAccountUserService app = new SWAccountUserService("WrongToken...", Utils.urlApiSW, null, 0);
		AccountUserResponse<List<DataAccountUser>> response = null;
		response = (AccountUserResponse<List<DataAccountUser>>) app.ObtenerUsuarioPorRfc("EKU9003173C9");
		List<DataAccountUser> lista = response.data;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				DataAccountUser dato = lista.get(i);
				System.out.println("Email: " + dato.email);
				System.out.println("Nombre: " + dato.name);
				System.out.println("Perfil: " + dato.profile);
				System.out.println("Stamps: " + dato.stamps);
				System.out.println("idUsuario: " + dato.idUsuario);
				System.out.println("Rfc: " + dato.taxId);
				System.out.println("Ilimitado: " + dato.isUnlimited);
				System.out.println("Activo: " + dato.isActive + "\n");
			}
		}
		Assert.assertNotNull(response.HttpStatusCode);
		Assert.assertNotNull(response.Status);
		Assert.assertNotNull(response.message);
		Assert.assertNotNull(response.messageDetail);
		Assert.assertTrue(response.Status.equals("error"));
	}

}
