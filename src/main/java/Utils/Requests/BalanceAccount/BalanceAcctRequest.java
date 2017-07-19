package Utils.Requests.BalanceAccount;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.BadResponse;
import Utils.Responses.BalanceAcctResponse;
import Utils.Responses.IResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

//@author: Lupita ALvarado

public class BalanceAcctRequest implements IRequestor {

    @Override
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException, GeneralException {
        try {

            HttpResponse<String> response = Unirest.get(request.URI)
                    .header("Authorization","bearer "+request.Token).asString();

            if(!response.getBody().equalsIgnoreCase("{}") && !response.getBody().equalsIgnoreCase("")) {
                JSONObject body = new JSONObject(response.getBody());
                
                if(response.getStatus()==200){
                    JSONObject data = body.getJSONObject("data");
                    return new BalanceAcctResponse(response.getStatus(),data.toString(),body.getString("status"));
                }
                else{
                    String messageDetail = null;

                    if (!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BadResponse(response.getStatus(),body.getString("status"),body.getString("message"),messageDetail);
                }
            }
            else{
                return new BadResponse(response.getStatus(),"error",response.getStatusText(),response.getStatusText());
            }

            
        } catch (UnirestException e) {

            throw  new GeneralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        }
        
    }
    
}
