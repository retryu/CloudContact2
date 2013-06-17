package com.http;

import org.json.JSONObject;

import com.db.model.User;
import com.http.response.CommonResponse;

public class LoginApi extends CommonApi {

	private static String url = "v1/user/signin";

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

	public static User toUser(String userJosn) {
		User user = new User();
		try {
			JSONObject jsonUser = new JSONObject(userJosn);
			if (!jsonUser.isNull("user_id")) {
				String user_id = jsonUser.getString("user_id");
				user.setUserId(user_id);
			}
			if (!jsonUser.isNull("access_token")) {
				String aceess_token = jsonUser.getString("access_token");
				user.setAccess_token(aceess_token);
			}
			if (!jsonUser.isNull("email")) {
				String email = jsonUser.getString("email");
				user.setEmail(email);
			}
			if (!jsonUser.isNull("name")) {
				String name = jsonUser.getString("name");
				user.setName(name);
			}
			if (!jsonUser.isNull("phone_one")) {
				String phone_one = jsonUser.getString("phone_one");
				user.setCellPhon1(phone_one);
			}
			if (!jsonUser.isNull("phone_two")) {
				String phone_two = jsonUser.getString("phone_two");
				user.setCellPhone2(phone_two);
			}
			if (!jsonUser.isNull("todo_one")) {
				String todo_one = jsonUser.getString("todo_one");
				user.setTodo_one(todo_one);
			}
			if (!jsonUser.isNull("todo_two")) {
				String todo_two = jsonUser.getString("todo_two");
				user.setTodo_two(todo_two);
			}
			if (!jsonUser.isNull("todo_three")) {
				String todo_threee = jsonUser.getString("todo_three");
				user.setTodo_three(todo_threee);
			}
			System.err.println(""+user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  user;
	}

	public static CommonResponse login(User loginUser) {
		CommonResponse commonResponse = null;
		JSONObject jsonUser = getJson(loginUser);
		commonResponse = HttpUtil.post(MAIN_URL + url, jsonUser);
		return commonResponse;
	}

}
