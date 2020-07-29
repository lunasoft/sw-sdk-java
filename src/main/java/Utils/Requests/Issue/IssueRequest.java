package Utils.Requests.Issue;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Helpers.ResponseStamp;
import Utils.Helpers.RespuestaTimbrado;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

public class IssueRequest implements IRequestor {

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {
		try {
			String xmlStr = ((IssueOptionsRequest) request).getXml();

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, xmlStr.length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader("Authorization", "bearer " + request.Token);
			httppost.addHeader("Content-Type", "application/jsontoxml");
			StringEntity stringEntity = new StringEntity(xmlStr,ContentType.create("application/json", Consts.UTF_8));
			httppost.setEntity(stringEntity);

			CloseableHttpResponse responseB = client.execute(httppost);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			ResponseStamp R = RespuestaTimbrado.Stamped(request.version.charAt(request.version.length()-1));
			R.setReason(responseB.getStatusLine());
			R.setResponse(responseString);
			R.setStatus(status);
			return R.getResponse();

		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new GeneralException(500, e.getMessage());
		}
	}

	public IResponse sendRequestXml(IRequest request) throws GeneralException, AuthException {
		try {
			String xmlStr = ((IssueOptionsRequest) request).getXml();
			String boundary = UUID.randomUUID().toString();
			String raw = "--" + boundary
					+ "\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"
					+ xmlStr + "\r\n--" + boundary + "--";

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);
			RequestHelper.setTimeOut(request.options, raw.length());
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httppost.setConfig(request.options.build());
			httppost.setHeader("Authorization", "bearer " + request.Token);
			httppost.addHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
			httppost.addHeader("Content-Disposition", "form-data; name=xml; filename=xml");
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Charset chars = Charset.forName("UTF-8");
			builder.setCharset(chars);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addTextBody("xml", raw, ContentType.create("text/plain", MIME.UTF8_CHARSET));
			httppost.setEntity(builder.build());
			CloseableHttpResponse responseB = client.execute(httppost);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			ResponseStamp R = RespuestaTimbrado.Stamped(request.version.charAt(request.version.length()-1));
			R.setReason(responseB.getStatusLine());
			R.setResponse(responseString);
			R.setStatus(status);
			return R.getResponse();
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new GeneralException(500, e.getMessage());
		}
	}
}
