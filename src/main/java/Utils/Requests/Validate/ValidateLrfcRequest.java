package Utils.Requests.Validate;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Validate.ValidateLrfcResponse;

public class ValidateLrfcRequest implements IRequestor{

	public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
            HttpGet http = new HttpGet(request.URI); 
            RequestHelper.setTimeOut(http, 7000);
            http.setHeader("Authorization", "bearer " + request.Token);
            CloseableHttpResponse responseB = client.execute(http);
            
            HttpEntity entity = responseB.getEntity();
            
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int statusE = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            if(!responseString.isEmpty()) {
            	JSONObject body = new JSONObject(responseString);
            	if(statusE==200){
            		JSONObject data = new JSONObject(body.get("data").toString());
            		return new ValidateLrfcResponse(statusE,body.getString("status"),data.get("contribuyenteRFC").toString(),data.getBoolean("sncf"),data.getBoolean("subcontratacion"),"OK","OK");
            	}
            	else {
            		String messageDetail = "";
            		if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new ValidateLrfcResponse(statusE, body.getString("status"), "", messageDetail);
            	}
            }
            else {
            	return new ValidateLrfcResponse(statusE,"error",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());
            }
		}
		catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneralException(500,e.getMessage());
        }
	}

}
