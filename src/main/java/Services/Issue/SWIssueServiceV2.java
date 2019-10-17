package Services.Issue;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.Issue.IssueOptionsRequest;
import Utils.Requests.Issue.IssueRequest;
import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.charset.Charset;

public class SWIssueServiceV2 extends SWIssueService {

	public SWIssueServiceV2(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWIssueServiceV2(String token, String URI) {
		super(token, URI);
	}
	
	public SWIssueServiceV2(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWIssueServiceV2(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	public IResponse IssueXml(String xml, String version) throws AuthException, GeneralException, IOException {
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xml, version, true, getProxyHost(), getProxyPort(), true);
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
	public IResponse IssueXml(byte[] xmlfile, String version) throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlfile, Charset.forName("UTF-8"));
		IssueOptionsRequest settings = new IssueOptionsRequest(getToken(), getURI(), xmlProcess, version, true, getProxyHost(), getProxyPort(), true);
		IssueRequest req = new IssueRequest();
		return req.sendRequestXml(settings);
	}
}
