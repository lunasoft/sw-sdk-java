package Services.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;

import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.charset.Charset;

public class SWStampService extends SWService {

	public SWStampService(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWStampService(String token, String URI) {
		super(token, URI);
	}
	
	public SWStampService(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWStampService(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	public IResponse Stamp(String xml, String version) throws AuthException, GeneralException, IOException {
		return this.Stamp(xml, version, false);
	}

	public IResponse Stamp(String xml, String version, boolean isb64)
			throws AuthException, GeneralException, IOException {
		StampRequest req = new StampRequest();
		return req.sendRequest(this.getOptions(xml, version, isb64));
	}

	public IResponse Stamp(byte[] xmlFile, String version, boolean isb64)
			throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		return this.Stamp(xmlProcess, version, isb64);
	}

	public IResponse Stamp(byte[] xmlFile, String version) throws AuthException, GeneralException, IOException {
		return this.Stamp(xmlFile, version, false);
	}

	private StampOptionsRequest getOptions(String xml, String version, boolean isBase64) throws AuthException,
			GeneralException, IOException {
		return new StampOptionsRequest(super.getToken(), super.getURI(), xml, version, isBase64,
				super.getProxyHost(), super.getProxyPort(), false);
	}
}