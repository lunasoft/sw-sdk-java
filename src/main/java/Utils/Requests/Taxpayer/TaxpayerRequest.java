package Utils.Requests.Taxpayer;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Exceptions.GeneralException;
import Utils.Helpers.RequestHelper;
import Utils.Requests.IRequest;
import Utils.Requests.Cancelation.CancelationOptionsRequest;
import Utils.Responses.IResponse;
import Utils.Responses.Cancelation.CancelationResponse;
import Utils.Responses.Taxplayer.TaxplayerResponse;
import Utils.Requests.Taxpayer.TaxpayerOptionsRequest;

public class TaxpayerRequest {

	public IResponse sendRequestrfc(IRequest request) throws ClientProtocolException, IOException, GeneralException {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(request.URI);
			RequestHelper.setTimeOut(request.options, 4000);
			RequestHelper.setProxy(request.options, request.proxyHost, request.proxyPort);
			httpget.setConfig(request.options.build());
			httpget.setHeader(new BasicHeader("Authorization", "bearer " + request.Token));
			CloseableHttpResponse responseB = client.execute(httpget);

			HttpEntity entity = responseB.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			int status = responseB.getStatusLine().getStatusCode();
			client.close();
			responseB.close();
			if (!responseString.isEmpty() && status < 500) {
				JSONObject body = new JSONObject(responseString);
				if (status == 200) {
					JSONObject data = body.getJSONObject("data");		
					return new TaxplayerResponse(status, body.getString("status"),data.getString("id"),
							data.getString("rfc"), 
                            data.getString("nombre_Contribuyente"),
							data.getString("situacion_del_contribuyente"),
							data.getString("numero_y_fecha_oficio_global_presuncion"),
							data.getString("publicacion_pagina_SAT_presuntos"),
							data.getString("publicacion_DOF_presuntos"),
							data.getString("publicacion_pagina_SAT_desvirtuados"),
							data.getString("numero_fecha_oficio_global_contribuyentes_que_desvirtuaron"),
							data.getString("publicacion_DOF_desvirtuados"),
							data.getString("numero_fecha_oficio_global_definitivos"),
							data.getString("publicacion_pagina_SAT_definitivos"),
                            data.getString("publicacion_DOF_definitivos"),
							data.getString("numero_fecha_oficio_global_sentencia_favorable"),
							data.getString("publicacion_pagina_SAT_sentencia_favorable"),
							data.getString("publicacion_DOF_sentencia_favorable"), "OK", "OK");
				} else {
					String messageDetail = "";
					if (!body.isNull("messageDetail")) {
						messageDetail = body.getString("messageDetail");
					}
					return new TaxplayerResponse(status, body.getString("status"), body.getString("message"),
							messageDetail);
				}
			} else {
				return new TaxplayerResponse(status, "error", responseB.getStatusLine().getReasonPhrase(),
						responseString);
			}
		} catch (JSONException e) {
			throw new GeneralException(500, e.getMessage());
		}
	}
}
