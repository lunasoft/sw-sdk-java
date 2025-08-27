package Services.StampRetention;
import java.io.IOException;
import java.nio.charset.Charset;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.StampRetention.StampRetentionOptionsRequest;
import Utils.Requests.StampRetention.StampRetentionRequest;
import Utils.Responses.IResponse;

public class SWStampRetentionService extends SWService {

    public SWStampRetentionService(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWStampRetentionService(String token, String URI) {
        super(token, URI);
	}

    public SWStampRetentionService(String user, String password, String URI, String proxyHost, int proxyPort)
			throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWStampRetentionService(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}
    
    /**
	 * Servicio para timbrar un CFDI de retenciones en formato XML.
	 *
	 * Realiza el timbrado de un CFDI de retenciones en formato XML.
	 *
	 * @param xml      String del CFDI en formato XML.
	 * @param version  Versión del servicio.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
    public IResponse StampRetention(String xml, String version) throws AuthException, GeneralException, IOException{
        StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xml, version, getProxyHost(),
				getProxyPort());
		StampRetentionRequest req = new StampRetentionRequest();
		return req.sendRequest(settings);
    }

	/**
	 * Servicio para timbrar un CFDI de retenciones en formato XML o Base64 dependiendo de la
	 * bandera isb64.
	 *
	 * Realiza el timbrado de un CFDI de retenciones en formato XML.
	 *
	 * @param xml      String del CFDI en formato XML.
	 * @param version  Versión del servicio.
	 * @param isb64    Bandera que indica si el XML está en formato Base64.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
    public IResponse StampRetention(String xml, String version, boolean isb64) throws AuthException, GeneralException, IOException{
		if (isb64){
			StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xml, version, isb64, getProxyHost(),
				getProxyPort());
			StampRetentionRequest req = new StampRetentionRequest();
			return req.sendRequest(settings);
		}
		else{
			StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xml, version, getProxyHost(),
				getProxyPort());
			StampRetentionRequest req = new StampRetentionRequest();
			return req.sendRequest(settings);
		}
    }

	/**
	 * Servicio para timbrar un CFDI de retenciones en formato XML a partir de un archivo byte[]
	 *
	 * Realiza el timbrado de un CFDI de retenciones en formato XML.
	 *
	 * @param xmlFile  Arreglo de bytes que representa el archivo XML o Base64 del CFDI de retención.
	 * @param version  Versión del servicio.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse StampRetention(byte[] xmlFile, String version) throws AuthException, GeneralException, IOException{
        String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xmlProcess, version, getProxyHost(),
				getProxyPort());
		StampRetentionRequest req = new StampRetentionRequest();
		return req.sendRequest(settings);
    }

	/**
	* Servicio para timbrar un CFDI de retenciones en formato XML a partir de un archivo byte[] o
	 * Base64.
	 *
	 * Realiza el timbrado de un CFDI de retenciones en formato XML.
	 *
	 * @param xmlFile  Arreglo de bytes que representa el archivo XML o Base64 del CFDI de retención.
	 * @param version  Versión del servicio.
	 * @param isb64    Bandera que indica si el XML está en formato Base64.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse StampRetention(byte[] xmlFile, String version, boolean isb64) throws AuthException, GeneralException, IOException{
		String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		if (isb64){
			StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xmlProcess, version, isb64, getProxyHost(),
				getProxyPort());
			StampRetentionRequest req = new StampRetentionRequest();
			return req.sendRequest(settings);
		}
		else{
			StampRetentionOptionsRequest settings = new StampRetentionOptionsRequest(getToken(), getURI(), xmlProcess, version, getProxyHost(),
				getProxyPort());
			StampRetentionRequest req = new StampRetentionRequest();
			return req.sendRequest(settings);
		}
    }
}
