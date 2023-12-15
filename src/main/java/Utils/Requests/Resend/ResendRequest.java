package Utils.Requests.Resend;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Exceptions.ValidationException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Resend.ResendResponse;

public class ResendRequest {

    // Método público para crear una solicitud de reenvío
    public static IResponse createResendRequest(IRequest request, UUID uuid, String correos)
            throws GeneralException, AuthException, IOException, ValidationException {
        return new ResendRequest().resendEmailRequest(request, uuid, correos);
    }

    // Método privado para enviar la solicitud de reenvío por correo
    private IResponse resendEmailRequest(IRequest request, UUID uuid, String correos)
            throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            httppost.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
            httppost.addHeader(new BasicHeader("Content-Type", "application/json"));

            JSONObject obj = buildResendJson((ResendOptionsRequest) request);
            StringEntity sEntity = new StringEntity(obj.toString());
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
                    String data = body.getString("data");
                    return new ResendResponse(status, body.getString("status"), data, "OK", "OK");

                } else {
                    String messageDetail = "";
                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new ResendResponse(status, body.getString("status"), body.getString("message"),
                            messageDetail);
                }
            } else {
                return new ResendResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    // Método privado para construir el JSON de la solicitud de reenvío
    private static JSONObject buildResendJson(ResendOptionsRequest resendOptionsRequest) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("to", resendOptionsRequest.getEmails());
        requestJSON.put("uuid", resendOptionsRequest.getUuid());
        return requestJSON;
    }
}
