package Utils.Requests.Csd;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Requests.Csd.CsdOptionsRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Csd.UploadCsdResponse;;

public class CsdRequest implements IRequestor {
	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("certificate_type", ((CsdOptionsRequest) request).getCertificateType());
			requestJSON.put("password", ((CsdOptionsRequest) request).getPasswordCsd());
			requestJSON.put("is_active", ((CsdOptionsRequest) request).getIsActive());
			requestJSON.put("b64Cer", ((CsdOptionsRequest) request).getB64Cer());
			requestJSON.put("b64Key", ((CsdOptionsRequest) request).getB64key());
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(httppost, requestJSON.toString().length());
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
					String data = body.getString("data");
					return new UploadCsdResponse(status, body.getString("status"),data, "OK", "OK");

				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new UploadCsdResponse(status, body.getString("status"), body.getString("message"), messageDetail);
				}
			} else {
				return new UploadCsdResponse(status, "error", responseB.getStatusLine().getReasonPhrase(), responseB.getStatusLine().getReasonPhrase());
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
