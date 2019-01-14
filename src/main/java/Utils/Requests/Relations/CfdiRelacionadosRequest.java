package Utils.Requests.Relations;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Relations.CfdiRelacionadosResponse;
import Utils.Responses.Relations.RelacionData;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CfdiRelacionadosRequest implements IRequestor {

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("uuid", ((CfdiRelacionadosOptionsRequest) request).getUuid());
			requestJSON.put("password", ((CfdiRelacionadosOptionsRequest) request).getPassword_csd());
			requestJSON.put("rfc", ((CfdiRelacionadosOptionsRequest) request).getRfc());
			requestJSON.put("b64Cer", ((CfdiRelacionadosOptionsRequest) request).getB64Cer());
			requestJSON.put("b64Key", ((CfdiRelacionadosOptionsRequest) request).getB64key());
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			StringEntity sEntity = new StringEntity(requestJSON.toString());
			httppost.setEntity(builder.build());
			httppost.setEntity(sEntity);
			CloseableHttpResponse responseB = client.execute(httppost);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty()) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					int codStatus = Integer.parseInt(body.getString("codStatus").trim());
					if (codStatus != 2000) {
						String messageDetail = "";
						if (!body.isNull("messageDetail")) {
							messageDetail = body.getString("messageDetail");
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
								messageDetail);
					} else {
						List<RelacionData> listaUUIDPadres = new LinkedList<RelacionData>();
						List<RelacionData> listaUUIDHijos = new LinkedList<RelacionData>();
						JSONObject data = body.getJSONObject("data");
						JSONArray padres = new JSONArray(data.get("uuidsRelacionadosPadres").toString());
						for (int i = 0; i < padres.length(); i++) {
							JSONObject dato = new JSONObject(padres.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDPadres.add(datos);
						}
						JSONArray hijos = new JSONArray(data.get("uuidsRelacionadosHijos").toString());
						for (int i = 0; i < hijos.length(); i++) {
							JSONObject dato = new JSONObject(hijos.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDHijos.add(datos);
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"),
								data.getString("uuidConsultado"), data.getString("resultado"), listaUUIDPadres,
								listaUUIDHijos, body.getString("message"), "OK");
					}
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CfdiRelacionadosResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse sendRequestPFX(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("uuid", ((CfdiRelacionadosOptionsRequest) request).getUuid());
			requestJSON.put("password", ((CfdiRelacionadosOptionsRequest) request).getPassword_csd());
			requestJSON.put("rfc", ((CfdiRelacionadosOptionsRequest) request).getRfc());
			requestJSON.put("b64Pfx", ((CfdiRelacionadosOptionsRequest) request).getB64Pfx());
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			StringEntity sEntity = new StringEntity(requestJSON.toString());
			httppost.setEntity(builder.build());
			httppost.setEntity(sEntity);
			CloseableHttpResponse responseB = client.execute(httppost);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty()) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					int codStatus = Integer.parseInt(body.getString("codStatus").trim());
					if (codStatus != 2000) {
						String messageDetail = "";
						if (!body.isNull("messageDetail")) {
							messageDetail = body.getString("messageDetail");
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
								messageDetail);
					} else {
						List<RelacionData> listaUUIDPadres = new LinkedList<RelacionData>();
						List<RelacionData> listaUUIDHijos = new LinkedList<RelacionData>();
						JSONObject data = body.getJSONObject("data");
						JSONArray padres = new JSONArray(data.get("uuidsRelacionadosPadres").toString());
						for (int i = 0; i < padres.length(); i++) {
							JSONObject dato = new JSONObject(padres.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDPadres.add(datos);
						}
						JSONArray hijos = new JSONArray(data.get("uuidsRelacionadosHijos").toString());
						for (int i = 0; i < hijos.length(); i++) {
							JSONObject dato = new JSONObject(hijos.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDHijos.add(datos);
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"),
								data.getString("uuidConsultado"), data.getString("resultado"), listaUUIDPadres,
								listaUUIDHijos, body.getString("message"), "OK");
					}
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CfdiRelacionadosResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse sendRequestXML(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			String xmlStr = ((CfdiRelacionadosOptionsRequest) request).getXml();
			String boundary = UUID.randomUUID().toString();
			String raw = "--" + boundary
					+ "\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"
					+ xmlStr + "\r\n--" + boundary + "--";
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, raw.length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			httppost.addHeader(new BasicHeader("content-type", "multipart/form-data; boundary=" + boundary));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addTextBody("xml", raw, ContentType.DEFAULT_BINARY);
			httppost.setEntity(builder.build());

			CloseableHttpResponse responseB = client.execute(httppost);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty()) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					int codStatus = Integer.parseInt(body.getString("codStatus").trim());
					if (codStatus != 2000) {
						String messageDetail = "";
						if (!body.isNull("messageDetail")) {
							messageDetail = body.getString("messageDetail");
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
								messageDetail);
					} else {
						List<RelacionData> listaUUIDPadres = new LinkedList<RelacionData>();
						List<RelacionData> listaUUIDHijos = new LinkedList<RelacionData>();
						JSONObject data = body.getJSONObject("data");
						JSONArray padres = new JSONArray(data.get("uuidsRelacionadosPadres").toString());
						for (int i = 0; i < padres.length(); i++) {
							JSONObject dato = new JSONObject(padres.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDPadres.add(datos);
						}
						JSONArray hijos = new JSONArray(data.get("uuidsRelacionadosHijos").toString());
						for (int i = 0; i < hijos.length(); i++) {
							JSONObject dato = new JSONObject(hijos.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDHijos.add(datos);
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"),
								data.getString("uuidConsultado"), data.getString("resultado"), listaUUIDPadres,
								listaUUIDHijos, body.getString("message"), "OK");
					}
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CfdiRelacionadosResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}

	}

	public IResponse sendRequestUUID(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, 7000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			CloseableHttpResponse responseB = client.execute(httppost);

			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty()) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					int codStatus = Integer.parseInt(body.getString("codStatus").trim());
					if (codStatus != 2000) {
						String messageDetail = "";
						if (!body.isNull("messageDetail")) {
							messageDetail = body.getString("messageDetail");
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
								messageDetail);
					} else {
						List<RelacionData> listaUUIDPadres = new LinkedList<RelacionData>();
						List<RelacionData> listaUUIDHijos = new LinkedList<RelacionData>();
						JSONObject data = body.getJSONObject("data");
						JSONArray padres = new JSONArray(data.get("uuidsRelacionadosPadres").toString());
						for (int i = 0; i < padres.length(); i++) {
							JSONObject dato = new JSONObject(padres.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDPadres.add(datos);
						}
						JSONArray hijos = new JSONArray(data.get("uuidsRelacionadosHijos").toString());
						for (int i = 0; i < hijos.length(); i++) {
							JSONObject dato = new JSONObject(hijos.get(i).toString());
							RelacionData datos = new RelacionData(dato.getString("uuid"), dato.getString("rfcEmisor"),
									dato.getString("rfcReceptor"));
							listaUUIDHijos.add(datos);
						}
						return new CfdiRelacionadosResponse(status, body.getString("status"),
								data.getString("uuidConsultado"), data.getString("resultado"), listaUUIDPadres,
								listaUUIDHijos, body.getString("message"), "OK");
					}
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CfdiRelacionadosResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CfdiRelacionadosResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
