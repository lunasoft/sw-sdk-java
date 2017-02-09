package Utils.Requests.Authentication;

import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.AuthResponse;
import Utils.Responses.IResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;


/**
 * Created by asalvio on 08/02/2017.
 */
public class AuthRequest implements IRequestor {


    @Override
    public IResponse sendRequest(IRequest request) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("usuario",request.User)
                    .header("contrasena",request.Password).asJson();
            JSONObject parser = new JSONObject(response.getBody().toString());

            if (response.getStatus()== 200){
                return new AuthResponse(response.getStatus(),parser.getJSONObject("data").getString("token").toString());
            }
            return new AuthResponse(response.getStatus(),parser.getJSONObject("data").getString("mensaje").toString());

        } catch (UnirestException e) {

            return new AuthResponse(500,e.getMessage());
        }


    }

}
