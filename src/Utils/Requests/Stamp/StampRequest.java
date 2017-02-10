package Utils.Requests.Stamp;

import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.IResponse;
import Utils.Responses.StampResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by asalvio on 09/02/2017.
 */
public class StampRequest implements IRequestor {
    @Override
    public IResponse sendRequest(IRequest request) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {

            //((StampOptionsRequest) request).getXml()
           //byte[] xml = ((StampOptionsRequest) request).getXml().getBytes();

            String xmlStr = ((StampOptionsRequest) request).getXml();
            File tempFile = File.createTempFile("tmp-", ".xml");
            System.out.println(tempFile.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write("This is the temporary file content");
            bw.close();


            tempFile.deleteOnExit();


            HttpResponse<JsonNode> response = Unirest.post(request.URI+((StampOptionsRequest) request).getVersion())
                    .header("Authorization",request.Token)

                    .field("xml",tempFile).asJson();
            JSONObject parser = new JSONObject(response.getBody().toString());
            //parser.getJSONObject("data").getString("mensaje").toString()
            if (response.getStatus()== 200){
                System.out.println("correlto");

                return new StampResponse(response.getStatus(),parser.getJSONObject("data").getString("tfd").toString());
            }
            return new StampResponse(response.getStatus(),parser.getJSONObject("data").getString("mensaje").toString());

        } catch (UnirestException e) {

            return new StampResponse(500,e.getMessage());
        }
        catch (RuntimeException e){
            return new StampResponse(500,e.getMessage());
        } catch (IOException e) {
            return new StampResponse(500,e.getMessage());
        }

    }
}
