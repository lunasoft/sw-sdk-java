package Utils.Requests.Stamp;

import Exceptions.AuthException;
import Exceptions.GenaralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.JSendFactory;
import Utils.Responses.StampResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GenaralException, AuthException {


        try {

            String xmlStr = ((StampOptionsRequest) request).getXml();
            File tempFile = File.createTempFile("tmp-", ".xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write(xmlStr);
            bw.close();
            tempFile.deleteOnExit();
            System.out.println(request.URI);
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization",request.Token)

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

            throw  new GenaralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GenaralException(500,e.getMessage());
        }
        catch(java.net.MalformedURLException e){
            throw  new GenaralException(404,"HOST DESCONOCIDO");
        } catch (IOException e) {
            e.printStackTrace();
            throw  new GenaralException(404,e.getCause().getMessage());
        }


    }
}
