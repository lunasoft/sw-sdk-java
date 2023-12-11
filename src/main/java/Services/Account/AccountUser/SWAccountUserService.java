package Services.Account.AccountUser;

import java.io.IOException;
import java.util.UUID;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Requests.Account.AccountUser.AccountUserOptionsRequest;
import Utils.Requests.Account.AccountUser.AccountUserRequest;
import Utils.Responses.IResponse;

public class SWAccountUserService extends SWService {

	public SWAccountUserService(String user, String password, String URI, String URIAPI, String proxyHost,
			int proxyPort) throws AuthException {
		super(user, password, URI, URIAPI, proxyHost, proxyPort);
	}

	public SWAccountUserService(String token, String URIAPI, String proxyHost, int proxyPort) {
		super(token, URIAPI, proxyHost, proxyPort);
	}

	public IResponse CrearUsuario(String email, String passwordUser, String name, String rfc, int stamps,
			AccountUserProfiles profile, boolean unlimited, boolean active)
			throws AuthException, GeneralException, IOException {
		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), email, passwordUser,
				name, rfc, stamps, profile, unlimited, active, getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.createUserRequest(settings);
	}

	public IResponse ActualizarUsuario(UUID idUser, String name, String rfc, boolean unlimited, boolean active)
			throws AuthException, GeneralException, IOException {

		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), idUser, name, rfc,
				unlimited, active, getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.updateUser(settings, idUser);
	}

	public IResponse EliminarUsuario(UUID idUser) throws AuthException, GeneralException, IOException {

		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), idUser,
				getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.deleteUserRequest(settings, idUser);
	}

	public IResponse ObtenerUsuarios(int page, int pageSize) throws AuthException, GeneralException, IOException {

		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), page, pageSize,
				getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.getAllUsersRequest(settings, page, pageSize);
	}

	public IResponse ObtenerInfoUsuario() throws AuthException, GeneralException, IOException {
		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), getProxyHost(),
				getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.getUserRequest(settings);
	}

	public IResponse ObtenerInfoUsuarioId(UUID idUser) throws AuthException, GeneralException, IOException {
		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), idUser,
				getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.getUserIdRequest(settings, idUser);
	}
}