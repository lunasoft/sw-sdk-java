package Utils.Requests.Pdf;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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
import Utils.Responses.IResponse;
import Utils.Responses.Pdf.PdfResponse;

public class PdfRequest implements IRequestor{

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
		try {
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("xmlContent", ((PdfOptionsRequest) request).getXml());
			requestJSON.put("logo", ((PdfOptionsRequest) request).getB64Logo());
			requestJSON.put("extras", ((PdfOptionsRequest) request).getExtras());
			requestJSON.put("templateId", ((PdfOptionsRequest) request).getTemplateId());

			CloseableHttpClient client = HttpClients.createDefault();
            HttpPost http = new HttpPost(request.URI);
            RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			http.setConfig(request.options.build());
            http.setHeader("Authorization", "Bearer " + request.Token);
            http.addHeader("Content-Type", "application/json");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
        	builder.setCharset(chars);
        	builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);


        	StringEntity sEntity = new StringEntity(requestJSON.toString(), "UTF-8");
			http.setEntity(builder.build());
			http.setEntity(sEntity);
			CloseableHttpResponse responseB = client.execute(http);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
                String message = body.isNull("message") ? "" : body.getString("message");
                String messageDetail = body.isNull("messageDetail") ? "" : body.getString("messageDetail");
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");
					return new PdfResponse(status, body.getString("status"), data.getString("contentB64"), data.getInt("contentSizeBytes"), 
                    data.getString("uuid"), data.getString("serie"), data.getString("folio"), data.getString("stampDate"),
                    data.getString("issuedDate"), data.getString("rfcIssuer"), data.getString("rfcReceptor"), data.getString("total"),
                    message, messageDetail);
				} else {
					return new PdfResponse(status, body.getString("status"), message, messageDetail);
				}
			} else {
				return new PdfResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		} catch (ClientProtocolException e) {
            throw new GeneralException(500, e.getMessage());
        }
	}
	public IResponse sendRequestUuid(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI + ((PdfOptionsRequest) request).getUuid());
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
				String messageDetail = "";
				if (status == 200) {
					return new PdfResponse(status, "success", body.getString("message"), messageDetail);
				} else {
					return new PdfResponse(status, "error", body.getString("message"),
							messageDetail);
				}
			} else {
				return new PdfResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
