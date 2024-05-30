package Services.Stamp;

import java.io.IOException;
import java.nio.charset.Charset;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Services.SWService;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Requests.Stamp.StampRequest;
import Utils.Responses.IResponse;

public class SWStampServiceV4 extends SWService {

	public SWStampServiceV4(String user, String password, String URI) throws AuthException {
		super(user, password, URI);
	}

	public SWStampServiceV4(String token, String URI) {
		super(token, URI);
	}

	public SWStampServiceV4(String user, String password, String URI, String proxyHost, int proxyPort)
			throws AuthException {
		super(user, password, URI, proxyHost, proxyPort);
	}

	public SWStampServiceV4(String token, String URI, String proxyHost, int proxyPort) {
		super(token, URI, proxyHost, proxyPort);
	}

	/**
	 * Servicio para timbrar un CFDI en formato XML.
	 *
	 * Realiza el timbrado de un CFDI en formato XML.
	 * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
	 * correo, así como guardar el PDF en el ADT.
	 *
	 * @param xml      String del CFDI en formato XML.
	 * @param version  Versión del servicio.
	 * @param emails   Arreglo de correos para el envío del CFDI y PDF.
	 * @param customId Identificador único asignado al comprobante.
	 * @param isPdf    Opción para habilitar la generación y guardado del PDF en el ADT.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse Stamp(String xml, String version, String[] emails, String customId, boolean isPdf)
			throws AuthException, GeneralException, IOException {
		StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xml, version, getProxyHost(),
				getProxyPort(), emails, customId, isPdf);
		StampRequest req = new StampRequest();
		return req.sendRequest(settings);
	}

	/**
	 * Servicio para timbrar un CFDI en formato XML o Base64 dependiendo de la
	 * bandera isb64.
	 *
	 * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
	 * correo, así como guardar el PDF en el ADT.
	 *
	 * @param xml      String del CFDI en formato XML o Base64.
	 * @param version  Versión del servicio.
	 * @param isb64    Bandera que indica si el XML está en formato Base64.
	 * @param emails   Arreglo de correos para el envío del CFDI y PDF.
	 * @param customId Identificador único asignado al comprobante.
	 * @param isPdf    Opción para habilitar la generación y guardado del PDF en el ADT.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse Stamp(String xml, String version, boolean isb64, String[] emails, String customId, boolean isPdf)
			throws AuthException, GeneralException, IOException {
		if (isb64) {
			StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xml, version, isb64,
					getProxyHost(), getProxyPort(), emails, customId, isPdf);
			StampRequest req = new StampRequest();
			return req.sendRequest(settings);
		} else {
			StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xml, version, getProxyHost(),
					getProxyPort(), emails, customId, isPdf);
			StampRequest req = new StampRequest();
			return req.sendRequest(settings);
		}
	}

	/**
	 * Servicio para timbrar un CFDI en formato XML a partir de un archivo byte[] o
	 * Base64.
	 *
	 * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
	 * correo, así como guardar el PDF en el ADT.
	 *
	 * @param xmlFile  Arreglo de bytes que representa el archivo XML o Base64 del CFDI.
	 * @param version  Versión del servicio.
	 * @param isb64    Bandera que indica si el XML está en formato Base64.
	 * @param emails   Arreglo de correos para el envío del CFDI y PDF.
	 * @param customId Identificador único asignado al comprobante.
	 * @param isPdf    Opción para habilitar la generación y guardado del PDF en el
	 *                 ADT.
	 * @return Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse Stamp(byte[] xmlFile, String version, boolean isb64, String[] emails, String customId,
			boolean isPdf)
			throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xmlProcess, version,
				getProxyHost(), getProxyPort(), emails, customId, isPdf);
		StampRequest req = new StampRequest();
		return req.sendRequest(settings);
	}
	/**
	 * Servicio para timbrar un CFDI en formato XML a partir de un archivo byte[]
	 *
	 * Se puede especificar un Custom Id y realizar el envío del CFDI y PDF por
	 * correo, así como guardar el PDF en el ADT.
	 *
	 * @param xmlFile  Arreglo de bytes que representa el archivo XML o Base64 del CFDI.
	 * @param version  Versión del servicio.
	 * @param isb64    Bandera que indica si el XML está en formato Base64.
	 * @param emails   Arreglo de correos para el envío del CFDI y PDF.
	 * @param customId Identificador único asignado al comprobante.
	 * @param isPdf    Opción para habilitar la generación y guardado del PDF en el
	 *                 ADT.
	 * @return 					Respuesta del servicio.
	 * @throws AuthException    Excepción de autenticación.
	 * @throws GeneralException Excepción general.
	 * @throws IOException      Excepción de entrada/salida.
	 */
	public IResponse Stamp(byte[] xmlFile, String version, String[] emails, String customId, boolean isPdf)
			throws AuthException, GeneralException, IOException {
		String xmlProcess = new String(xmlFile, Charset.forName("UTF-8"));
		StampOptionsRequest settings = new StampOptionsRequest(getToken(), getURI(), xmlProcess, version,
				getProxyHost(), getProxyPort(), emails, customId, isPdf);
		StampRequest req = new StampRequest();
		return req.sendRequest(settings);
	}
}
