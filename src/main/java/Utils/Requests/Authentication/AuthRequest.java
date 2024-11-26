package Utils.Requests.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Authentication.SuccessAuthResponse;

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

import java.io.IOException;
import java.nio.charset.Charset;

public class AuthRequest implements IRequestor {
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            // Verificar que los campos necesarios no estén vacíos
            if (RequestHelper.stringEmptyOrNull(((AuthOptionsRequest) request).getUser())
                    || RequestHelper.stringEmptyOrNull(((AuthOptionsRequest) request).getPassword())) {
                return new SuccessAuthResponse(400, "error", null, 0,
                        "El usuario o contraseña proporcionados están vacíos", null);
            }
            String messageDetail = "";

            // Crear JSON de la solicitud
            JSONObject requestJSON = new JSONObject();
            requestJSON.put("user", ((AuthOptionsRequest) request).getUser());
            requestJSON.put("password", ((AuthOptionsRequest) request).getPassword());

            // Configuración de la solicitud HTTP
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            RequestHelper.setTimeOut(request.options, requestJSON.toString().length());
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httppost.setConfig(request.options.build());
            httppost.addHeader(new BasicHeader("Content-Type", "application/json"));

            // Enviar los datos en el cuerpo de la solicitud
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            StringEntity sEntity = new StringEntity(requestJSON.toString());
            httppost.setEntity(sEntity);

            // Ejecutar la solicitud y procesar la respuesta
            CloseableHttpResponse responseB = client.execute(httppost);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();

            // Cerrar conexiones
            client.close();
            responseB.close();

            // Manejo de la respuesta
            if (!responseString.isEmpty() && status < 500) {
                JSONObject body = new JSONObject(responseString);
                if (!body.isNull("messageDetail")) {
                    messageDetail = body.getString("messageDetail");
                }

                if (status == 200) {
                    JSONObject data = body.getJSONObject("data");
                    return new SuccessAuthResponse(status, body.getString("status"), data.getString("token"),
                            data.getInt("expires_in"), "OK", "OK");
                } else {
                    return new SuccessAuthResponse(status, body.getString("status"), "", 0, body.getString("message"),
                            messageDetail);

                }
            } else {
                return new SuccessAuthResponse(status, "error", "", 0, responseB.getStatusLine().getReasonPhrase(),
                        responseString);

            }

        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }
}
