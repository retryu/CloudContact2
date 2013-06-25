package com.http;

import org.json.JSONObject;

import com.db.model.User;
import com.http.response.CommonResponse;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-6 ÏÂÎç04:38:52 file declare:
 */
public class UserInfoApi extends CommonApi {

	private static String url = "/v1/user/contact";

	public static JSONObject getJson(User user) {
		JSONObject userJson = new JSONObject();
		try {
			userJson.put("access_token", user.getAccess_token());
			userJson.put("user_id", user.getUserId());
			userJson.put("name", user.getName());
			userJson.put("phone_one", user.getCellPhon1());
			userJson.put("phone_two", user.getCellPhone2());
			userJson.put("todo_one", user.getTodo_one());
			userJson.put("todo_two", user.getTodo_two());
			userJson.put("todo_three", user.getTodo_three());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userJson;
	}

	public static CommonResponse updateInfo(User user) {
		JSONObject userJson = getJson(user);
		CommonResponse response = HttpUtil.post(MAIN_URL + url, userJson);
		return response;
	}

}
