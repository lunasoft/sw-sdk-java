package Utils.Requests.Csd;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

import java.util.LinkedList;
import java.util.List;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Requests.Csd.CsdOptionsRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Csd.CsdResponse;
import Utils.Responses.Csd.InfoCsd;
import Utils.Responses.Csd.InfoCsdResponse;
import Utils.Responses.Csd.ListInfoCsdResponse;

public class CsdRequest implements IRequestor {
	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			if (RequestHelper.stringEmptyOrNull(((CsdOptionsRequest) request).getB64Cer())
					|| RequestHelper.stringEmptyOrNull(((CsdOptionsRequest) request).getB64key())) {
				return new CsdResponse(400, "error", "El certificado o llave proporcionados vienen vacíos", "");
			}
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("type", ((CsdOptionsRequest) request).getCertificateType());
			requestJSON.put("password", ((CsdOptionsRequest) request).getPasswordCsd());
			requestJSON.put("is_active", ((CsdOptionsRequest) request).getIsActive());
			requestJSON.put("b64Cer", ((CsdOptionsRequest) request).getB64Cer());
			requestJSON.put("b64Key", ((CsdOptionsRequest) request).getB64key());
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
					String data = body.getString("data");
					return new CsdResponse(status, body.getString("status"), data, "OK", "OK");

				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CsdResponse(status, body.getString("status"), body.getString("message"), messageDetail);
				}
			} else {
				return new CsdResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse DisableCsdRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpDelete http = new HttpDelete(request.URI);
			RequestHelper.setTimeOut(request.options, 3000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			http.setConfig(request.options.build());
			http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			http.addHeader(new BasicHeader("Content-Type", "application/json"));
			CloseableHttpResponse responseB = client.execute(http);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					String data = body.getString("data");
					return new CsdResponse(status, body.getString("status"), data, "OK", "OK");

				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new CsdResponse(status, body.getString("status"), body.getString("message"), messageDetail);
				}
			} else {
				return new CsdResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse GetListCsdRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet http = new HttpGet(request.URI);
			RequestHelper.setTimeOut(request.options, 3000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			http.setConfig(request.options.build());
			http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			http.addHeader(new BasicHeader("Content-Type", "application/json"));
			CloseableHttpResponse responseB = client.execute(http);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONArray data = body.getJSONArray("data");
					List<InfoCsd> lista = new LinkedList<InfoCsd>();
					for (int i = 0; i < data.length(); i++) {
						JSONObject dato = new JSONObject(data.get(i).toString());
						InfoCsd datos = new InfoCsd(dato.getString("issuer_rfc"), dato.getString("certificate_number"),
								dato.getBoolean("is_active"), dato.getString("issuer_business_name"),
								dato.getString("valid_from"), dato.getString("valid_to"),
								dato.getString("certificate_type"));
						lista.add(datos);
					}
					return new ListInfoCsdResponse(status, body.getString("status"), lista, "OK", "OK");

				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new ListInfoCsdResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new ListInfoCsdResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse GetInfoCsdRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet http = new HttpGet(request.URI);
			RequestHelper.setTimeOut(request.options, 3000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			http.setConfig(request.options.build());
			http.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			http.addHeader(new BasicHeader("Content-Type", "application/json"));
			CloseableHttpResponse responseB = client.execute(http);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");
					InfoCsd datos = new InfoCsd(data.getString("issuer_rfc"), data.getString("certificate_number"),
							data.getBoolean("is_active"), data.getString("issuer_business_name"),
							data.getString("valid_from"), data.getString("valid_to"),
							data.getString("certificate_type"));
					return new InfoCsdResponse(status, body.getString("status"), datos, "OK", "OK");

				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new InfoCsdResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new InfoCsdResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
