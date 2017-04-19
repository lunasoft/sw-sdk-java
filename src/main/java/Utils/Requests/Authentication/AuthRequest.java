package Utils.Requests.Authentication;

import Exceptions.GeneralException;
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

public class AuthRequest implements IRequestor {


    @Override
    public IResponse sendRequest(IRequest request) throws GeneralException {

        if (request.URI.isEmpty()){
            throw new GeneralException(500,"URL VACIA");
        }
        try {

            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("user",request.User)
                    .header("password",request.Password).asJson();
            JSONObject parser = new JSONObject(response.getBody().toString());

            return (IResponse) JSendFactory.response(
                    parser.getString("status").toString(),
                    parser.getString("status").toString().equals("error") ? parser.getString("message").toString() : parser.getJSONObject("data").toString(),
                    response.getStatus()
            );



        } catch (UnirestException e) {

            throw new GeneralException(500,"SERVIDOR INACTIVO");
        }
        catch(JSONException e){
            throw new GeneralException(500,e.getMessage());
        }


    }

}
