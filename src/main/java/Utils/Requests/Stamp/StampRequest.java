package Utils.Requests.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {
        try {
            String xmlStr = ((StampOptionsRequest) request).getXml();
            String boundary = UUID.randomUUID().toString();
            String raw = "--"+boundary+"\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"+xmlStr+"\r\n--"+boundary+"--";

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(request.URI);
            httppost.setHeader("Authorization", "bearer " + request.Token);
            httppost.addHeader("Content-Type", "multipart/form-data; boundary="+boundary);
            httppost.addHeader("Content-Disposition", "form-data; name=xml; filename=xml");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
        	builder.setCharset(chars);
        	builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("xml", raw, ContentType.DEFAULT_BINARY);
            httppost.setEntity(builder.build());
            
            CloseableHttpResponse responseB = client.execute(httppost);
            HttpEntity entity = responseB.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            int statusE = responseB.getStatusLine().getStatusCode();
            client.close();
            responseB.close();
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
            e.printStackTrace();
            throw new GeneralException(500,e.getMessage());
        }


    }
}
