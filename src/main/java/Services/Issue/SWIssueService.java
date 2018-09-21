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
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), json, version);
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);
	}

	public IResponse IssueJson(byte[] jsonFile, String version) throws AuthException, GeneralException, IOException {
		String jsonProcess = new String(jsonFile, Charset.forName("UTF-8"));
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), jsonProcess, version);
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);
	}
	public IResponse IssueXml(String xml, String version) throws AuthException, GeneralException, IOException {
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xml, version, true);
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
	public IResponse IssueXml(byte[] xmlfile, String version) throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlfile, Charset.forName("UTF-8"));
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xmlProcess, version, true);
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
}
