package Utils.Requests.Storage;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Storage.StorageData;
import Utils.Responses.Storage.StorageResponse;

public class StorageRequest implements IRequestor {
    StorageData dataXml;

    public IResponse sendRequest(IRequest request)
            throws GeneralException, AuthException, GeneralException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(request.URI + ((StorageOptionsRequest) request).getUuid());
            httpget.setHeader("Authorization", "bearer " + request.Token);
            RequestHelper.setTimeOut(request.options, 3500);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httpget.setConfig(request.options.build());
            CloseableHttpResponse responseB = client.execute(httpget);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();

            if (!responseString.isEmpty() && status < 500) {
                JSONObject body = new JSONObject(responseString);
                if (status == 200) {
                    dataXml = deserialize(responseString, StorageData.class);
                    StorageResponse response = new StorageResponse(status, body.getString("status"), "OK", "OK",
                            dataXml);
                    if (response.getData() == null || response.getData().getData().getRecords().size() <= 0) {
                        response.setHttpStatusCode(400);
                        response.setStatus("error");
                        response.setMessage("Bad request.");
                        response.setMessageDetail("El UUID no ha sido encontrado");
                    }
                    return response;
                } else {
                    StorageResponse response = new StorageResponse(status, body.getString("status"), "OK", "OK",
                            dataXml);
                    return new StorageResponse(status, "error", response.getMessage(), response.getMessageDetail());
                }
            } else {
                return new StorageResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }

    }

    public <T> T deserialize(String json, Class<T> contentClass) throws JsonSyntaxException, IOException {
        if (json == null || json.isEmpty()) {
            throw new IOException("No se obtuvo respuesta para el request hecho.");
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, contentClass);
    }
}
