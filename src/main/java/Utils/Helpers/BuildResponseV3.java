package Utils.Helpers;

import org.json.JSONObject;

import Utils.Responses.IResponse;
import Utils.Responses.Stamp.SuccessV3Response;

public class BuildResponseV3 extends ResponseStamp {
	public IResponse getResponse() { 
		if(!response.trim().isEmpty()) {
			JSONObject body = new JSONObject(response);
			if(status == 200) {
				JSONObject data = body.getJSONObject("data");
				return new SuccessV3Response(status, body.getString("status"), data.getString("cfdi"),reason.getReasonPhrase(), reason.getReasonPhrase());
			}
			else {
				String messageDetail = "";
                if (!body.isNull("messageDetail")){
                    messageDetail = body.getString("messageDetail");
                }
				return new SuccessV3Response(status, body.getString("status"), "",body.getString("message"), messageDetail);
			}
		}
		else {
			return new SuccessV3Response(status, "error", "","Error con código "+status+": "+reason.getReasonPhrase(), reason.getProtocolVersion().getProtocol());
		}
	}
}
