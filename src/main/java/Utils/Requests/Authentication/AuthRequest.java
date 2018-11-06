package Utils.Requests.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.Authentication.SuccessAuthResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AuthRequest implements IRequestor {
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, IOException {

        if (request.URI.isEmpty()){
            throw new GeneralException(400,"URL VACIA");
        }

        String messageDetail = "";
        try {
        	CloseableHttpClient client = HttpClients.createDefault();
        	HttpPost httppost = new HttpPost(request.URI);
        	httppost.setHeader("user", request.User);
            httppost.addHeader("password", request.Password);
            
            CloseableHttpResponse responseB = client.execute(httppost);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int status = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
            if(!responseString.isEmpty()) {
                    JSONObject body = new JSONObject(responseString);
                    if(!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }

                    if(status==200){
                        JSONObject data = body.getJSONObject("data");
                        return new SuccessAuthResponse(status,body.getString("status"),data.getString("token"),data.getInt("expires_in"),"OK","OK");
                    }
                    else{
                        return new SuccessAuthResponse(status,body.getString("status"),"",0,body.getString("message"),messageDetail);

                    }
            }
            else{
                return new SuccessAuthResponse(status,"error","",0,responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());

            }

        } catch(JSONException e){
            throw new GeneralException(500,e.getMessage());
        }


    }

}
