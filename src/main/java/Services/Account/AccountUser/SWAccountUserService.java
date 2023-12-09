package Services.Account.AccountUser;

import java.io.IOException;
import java.util.UUID;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Helpers.EnumAccountUser.AccountUserProfiles;
import Utils.Requests.Account.AccountUser.AccountUserOptionsRequest;
import Utils.Requests.Account.AccountUser.AccountUserRequest;
import Utils.Requests.Csd.CsdOptionsRequest;
import Utils.Requests.Csd.CsdRequest;
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
		return req.sendRequest(settings);
	}

	public IResponse ActualizarUsuario(UUID idUser, String name, String rfc, boolean unlimited, boolean active) throws AuthException,GeneralException, IOException {
	
		AccountUserOptionsRequest settings = new AccountUserOptionsRequest(getToken(), getURIAPI(), idUser,name, rfc,
				unlimited, active, getProxyHost(), getProxyPort());
		AccountUserRequest req = new AccountUserRequest();
		return req.UpdateUser(settings, idUser);
	}
	/*
	 * public IResponse DeleteUser(String certificateNumber) throws AuthException,
	 * GeneralException, IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), certificateNumber,
	 * getProxyHost(), getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.DisableCsdRequest(settings);
	 * }
	 * 
	 * public IResponse GetAllUsers() throws AuthException, GeneralException,
	 * IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), getProxyHost(),
	 * getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.GetListCsdRequest(settings);
	 * }
	 * 
	 * public IResponse GetUserByToken() throws AuthException, GeneralException,
	 * IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), "type/" + type,
	 * getProxyHost(), getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.GetListCsdRequest(settings);
	 * }
	 * 
	 * public IResponse GetUserById(UUID idUser) throws AuthException,
	 * GeneralException, IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), "rfc/" + rfc,
	 * getProxyHost(), getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.GetListCsdRequest(settings);
	 * }
	 * 
	 * public IResponse SearchMyCsd(String certificateNumber) throws AuthException,
	 * GeneralException, IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), certificateNumber,
	 * getProxyHost(), getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.GetInfoCsdRequest(settings);
	 * }
	 * 
	 * public IResponse SearchActiveCsd(String rfc, String type) throws
	 * AuthException, GeneralException, IOException {
	 * CsdOptionsRequest settings = null;
	 * settings = new CsdOptionsRequest(getToken(), getURI(), "rfc/" + rfc, type,
	 * getProxyHost(), getProxyPort());
	 * CsdRequest req = new CsdRequest();
	 * return req.GetInfoCsdRequest(settings);
	 * }
	 */
}