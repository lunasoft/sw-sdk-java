package Utils.Requests.Stamp;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.JSendFactory;
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
            if (response.getStatus()==403){
                throw new AuthException(403,"Token invalido");
            }

            JSONObject parser = new JSONObject(response.getBody().toString());
            return (IResponse) JSendFactory.response(
                    parser.getString("status").toString(),
                    parser.getString("status").toString().equals("error") ? parser.getString("message").toString() : parser.getJSONObject("data").toString(),
                     response.getStatus()
            );


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
