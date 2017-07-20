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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.UUID;

public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {


        try {

            String xmlStr = ((StampOptionsRequest) request).getXml();
            String boundary = UUID.randomUUID().toString();
            String raw = "--"+boundary+"\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"+xmlStr+"\r\n--"+boundary+"--";

            Unirest.setTimeouts(60000, 360000);
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization","bearer "+request.Token)
                    .header("content-type","multipart/form-data; boundary="+boundary)
                    .body( raw).asJson();


            if(!response.getBody().toString().equalsIgnoreCase("{}")) {
                JSONObject body = new JSONObject(response.getBody().toString());
                if(response.getStatus()==200){
                    JSONObject data = body.getJSONObject("data");

                    if (request.version.equalsIgnoreCase("v1")) {
                        return new SuccessV1Response(response.getStatus(),body.getString("status"),data.getString("tfd"),"OK","OK");
                    }
                    else if(request.version.equalsIgnoreCase("v2")){
                        return new SuccessV2Response(response.getStatus(),body.getString("status"),data.getString("tfd"),data.getString("cfdi"),"OK","OK");
                    }
                    else if(request.version.equalsIgnoreCase("v3")){
                        return new SuccessV3Response(response.getStatus(),body.getString("status"),data.getString("cfdi"),"OK","OK");

                    }else if(request.version.equalsIgnoreCase("v4")){
                        return new SuccessV4Response(response.getStatus(),body.getString("status"),data.getString("cfdi"),data.getString("cadenaOriginalSAT"),data.getString("noCertificadoSAT"),data.getString("noCertificadoCFDI"),data.getString("uuid"),data.getString("selloSAT"),data.getString("selloCFDI"),data.getString("fechaTimbrado"),data.getString("qrCode"),"OK","OK");
                    }
                    else{
                        return new SuccessV1Response(response.getStatus(),body.getString("status"),data.toString(),"OK","OK");
                    }


                }
                else{
                    String messageDetail = "";


                    if (!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    if (request.version.equalsIgnoreCase("v1")) {
                        return new SuccessV1Response(response.getStatus(),body.getString("status"),"",body.getString("message"),messageDetail);
                    }
                    else if(request.version.equalsIgnoreCase("v2")){
                        return new SuccessV2Response(response.getStatus(),body.getString("status"),"","",body.getString("message"),messageDetail);
                    }
                    else if(request.version.equalsIgnoreCase("v3")){
                        return new SuccessV3Response(response.getStatus(),body.getString("status"),"",body.getString("message"),messageDetail);

                    }else if(request.version.equalsIgnoreCase("v4")){
                        return new SuccessV4Response(response.getStatus(),body.getString("status"),"","","","","","","","","",body.getString("message"),messageDetail);
                    }
                    else{
                        return new SuccessV1Response(response.getStatus(),body.getString("status"),"",body.getString("message"),messageDetail);
                    }

                }
            }
            else{
                if (request.version.equalsIgnoreCase("v1")) {
                    return new SuccessV1Response(response.getStatus(),"error","",response.getStatusText(),response.getStatusText());
                }
                else if(request.version.equalsIgnoreCase("v2")){
                    return new SuccessV2Response(response.getStatus(),"error","","",response.getStatusText(),response.getStatusText());
                }
                else if(request.version.equalsIgnoreCase("v3")){
                    return new SuccessV3Response(response.getStatus(),"error","",response.getStatusText(),response.getStatusText());

                }else if(request.version.equalsIgnoreCase("v4")){
                    return new SuccessV4Response(response.getStatus(),"error","","","","","","","","","",response.getStatusText(),response.getStatusText());
                }
                else{
                    return new SuccessV1Response(response.getStatus(),"error","",response.getStatusText(),response.getStatusText());
                }


            }












        } catch (UnirestException e) {

            throw  new GeneralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        }



    }
}
