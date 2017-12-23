package Utils.Requests.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.mashape.unirest.request.body.MultipartBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.UUID;

public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {


        try {

            String xmlStr = ((StampOptionsRequest) request).getXml();
            String hostProxy = ((StampOptionsRequest) request).getProxyHost();
            String portProxy = ((StampOptionsRequest) request).getPortHost();
            String boundary = UUID.randomUUID().toString();
            String raw = "--"+boundary+"\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"+xmlStr+"\r\n--"+boundary+"--";

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

                    if (request.version.equalsIgnoreCase("v1")) {
                        return new SuccessV1Response(statusE,body.getString("status"),data.getString("tfd"),"OK","OK");
                    }
                    else if(request.version.equalsIgnoreCase("v2")){
                        return new SuccessV2Response(statusE,body.getString("status"),data.getString("tfd"),data.getString("cfdi"),"OK","OK");
                    }
                    else if(request.version.equalsIgnoreCase("v3")){
                        return new SuccessV3Response(statusE,body.getString("status"),data.getString("cfdi"),"OK","OK");

                    }else if(request.version.equalsIgnoreCase("v4")){
                        return new SuccessV4Response(statusE,body.getString("status"),data.getString("cfdi"),data.getString("cadenaOriginalSAT"),data.getString("noCertificadoSAT"),data.getString("noCertificadoCFDI"),data.getString("uuid"),data.getString("selloSAT"),data.getString("selloCFDI"),data.getString("fechaTimbrado"),data.getString("qrCode"),"OK","OK");
                    }
                    else{
                        return new SuccessV1Response(statusE,body.getString("status"),data.toString(),"OK","OK");
                    }


                }
                else{
                    String messageDetail = "";


                    if (!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    if (request.version.equalsIgnoreCase("v1")) {
                        return new SuccessV1Response(statusE,body.getString("status"),"",body.getString("message"),messageDetail);
                    }
                    else if(request.version.equalsIgnoreCase("v2")){
                        return new SuccessV2Response(statusE,body.getString("status"),"","",body.getString("message"),messageDetail);
                    }
                    else if(request.version.equalsIgnoreCase("v3")){
                        return new SuccessV3Response(statusE,body.getString("status"),"",body.getString("message"),messageDetail);

                    }else if(request.version.equalsIgnoreCase("v4")){
                        return new SuccessV4Response(statusE,body.getString("status"),"","","","","","","","","",body.getString("message"),messageDetail);
                    }
                    else{
                        return new SuccessV1Response(statusE,body.getString("status"),"",body.getString("message"),messageDetail);
                    }

                }
            }
            else{
                if (request.version.equalsIgnoreCase("v1")) {
                    return new SuccessV1Response(statusE,"error","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());
                }
                else if(request.version.equalsIgnoreCase("v2")){
                    return new SuccessV2Response(statusE,"error","","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());
                }
                else if(request.version.equalsIgnoreCase("v3")){
                    return new SuccessV3Response(statusE,"error","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());

                }else if(request.version.equalsIgnoreCase("v4")){
                    return new SuccessV4Response(statusE,"error","","","","","","","","","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());
                }
                else{
                    return new SuccessV1Response(statusE,"error","",responseB.getStatusLine().getReasonPhrase(),responseB.getStatusLine().getReasonPhrase());
                }


            }












        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new GeneralException(500,e.getMessage());
        }


    }
}
