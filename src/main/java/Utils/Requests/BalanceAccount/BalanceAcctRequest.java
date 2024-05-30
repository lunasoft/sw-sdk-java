package Utils.Requests.BalanceAccount;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.BalanceAccount.BalanceAcctResponse;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
    public static IResponse createBalanceAcctRequest(IRequest request) throws GeneralException, AuthException, IOException {
        return new BalanceAcctRequest().balanceAcctRequest(request);
    }

    public static IResponse createBalanceStampRequest(IRequest request, String comment) throws GeneralException, AuthException, IOException {
        return new BalanceAcctRequest().balanceAcctStampRequest(request, comment);
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
            if(!responseString.isEmpty() && status < 500) {
                JSONObject body = new JSONObject(responseString);
                if (status == 200) {
                    JSONObject data = body.getJSONObject("data");

                    return new BalanceAcctResponse(status, body.getString("status"),
                            data.getString("idSaldoCliente"), data.getString("idClienteUsuario"), data.getInt("saldoTimbres"), data.getInt("timbresUtilizados"),
                            data.get("fechaExpiracion").toString(), data.getBoolean("unlimited"), data.getInt("timbresAsignados"),"OK","OK");
                } 
                else {
                    String messageDetail = "";

                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BalanceAcctResponse(status, body.getString("status"), body.getString("message"), messageDetail);
                }
            } else {
                return new BalanceAcctResponse(status, "error", responseB.getStatusLine().getReasonPhrase(), responseString);
            }

        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    private IResponse balanceAcctStampRequest (IRequest request, String comment) throws GeneralException, AuthException, IOException {
        try {
        	CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(request.URI);
            httpPost.setHeader("Authorization", "Bearer " + request.Token);
            httpPost.setHeader("Content-Type", "application/json");
            RequestHelper.setTimeOut(request.options, 3500);
            RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
            httpPost.setConfig(request.options.build());

            JSONObject json = new JSONObject();
            json.put("Comentario", comment);
            
            StringEntity entity = new StringEntity(json.toString());
            httpPost.setEntity(entity);

            CloseableHttpResponse responseB = client.execute(httpPost);

            int status = responseB.getStatusLine().getStatusCode();
            String responseString = EntityUtils.toString(responseB.getEntity(), "UTF-8");
            JSONObject body = new JSONObject(responseString);

            client.close();
            responseB.close();

            if (status == 200) {
                return new BalanceAcctResponse(status, body.getString("status"), body.getString("data"), "");
            }else{
                String messageDetail = "";

                if (!body.isNull("messageDetail")) {
                    messageDetail = body.getString("messageDetail");
                }
                return new BalanceAcctResponse(status, body.getString("status"), body.getString("message"), messageDetail);
            }
                              
        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }
}