package Utils.Helpers;

import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import Exceptions.GeneralException;

public class RequestHelper {
	public static void setTimeOut(HttpPost request, int time) {
		RequestConfig requestConfig = RequestConfig.custom()
									  .setSocketTimeout(time*50)
									  .setConnectTimeout(time*40)
									  .setConnectionRequestTimeout(time*40)
									  .build();
		request.setConfig(requestConfig);
	}
	public static JSONArray buildJSONFromMap(Map<String, String> map) throws GeneralException {
		JSONArray mJSONArray = new JSONArray();
		if(map.isEmpty()) {
			throw new GeneralException(400, "No existen uuid --> "+map.toString());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			JSONObject obj = new JSONObject();
			obj.put("uuid", entry.getKey());
			obj.put("action", entry.getValue());
			mJSONArray.put(obj);
		}
		return mJSONArray;
	}
	public static void setTimeOut(HttpGet request, int time) {
		RequestConfig requestConfig = RequestConfig.custom()
				  .setSocketTimeout(time*7)
				  .setConnectTimeout(time*5)
				  .setConnectionRequestTimeout(time*5)
				  .build();
		request.setConfig(requestConfig);
	}
}
