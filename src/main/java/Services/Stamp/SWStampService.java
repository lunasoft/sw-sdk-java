package Services.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Responses.IResponse;
import Utils.Helpers.RequestZipHelper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SWStampService extends SWService {

	public SWStampService(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWStampService(String token, String URI) {
		super(token, URI);
	}

	public SWStampService(String user, String password, String URI, String proxyHost, int proxyPort)
			throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWStampService(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	public IResponse Stamp(String xml, String version) throws AuthException, GeneralException, IOException {
		byte[] xmlFile = xml.getBytes(StandardCharsets.UTF_8);
		return RequestZipHelper.processStampRequest(xmlFile, version, this);
	}

	public IResponse Stamp(String xml, String version, boolean isb64)
			throws AuthException, GeneralException, IOException {
		if (isb64) {
			StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xml, version, isb64,
					getProxyHost(), getProxyPort(), false);
			StampRequest req = new StampRequest();
			return req.sendRequest(settings);
		} else {
			StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xml, version, getProxyHost(),
					getProxyPort(), false);
			StampRequest req = new StampRequest();
			return req.sendRequest(settings);
		}
	}

	public IResponse Stamp(byte[] xmlFile, String version, boolean isb64)
			throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xmlProcess, version,
				getProxyHost(), getProxyPort(), false);
		StampRequest req = new StampRequest();
		return req.sendRequest(settings);
	}

	public IResponse Stamp(byte[] xmlFile, String version) throws AuthException, GeneralException, IOException {
		return RequestZipHelper.processStampRequest(xmlFile, version, this);
	}
}