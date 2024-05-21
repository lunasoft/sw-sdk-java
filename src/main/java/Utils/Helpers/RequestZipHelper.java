package Utils.Helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.Stamp.SWStampService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Requests.Stamp.StampRequestZip;
import Utils.Responses.IResponse;

public class RequestZipHelper {

    public static IResponse processStampRequest(byte[] xmlFile, String version, SWStampService service) 
    throws AuthException, GeneralException, IOException {
        if (xmlFile.length > 35 * 1024 * 1024) { 
			xmlFile = convertToZip(xmlFile);
			StampOptionsRequest settings = new StampOptionsRequest(service.getToken(), service.getURI(), xmlFile, version, service.getProxyHost(), service.getProxyPort());
			StampRequestZip req = new StampRequestZip();
			return req.sendRequestZip(settings);
		} else {
            String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
			StampOptionsRequest settings = new StampOptionsRequest(service.getToken(), service.getURI(), xmlProcess, version, service.getProxyHost(), service.getProxyPort(), false);
			StampRequest req = new StampRequest();
			return req.sendRequest(settings);
		}
    }

    private static byte[] convertToZip(byte[] data) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ZipOutputStream zos = new ZipOutputStream(baos)) {
			ZipEntry entry = new ZipEntry("file.xml");
			zos.putNextEntry(entry);
			zos.write(data);
			zos.closeEntry();
		}
		return baos.toByteArray();
	}
}
