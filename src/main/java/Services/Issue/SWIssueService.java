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

	public SWIssueService(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWIssueService(String token, String URI) {
		super(token, URI);
	}
	
	public SWIssueService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWIssueService(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	public IResponse IssueJson(String json, String version) throws AuthException, GeneralException, IOException {
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), json, version, getProxyHost(), getProxyPort());
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);
	}

	public IResponse IssueJson(byte[] jsonFile, String version) throws AuthException, GeneralException, IOException {
		String jsonProcess = new String(jsonFile, Charset.forName("UTF-8"));
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), jsonProcess, version, getProxyHost(), getProxyPort());
		IssueRequest req = new IssueRequest();
		return req.sendRequest(settings);
	}
	public IResponse IssueXml(String xml, String version) throws AuthException, GeneralException, IOException {
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xml, version, true, getProxyHost(), getProxyPort());
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
	public IResponse IssueXml(byte[] xmlfile, String version) throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlfile, Charset.forName("UTF-8"));
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xmlProcess, version, true, getProxyHost(), getProxyPort());
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
}
