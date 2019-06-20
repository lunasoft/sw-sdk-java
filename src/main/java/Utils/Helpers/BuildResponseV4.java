package Utils.Helpers;

import org.json.JSONObject;

import Utils.Responses.IResponse;
import Utils.Responses.Stamp.SuccessV3Response;
import Utils.Responses.Stamp.SuccessV4Response;

public class BuildResponseV4 extends ResponseStamp {
	public IResponse getResponse() { 
		if(!response.trim().isEmpty() && status < 500) {
			JSONObject body = new JSONObject(response);
			if(status == 200) {
				JSONObject data = body.getJSONObject("data");
				return new SuccessV4Response(status, body.getString("status"),
						data.getString("cfdi"), data.getString("cadenaOriginalSAT"),
						data.getString("noCertificadoSAT"), data.getString("noCertificadoCFDI"),
						data.getString("uuid"), data.getString("selloSAT"),
						data.getString("selloCFDI"), data.getString("fechaTimbrado"),
						data.getString("qrCode"), "OK", "OK");
			}
			else {
				String messageDetail = "";
                if (!body.isNull("messageDetail")){
                    messageDetail = body.getString("messageDetail");
                }
                if(body.getString("message").equals("307. El comprobante contiene un timbre previo.")) {
					if(!body.isNull("data")) {
						JSONObject data = body.getJSONObject("data");
						return new SuccessV4Response(status, body.getString("status"),
								data.getString("cfdi"), data.getString("cadenaOriginalSAT"),
								data.getString("noCertificadoSAT"), data.getString("noCertificadoCFDI"),
								data.getString("uuid"), data.getString("selloSAT"),
								data.getString("selloCFDI"), data.getString("fechaTimbrado"),
								data.getString("qrCode"), body.getString("message"), messageDetail);
					}
				}
				return new SuccessV4Response(status, body.getString("status"), "", "", "", "", "", "", "", "", "", body.getString("message"), messageDetail);
			}
		}
		else {
			return new SuccessV4Response(status, "error", "", "", "", "", "", "", "", "", "", "Error con código "+status+": "+reason.getReasonPhrase(), response);
		}
	}
}
