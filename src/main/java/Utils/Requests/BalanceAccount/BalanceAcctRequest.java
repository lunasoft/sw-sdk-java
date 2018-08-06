package Utils.Requests.BalanceAccount;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.BalanceAcctResponse;
import Utils.Responses.IResponse;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

//@author: Lupita ALvarado
public class BalanceAcctRequest implements IRequestor {

    @Override
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException, IOException {
        try {
        	CloseableHttpClient client = HttpClients.createDefault();
        	HttpGet httpget = new HttpGet(request.URI);
        	httpget.setHeader("Authorization", "bearer " + request.Token);
        	
        	CloseableHttpResponse responseB = client.execute(httpget);
        	HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int statusE = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            if(!responseString.isEmpty()) {
                JSONObject body = new JSONObject(responseString);
                if (statusE == 200) {
                    JSONObject data = body.getJSONObject("data");

                    return new BalanceAcctResponse(statusE, body.getString("status"),
                            data.getString("idSaldoCliente"), data.getString("idClienteUsuario"), data.getInt("saldoTimbres"), data.getInt("timbresUtilizados"),
                            data.getString("fechaExpiracion"), data.getBoolean("unlimited"), data.getInt("timbresAsignados"),"OK","OK");
                } 
                else {
                    String messageDetail = "";

                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BalanceAcctResponse(statusE, body.getString("status"), body.getString("message"), messageDetail);
                }
            } else {
                return new BalanceAcctResponse(statusE, "error", responseB.getStatusLine().getReasonPhrase(), responseB.getStatusLine().getReasonPhrase());
            }

        } catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        }

    }

}
