package Utils.Requests.Cancelation;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

public class CancelationRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {

        try {
            String hostProxy = ((CancelationOptionsRequest) request).getProxyHost();
            String portProxy = ((CancelationOptionsRequest) request).getPortHost();

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            httppost.setHeader("Authorization", "bearer "+request.Token);
            httppost.setHeader("Content-Type", "application/json");
            JSONObject send = new JSONObject("{\r\n \"uuid\": \"" + ((CancelationOptionsRequest) request).getUuid() + "\",\r\n \"password\": \"" + ((CancelationOptionsRequest) request).getPassword_csd() + "\",\r\n \"rfc\": \"" + ((CancelationOptionsRequest) request).getRfc() + "\",\r\n \"b64Cer\": \"" + ((CancelationOptionsRequest) request).getB64Cer() + "\",\r\n \"b64Key\": \"" + ((CancelationOptionsRequest) request).getB64key() + "\"\r\n}");
            StringEntity d = new StringEntity(send.toString());
            httppost.setEntity(d);
            if( hostProxy !=null && portProxy != null){
                HttpHost proxy = new HttpHost(hostProxy, Integer.parseInt(portProxy));
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
                    String uuid = ((CancelationOptionsRequest) request).getUuid().toUpperCase();
                    JSONObject uuid_data = data.getJSONObject("uuid");
                    String uuidSC = uuid_data.getString(uuid);
                    return new CancelationResponse(statusE, body.getString("status"), data.getString("acuse"), uuid, Integer.parseInt(uuidSC),"OK","OK");
                }else{
                    String messageDetail = "";

                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new CancelationResponse(statusE,body.getString("status"),body.getString("message"),messageDetail);
                }
            }else{
                return new CancelationResponse(statusE, "error", responseB.getStatusLine().getReasonPhrase(), responseB.getStatusLine().getReasonPhrase());

            }



        }  catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (ClientProtocolException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (IOException e) {
            throw new GeneralException(500, e.getMessage());
        }
    }

    
    
    
    public IResponse sendRequest(IRequest request, boolean isXml) throws GeneralException, AuthException {

        try {

            String xmlStr = ((CancelationOptionsRequest) request).getXml();
            String hostProxy = ((CancelationOptionsRequest) request).getProxyHost();
            String portProxy = ((CancelationOptionsRequest) request).getPortHost();
            String boundary = UUID.randomUUID().toString();
            String raw = "--" + boundary + "\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n" + xmlStr + "\r\n--" + boundary + "--";

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );
            StringBody xmlcfdi = new StringBody(raw,  Charset.forName( "UTF-8" ));
            entity.addPart("xml",xmlcfdi);
            httppost.setEntity(entity);
            httppost.setHeader("Authorization", "bearer " + request.Token);
            httppost.setHeader("Content-Type", "multipart/form-data; boundary="+boundary);
            httppost.addHeader("Content-Disposition", "form-data; name=xml; filename=xml");

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
                    JSONObject uuid_data = data.getJSONObject("uuid");
                    String uuid = (String) uuid_data.keys().next();
                    int st_uuid = Integer.parseInt((String) uuid_data.get(uuid));
                    return new CancelationResponse(statusE, body.getString("status"), data.getString("acuse"), uuid, st_uuid,"OK","OK");
                }else{
                    String messageDetail = "";

                    if (!body.isNull("messageDetail")) {
                        messageDetail = body.getString("messageDetail");
                    }
                    return new CancelationResponse(statusE,body.getString("status"),body.getString("message"),messageDetail);
                }
            }else{
                return new CancelationResponse(statusE, "error", responseB.getStatusLine().getReasonPhrase(), responseB.getStatusLine().getReasonPhrase());

            }




        }  catch (JSONException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (ClientProtocolException e) {
            throw new GeneralException(500, e.getMessage());
        } catch (IOException e) {
            throw new GeneralException(500, e.getMessage());
        }

    }
}
