package Utils.Requests.Authentication;

import Exceptions.GenaralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.AuthResponse;
import Utils.Responses.IJSend;
import Utils.Responses.IResponse;
import Utils.Responses.JSendFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;


/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthRequest implements IRequestor {


    @Override
    public IResponse sendRequest(IRequest request) throws GenaralException {
        if (request.URI.isEmpty()){
            throw new GenaralException(500,"URL VACIA");
        }
        try {
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("usuario",request.User)
                    .header("contrasena",request.Password).asJson();
            JSONObject parser = new JSONObject(response.getBody().toString());

            return (IResponse) JSendFactory.response(
                    parser.getJSONObject("status").toString(),
                    parser.getJSONObject("status").toString().equals("fail") ? parser.getJSONObject("message").toString() : parser.getJSONObject("data").toString(),
                    parser.getJSONObject("status").toString().equals("fail") ? Integer.parseInt(parser.getJSONObject("code").toString()) : null
            );



        } catch (UnirestException e) {

            throw new GenaralException(500,"SERVIDOR INACTIVO");
        }
        catch(JSONException e){
            return new AuthResponse(500,e.getMessage());
        }


    }

}
