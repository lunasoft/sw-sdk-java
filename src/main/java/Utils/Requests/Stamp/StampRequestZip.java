package Utils.Requests.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Helpers.ResponseStamp;
import Utils.Helpers.RespuestaTimbrado;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

public class StampRequestZip {

	public IResponse sendRequestZip(IRequest request) throws GeneralException,
			AuthException {
		try {
			byte[] zipData = ((StampOptionsRequest) request).getZipData();

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(request.URI);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

			builder.addBinaryBody("xml", zipData, ContentType.APPLICATION_OCTET_STREAM,
					"zip");

			httppost.addHeader("Authorization", "Bearer " + request.Token);

			httppost.setEntity(builder.build());

			try (CloseableHttpResponse responseB = client.execute(httppost)) {
				String responseString = EntityUtils.toString(responseB.getEntity(), "UTF-8");
				int status = responseB.getStatusLine().getStatusCode();

				System.out.println(responseString);

				ResponseStamp R = RespuestaTimbrado.Stamped(request.version.charAt(request.version.length() -
						1));
				R.setReason(responseB.getStatusLine());
				R.setResponse(responseString);
				R.setStatus(status);

				return R.getResponse();
			}
		} catch (IOException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}