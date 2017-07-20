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

        String messageDetail = "";
        try {

            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("user",request.User)
                    .header("password",request.Password).asJson();
            if(!response.getBody().toString().isEmpty()) {
                    JSONObject body = new JSONObject(response.getBody().toString());
                    if(!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }

                    if(response.getStatus()==200){
                        JSONObject data = body.getJSONObject("data");
                        return new SuccessAuthResponse(response.getStatus(),body.getString("status"),data.getString("token"),"OK","OK");
                    }
                    else{
                        return new SuccessAuthResponse(response.getStatus(),body.getString("status"),"",body.getString("message"),messageDetail);

                    }
            }
            else{
                return new SuccessAuthResponse(response.getStatus(),"error","",response.getStatusText(),response.getStatusText());

            }

        } catch (UnirestException e) {

            throw new GeneralException(500,"SERVIDOR INACTIVO");
        }
        catch(JSONException e){
            throw new GeneralException(500,e.getMessage());
        }


    }

}
