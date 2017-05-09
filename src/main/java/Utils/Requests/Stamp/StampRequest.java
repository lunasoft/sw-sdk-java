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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {


        try {

            String xmlStr = ((StampOptionsRequest) request).getXml();

            File tempFile = File.createTempFile("tmp-", ".xml");
            //BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            BufferedWriter bw = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(tempFile),"UTF-8"));
            bw.write(xmlStr);
            bw.close();

            tempFile.deleteOnExit();

            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization","bearer "+request.Token)

                    .field("xml",tempFile).asJson();
            if(!response.getBody().toString().isEmpty()) {
                JSONObject body = new JSONObject(response.getBody().toString());
                if(response.getStatus()==200){
                    JSONObject data = body.getJSONObject("data");
                    String tfd = null;
                    String cfdi = null;
                    if(request.version.equalsIgnoreCase("v4")){
                        return new SuccessV4Response(response.getStatus(),body.getString("status"),data.getString("cfdi"),data.getString("cadenaOriginalSAT"),data.getString("noCertificadoSAT"),data.getString("noCertificadoCFDI"),data.getString("uuid"),data.getString("selloSAT"),data.getString("selloCFDI"),data.getString("fechaTimbrado"),data.getString("qrCode"));
                    }
                    if(data.has("tfd")){
                        tfd = data.getString("tfd");
                    }
                    if(data.has("cfdi")){
                        cfdi = data.getString("cfdi");
                    }
                    return new SuccessCFDIResponse(response.getStatus(),data.toString(),body.getString("status").toString(),tfd,cfdi);
                }
                else{
                    return new BadResponse(response.getStatus(),body.getString("status"),body.getString("message"),body.getString("messageDetail"));
                }
            }
            else{
                return new BadResponse(response.getStatus(),"error",response.getStatusText(),response.getStatusText());
            }












        } catch (UnirestException e) {

            throw  new GeneralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        }
        catch(java.net.MalformedURLException e){
            throw  new GeneralException(404,"HOST DESCONOCIDO");
        } catch (IOException e) {

            throw  new GeneralException(404,e.getCause().getMessage());
        }


    }
}
