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
            if (response.getStatus()>=401 && response.getStatus()<=403){
                throw new AuthException(401,"Token invalido");
            }
            else if(response.getStatus()==404){
                throw new GeneralException(404,"URL no encontrada");
            }

            else if(response.getStatus()==400 || response.getStatus()==500){
                JSONObject parser = new JSONObject(response.getBody().toString());
                return new BadResponse(response.getStatus(), parser.getString("status").toString(), parser.getString("message").toString(), parser.getString("messageDetail").toString());
            }
            JSONObject parser = new JSONObject(response.getBody().toString());
            JSONObject data = new JSONObject(parser.get("data").toString());
            String tfd = null;
            String cfdi = null;

            if(request.version.equalsIgnoreCase("v4")){
                return new SuccessV4Response(response.getStatus(),parser.getString("status"),data.getString("cfdi"),data.getString("cadenaOriginalSAT"),data.getString("noCertificadoSAT"),data.getString("noCertificadoCFDI"),data.getString("uuid"),data.getString("selloSAT"),data.getString("selloCFDI"),data.getString("fechaTimbrado"),data.getString("qrCode"));
            }
            if(data.has("tfd")){
                tfd = data.getString("tfd");
            }
            if(data.has("cfdi")){
                cfdi = data.getString("cfdi");
            }
            return new SuccessCFDIResponse(response.getStatus(),data.toString(),parser.getString("status").toString(),tfd,cfdi);




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
