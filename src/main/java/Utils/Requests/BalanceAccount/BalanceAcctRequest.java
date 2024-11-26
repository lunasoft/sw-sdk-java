package Utils.Requests.BalanceAccount;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Responses.IResponse;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * La clase BalanceAcctRequest maneja las solicitudes relacionadas con las
 * operaciones de saldo de cuentas.
 * Utiliza la biblioteca Apache HttpClient para realizar solicitudes HTTP.
 */
public class BalanceAcctRequest {
    public static IResponse createBalanceAcctRequest(IRequest request)
            throws GeneralException, AuthException, IOException {
        return new BalanceAcctRequest().balanceAcctRequest(request);
    }

    public static IResponse createBalanceStampRequest(IRequest request, String action, int stamps, String comment)
            throws GeneralException, AuthException, IOException {
        return new BalanceAcctRequest().balanceAcctStampRequest(request, action, stamps, comment);
    }

    private IResponse balanceAcctRequest(IRequest request) throws GeneralException, AuthException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(request.URI);
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
                    JSONObject data = body.getJSONObject("data");

                    // Asegúrate de obtener correctamente los nuevos campos, incluyendo la
                    // subestructura "lastTransaction".
                    return new BalanceAcctResponse(
                            status,
                            body.getString("status"),
                            data.getString("idUserBalance"),
                            data.getString("idUser"),
                            data.getInt("stampsBalance"),
                            data.getInt("stampsUsed"),
                            data.getInt("stampsAssigned"),
                            data.getBoolean("isUnlimited"),
                            data.get("expirationDate") != null ? data.get("expirationDate").toString() : null,
                            // Agregar la subestructura de la transacción
                            new BalanceAcctResponse.LastTransaction(
                                    data.getJSONObject("lastTransaction").getInt("folio"),
                                    data.getJSONObject("lastTransaction").getString("idUser"),
                                    data.getJSONObject("lastTransaction").getString("idUserReceiver"),
                                    data.getJSONObject("lastTransaction").getString("nameReceiver"),
                                    data.getJSONObject("lastTransaction").has("stampsIn")
                                            && !data.getJSONObject("lastTransaction").isNull("stampsIn")
                                                    ? data.getJSONObject("lastTransaction").getInt("stampsIn")
                                                    : null,
                                    data.getJSONObject("lastTransaction").has("stampsOut")
                                            && !data.getJSONObject("lastTransaction").isNull("stampsOut")
                                                    ? data.getJSONObject("lastTransaction").getInt("stampsOut")
                                                    : null,
                                    data.getJSONObject("lastTransaction").has("stampsCurrent")
                                            && !data.getJSONObject("lastTransaction").isNull("stampsCurrent")
                                                    ? data.getJSONObject("lastTransaction").getInt("stampsCurrent")
                                                    : null,
                                    data.getJSONObject("lastTransaction").has("comment")
                                            && !data.getJSONObject("lastTransaction").isNull("comment")
                                                    ? data.getJSONObject("lastTransaction").getString("comment")
                                                    : null,
                                    data.getJSONObject("lastTransaction").getString("date"),
                                    data.getJSONObject("lastTransaction").getBoolean("isEmailSent")),
                            "OK", "OK");

                } else {
                    String messageDetail = "";

                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BalanceAcctResponse(status, body.getString("status"), body.getString("message"),
                            messageDetail);
                }
            } else {
                return new BalanceAcctResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
                        responseString);
            }

        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    private IResponse balanceAcctStampRequest(IRequest request, String action, int stamps, String comment)
            throws GeneralException, AuthException, IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpEntityEnclosingRequestBase httpRequest;

            if ("add".equalsIgnoreCase(action)) {
                httpRequest = new HttpPost(request.URI);
            } else if ("remove".equalsIgnoreCase(action)) {
                httpRequest = new HttpDeleteWithBody(request.URI);
            } else {
                throw new IllegalArgumentException("Acción no válida: " + action);
            }
            httpRequest.setHeader("Authorization", "bearer " + request.Token);
            httpRequest.setHeader("Content-Type", "application/json");
            RequestHelper.setTimeOut(request.options, 3500);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httpRequest.setConfig(request.options.build());
            JSONObject json = new JSONObject();
            json.put("stamps", stamps);
            json.put("comment", comment);
            httpRequest.setEntity(new StringEntity(json.toString()));
            try (CloseableHttpResponse response = client.execute(httpRequest)) {
                int status = response.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONObject body = new JSONObject(responseString);

                if (status == 200) {
                    return new BalanceAcctResponse(status, body.getString("status"),
                            "Nuevo saldo: " + body.getInt("data"), "");
                } else {
                    String messageDetail = body.optString("messageDetail", "");
                    return new BalanceAcctResponse(status, body.getString("status"), body.getString("message"),
                            messageDetail);
                }
            }
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }

}