package Utils.Requests.Authentication;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Requests.Stamp.StampOptionsRequest;
import Utils.Responses.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;

public class AuthRequest implements IRequestor {


    @Override
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {

        if (request.URI.isEmpty()){
            throw new GeneralException(500,"URL VACIA");
        }

        String messageDetail = "";
        try {
            String hostProxy = ((AuthOptionsRequest) request).getProxyHost();
            String portProxy = ((AuthOptionsRequest) request).getPortHost();

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            httppost.setHeader("user", request.User);
            httppost.setHeader("password", request.Password);
            if( hostProxy !=null && portProxy != null){
                HttpHost proxy = new HttpHost(hostProxy, Integer.parseInt(portProxy), request.URI.split(":")[0]);
                RequestConfig config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
                httppost.setConfig(config);
            }
            CloseableHttpResponse responseB = client.execute(httppost);

            InputStream inputStream = responseB.getEntity().getContent();

            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String responseString = s.hasNext() ? s.next() : "";

            int statusE = responseB.getStatusLine().getStatusCode();
            client.close();

            if(!responseString.isEmpty()) {
                JSONObject body = new JSONObject(responseString);
                if(statusE==200){
                    JSONObject data = body.getJSONObject("data");
                    return new SuccessAuthResponse(statusE,body.getString("status"),data.getString("token"),"OK","OK");
                }
                else{
                    if(!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    return new SuccessAuthResponse(statusE,body.getString("status"),"",body.getString("message"),messageDetail);
                }
            }
            else{
                return new SuccessAuthResponse(statusE,"error","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());

            }


        }
        catch(JSONException e){
            throw new GeneralException(500,e.getMessage());
        } catch (ClientProtocolException e) {
            throw new GeneralException(500,e.getMessage());
        } catch (IOException e) {
            throw new GeneralException(500,e.getMessage());
        }


    }

}
