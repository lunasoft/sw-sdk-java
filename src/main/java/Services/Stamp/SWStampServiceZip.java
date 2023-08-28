package Services.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequestXML;
import Utils.Requests.Stamp.StampRequestZIP;
import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SWStampServiceZip extends SWService{
    public SWStampServiceZip(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWStampServiceZip(String token, String URI) {
		super(token, URI);
	}
	
	public SWStampServiceZip(String user, String password, String URI, String proxyHost, int proxyPort) throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWStampServiceZip(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}
	
	public IResponse Stamp(String zipFile) throws AuthException, GeneralException, IOException {
		byte[] zipData = Files.readAllBytes(Paths.get(zipFile));
		StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), zipData, getProxyHost(), getProxyPort(), false, true);
		StampRequestZIP req = new StampRequestZIP();
		return req.sendRequest(settings);
	}
}
