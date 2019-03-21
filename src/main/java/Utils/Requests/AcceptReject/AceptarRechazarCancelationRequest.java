package Utils.Requests.AcceptReject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.soap.SOAPException;

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

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.AcceptReject.AceptarRechazarCancelationResponse;
import Utils.Responses.AcceptReject.CancelationData;

public class AceptarRechazarCancelationRequest implements IRequestor{
	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
		Map<String, String> map = ((AceptarRechazarOptionsRequest) request).getUuids();
		JSONArray mJSONArray = RequestHelper.buildJSONFromMap(map);
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("uuids", mJSONArray);
		requestJSON.put("password", ((AceptarRechazarOptionsRequest) request).getPassword_csd());
		requestJSON.put("rfc", ((AceptarRechazarOptionsRequest) request).getRfc());
		requestJSON.put("b64Cer", ((AceptarRechazarOptionsRequest) request).getB64Cer());
		requestJSON.put("b64Key", ((AceptarRechazarOptionsRequest) request).getB64key());
		try {
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
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");
					JSONArray foliosJson = data.getJSONArray("folios");
					List<CancelationData> list = new LinkedList<CancelationData>();
					for (int i = 0; i < foliosJson.length(); i++) {
						JSONObject foliosData = new JSONObject(foliosJson.get(i).toString());
						CancelationData datos = new CancelationData(foliosData.getString("uuid"),foliosData.getString("estatusUUID"),foliosData.get("respuesta").toString());
						list.add(datos);
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), data.getString("acuse"), list, "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new AceptarRechazarCancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
	
	public IResponse sendRequestPFX(IRequest request) throws GeneralException, AuthException, GeneralException,
	UnsupportedEncodingException, ClientProtocolException, IOException, SOAPException {
		Map<String, String> map = ((AceptarRechazarOptionsRequest) request).getUuids();
		JSONArray mJSONArray = RequestHelper.buildJSONFromMap(map);
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("uuids", mJSONArray);
		requestJSON.put("password", ((AceptarRechazarOptionsRequest) request).getPassword_csd());
		requestJSON.put("rfc", ((AceptarRechazarOptionsRequest) request).getRfc());
		requestJSON.put("b64Pfx", ((AceptarRechazarOptionsRequest) request).getB64Pfx());
		
		try {
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
					JSONObject data = body.getJSONObject("data");
					JSONArray foliosJson = data.getJSONArray("folios");
					List<CancelationData> list = new LinkedList<CancelationData>();
					for (int i = 0; i < foliosJson.length(); i++) {
						JSONObject foliosData = new JSONObject(foliosJson.get(i).toString());
						CancelationData datos = new CancelationData(foliosData.getString("uuid"),foliosData.getString("estatusUUID"),foliosData.get("respuesta").toString());
						list.add(datos);
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), data.getString("acuse"), list, "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new AceptarRechazarCancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
	
	public IResponse sendRequestXML(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			String xmlStr = ((AceptarRechazarOptionsRequest) request).getXml();
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
					JSONObject data = body.getJSONObject("data");
					JSONArray foliosJson = data.getJSONArray("folios");
					List<CancelationData> list = new LinkedList<CancelationData>();
					for (int i = 0; i < foliosJson.length(); i++) {
						JSONObject foliosData = new JSONObject(foliosJson.get(i).toString());
						CancelationData datos = new CancelationData(foliosData.getString("uuid"),foliosData.getString("estatusUUID"),foliosData.get("respuesta").toString());
						list.add(datos);
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), data.getString("acuse"), list, "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new AceptarRechazarCancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
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
					JSONObject data = body.getJSONObject("data");
					JSONArray foliosJson = data.getJSONArray("folios");
					List<CancelationData> list = new LinkedList<CancelationData>();
					for (int i = 0; i < foliosJson.length(); i++) {
						JSONObject foliosData = new JSONObject(foliosJson.get(i).toString());
						CancelationData datos = new CancelationData(foliosData.getString("uuid"),foliosData.getString("estatusUUID"),foliosData.get("respuesta").toString());
						list.add(datos);
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), data.getString("acuse"), list, "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new AceptarRechazarCancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new AceptarRechazarCancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
