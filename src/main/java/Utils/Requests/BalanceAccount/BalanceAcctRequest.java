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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class BalanceAcctRequest implements IRequestor {
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException, IOException {
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

}
