package Utils.Helpers;

import org.json.JSONObject;

import Utils.Responses.IResponse;
import Utils.Responses.Stamp.SuccessV1Response;

public class BuildResponseV1 extends ResponseStamp {
	public IResponse getResponse() { 
		if(!response.trim().isEmpty()) {
			JSONObject body = new JSONObject(response);
			if(status == 200) {
				JSONObject data = body.getJSONObject("data");
				return new SuccessV1Response(status, body.getString("status"), data.getString("tfd"), "OK", "OK");
			}
			else {
				return new SuccessV1Response(status, body.getString("status"), "", body.getString("message"), body.getString("messageDetail"));
			}
		}
		else {
			return new SuccessV1Response(status, "error", "","Error con código "+status+": "+reason.getReasonPhrase(), reason.getProtocolVersion().getProtocol());			
		}
	}
}
