package Utils.Helpers;

import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.json.JSONArray;
import org.json.JSONObject;

import Exceptions.GeneralException;

public class RequestHelper {
	public static void setTimeOut(Builder build, int time) {
		build.setSocketTimeout(time * 50).setConnectTimeout(time * 40)
				.setConnectionRequestTimeout(time * 40);
	}

	public static JSONArray buildJSONFromMap(Map<String, String> map) throws GeneralException {
		JSONArray mJSONArray = new JSONArray();
		if (map.isEmpty()) {
			throw new GeneralException(400, "No existen uuid --> " + map.toString());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			JSONObject obj = new JSONObject();
			obj.put("uuid", entry.getKey());
			obj.put("action", entry.getValue());
			mJSONArray.put(obj);
		}
		return mJSONArray;
	}

	public static void setProxy(Builder build, String host, int port) throws GeneralException {
		if(host != null){
			try {
				HttpHost proxy = new HttpHost(host, port);
				build.setProxy(proxy);
			} catch (Exception e) {
				throw new GeneralException(400, e.getMessage());
			}
		}
	}

}
