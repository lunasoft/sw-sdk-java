package Utils.Requests.Authentication;

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

import java.net.MalformedURLException;

public class AuthRequest implements IRequestor {


    @Override
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {

        if (request.URI.isEmpty()){
            throw new GeneralException(500,"URL VACIA");
        }
        try {

            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("user",request.User)
                    .header("password",request.Password).asJson();
            if(response.getStatus()==401 || response.getStatus()==500 || response.getStatus()==400){
                throw new AuthException(401,"CREDENCIALES INVALIDAS");
            }
            JSONObject parser = new JSONObject(response.getBody().toString());
                JSONObject data = parser.getJSONObject("data");
            return new SuccessAuthResponse(response.getStatus(),parser.getString("status"),data.getString("token"),true);




        } catch (UnirestException e) {

            throw new GeneralException(500,"SERVIDOR INACTIVO");
        }
        catch(JSONException e){
            throw new GeneralException(500,e.getMessage());
        }


    }

}
