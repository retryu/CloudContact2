package com.http;

import org.json.JSONObject;

import com.db.model.User;
import com.http.response.CommonResponse;

public class RegisterApi extends CommonApi {

	private static String url = "v1/user/signup";

	public static JSONObject getJson(User loginUser) {
		JSONObject jsonUser = new JSONObject();
		try {
			jsonUser.put("email", loginUser.getEmail());
			jsonUser.put("password", loginUser.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonUser;
	}

	public static User toUser(String userJson, User u) {
		try {
			JSONObject jsonUser = new JSONObject(userJson);
			String accessToken = jsonUser.getString("access_token");
			String user_id = jsonUser.getString("user_id");
			System.err.print("user"+u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public static CommonResponse register(User user) {
		JSONObject userJson = getJson(user);
		CommonResponse commonResponse = HttpUtil.post(MAIN_URL + url, userJson);
		return commonResponse;
	}

}
