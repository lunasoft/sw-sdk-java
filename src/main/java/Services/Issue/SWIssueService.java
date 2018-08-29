package Services.Issue;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Issue.IssueOptionsRequest;
import Utils.Requests.Issue.IssueRequest;
import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.charset.Charset;

public class SWIssueService extends SWService {

	public SWIssueService(String user, String password, String URI) {
		super(user, password, URI);
	}

	public SWIssueService(String token, String URI) {
		super(token, URI);
	}

	public IResponse IssueJson(String json, String version) throws AuthException, GeneralException, IOException {
		if (getToken() == null) {
			generateToken();
		}
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), json, version);
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);
	}

	public IResponse Issue(byte[] jsonFile, String version) throws AuthException, GeneralException, IOException {
		String jsonProcess = new String(jsonFile, Charset.forName("UTF-8"));
		if (getToken() == null) {
			generateToken();
		}
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), jsonProcess, version);
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);

	}
}
