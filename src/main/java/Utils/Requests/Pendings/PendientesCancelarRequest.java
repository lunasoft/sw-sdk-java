package Utils.Requests.Pendings;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Pendings.PendientesCancelarResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PendientesCancelarRequest implements IRequestor {
	public IResponse sendRequest(IRequest request)
			throws GeneralException, AuthException, GeneralException, IOException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(request.URI);
			RequestHelper.setTimeOut(httpget, 7000);
			httpget.setHeader("Authorization", "bearer " + request.Token);
			CloseableHttpResponse responseB = client.execute(httpget);
			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty()) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");
					try {
						JSONArray uuids = new JSONArray(data.get("uuid").toString());
						List<String> UUIDS = new LinkedList<String>();
						for (int i = 0; i < uuids.length(); i++) {
							String dato = new String(uuids.get(i).toString());
							UUIDS.add(dato);
						}
						return new PendientesCancelarResponse(status, body.getString("status"), UUIDS, body.getString("codStatus"), body.getString("message"), "OK");
					} catch (JSONException e) {
						return new PendientesCancelarResponse(status, body.getString("status"), null, body.getString("codStatus"), body.getString("message"), "OK");
					}
				} else {
					String messageDetail = "";

					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new PendientesCancelarResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new PendientesCancelarResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseB.getStatusLine().getReasonPhrase());
			}

		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}

	}

}
