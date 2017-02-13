package Utils.Requests.Stamp;

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

import org.json.JSONObject;

import java.io.*;


/**
 * Created by asalvio on 09/02/2017.
 */
public class StampRequest implements IRequestor {

    public IResponse sendRequest(IRequest request) throws GenaralException {


        try {

            String xmlStr = ((StampOptionsRequest) request).getXml();


            File tempFile = File.createTempFile("tmp-", ".xml");



            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write(xmlStr);
            bw.close();
            tempFile.deleteOnExit();

            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization",request.Token)

                    .field("xml",tempFile).asJson();

            JSONObject parser = new JSONObject(response.getBody().toString());
            return (IResponse) JSendFactory.response(
                    parser.getJSONObject("status").toString(),
                    parser.getJSONObject("status").toString().equals("fail") ? parser.getJSONObject("message").toString() : parser.getJSONObject("data").toString(),
                    parser.getJSONObject("status").toString().equals("fail") ? Integer.parseInt(parser.getJSONObject("code").toString()) : null
            );


        } catch (UnirestException e) {

            throw  new GenaralException(404,"SERVIDOR INACTIVO");
        }
         catch (IOException e) {
            return new StampResponse(500,e.getMessage());
        }

    }
}
