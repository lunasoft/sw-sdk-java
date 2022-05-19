package Utils.Requests.Cancelation;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Cancelation.CancelationResponse;

import java.io.IOException;
import java.nio.charset.Charset;
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
import org.json.JSONException;
import org.json.JSONObject;

public class CancelationRequest implements IRequestor {

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {

		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			StringEntity sEntity = new StringEntity(
					"{\r\n \"uuid\": \"" + ((CancelationOptionsRequest) request).getUuid() + "\",\r\n \"password\": \""
							+ ((CancelationOptionsRequest) request).getPassword() + "\",\r\n \"rfc\": \""
							+ ((CancelationOptionsRequest) request).getRfc()+ "\",\r\n \"motivo\": \""
                                                        + ((CancelationOptionsRequest) request).getMotivo()+ "\",\r\n \"foliosustitucion\": \""
                                                        + ((CancelationOptionsRequest) request).getFolioSustitucion()+ "\",\r\n \"b64Cer\": \""
							+ ((CancelationOptionsRequest) request).getB64Cer() + "\",\r\n \"b64Key\": \""
							+ ((CancelationOptionsRequest) request).getB64key() + "\"\r\n}");
			httppost.setEntity(builder.build());
			httppost.setEntity(sEntity);
			RequestHelper.setTimeOut(request.options, (int) sEntity.getContentLength());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
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
					String uuid = ((CancelationOptionsRequest) request).getUuid().toUpperCase();
					JSONObject uuid_data = data.getJSONObject("uuid");
					String uuidSC = uuid_data.getString(uuid);
					return new CancelationResponse(status, body.getString("status"), data.getString("acuse"), uuid,
							Integer.parseInt(uuidSC), "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse sendRequestxml(IRequest request, boolean isXml) throws GeneralException, AuthException, IOException {

		try {
			String xmlStr = ((CancelationOptionsRequest) request).getXml();
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
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");
					String xml = ((CancelationOptionsRequest) request).getXml();
					String uuid = xml.substring(xml.indexOf("<Folio UUID=") +13, xml.indexOf("Motivo")-2).toUpperCase();
					JSONObject uuid_data = data.getJSONObject("uuid");
					String uuidSC = uuid_data.getString(uuid);
					return new CancelationResponse(status, body.getString("status"), data.getString("acuse"), uuid,
							Integer.parseInt(uuidSC), "OK", "OK");
				} else {
					String messageDetail = null;
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}

	}

	public IResponse sendRequestPfx(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			httppost.addHeader(new BasicHeader("Content-Type", "application/json"));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			StringEntity sEntity = new StringEntity(
					"{\r\n \"uuid\": \"" + ((CancelationOptionsRequest) request).getUuid() + "\",\r\n \"password\": \""
							+ ((CancelationOptionsRequest) request).getPassword() + "\",\r\n \"rfc\": \""
                                                        + ((CancelationOptionsRequest) request).getRfc() + "\",\r\n \"motivo\": \""
                                                        + ((CancelationOptionsRequest) request).getMotivo()+ "\",\r\n \"foliosustitucion\": \""
							+ ((CancelationOptionsRequest) request).getFolioSustitucion() + "\",\r\n \"b64Pfx\": \""
							+ ((CancelationOptionsRequest) request).getB64Pfx() + "\"\r\n}");
			httppost.setEntity(builder.build());
			httppost.setEntity(sEntity);
			RequestHelper.setTimeOut(request.options, (int) sEntity.getContentLength());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
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
					String uuid = ((CancelationOptionsRequest) request).getUuid().toUpperCase();
					JSONObject uuid_data = (JSONObject) data.get("uuid");
					String uuidSC = uuid_data.getString(uuid);
					return new CancelationResponse(status, body.getString("status"), data.getString("acuse"), uuid,
							Integer.parseInt(uuidSC), "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse sendRequestUuid(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, 4000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
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
					String uuid = ((CancelationOptionsRequest) request).getUuid().toUpperCase();
					JSONObject uuid_data = data.getJSONObject("uuid");
					String uuidSC = uuid_data.getString(uuid);
					return new CancelationResponse(status, body.getString("status"), data.getString("acuse"), uuid,
							Integer.parseInt(uuidSC), "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CancelationResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new CancelationResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
