package com.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.db.model.Friend;
import com.http.response.CommonResponse;
import com.model.FriendItem;
import com.model.FriendState;
import com.util.PingYinUtil;

public class FriendApi extends CommonApi {
	private final static String url = "v1/user";

	private final static String REQUEST_RIREND_URL = "v1/friend/request";

	private final static String GET_FRIEND_URL = "v1/friend";

	public static CommonResponse getFriend(String clientId) {
		CommonResponse comResponse = null;
		JSONObject jsonParams = new JSONObject();
		try {
			jsonParams.put("access_token", HttpUtil.Token);
			jsonParams.put("user_id", HttpUtil.UsreId);
			jsonParams.put("client_email", clientId);
			comResponse = HttpUtil.get(MAIN_URL + url, jsonParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comResponse;
	}

	public static List<Friend> getFriends() {
		List<Friend> friends = new ArrayList<Friend>();
		CommonResponse comResponse = null;
		JSONObject jsonParams = new JSONObject();

		try {
			jsonParams.put("access_token", HttpUtil.getToken());
			jsonParams.put("user_id", HttpUtil.getUsreId());
			System.err.println("params:" + jsonParams);
			comResponse = HttpUtil.post(MAIN_URL + GET_FRIEND_URL, jsonParams);
			friends = toFrineds(comResponse.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friends;
	}

	public static List<FriendItem> getFriendItems() {
		List<Friend> friends = getFriends();
		ArrayList<FriendItem> friendItems = new ArrayList<FriendItem>();
		for (int i = 0; i < friends.size(); i++) {
			FriendItem friendItem = new FriendItem();
			Friend friend = friends.get(i);
			friendItem.setFriend(friend);
			friendItem.setType(1);
			friendItem.setNickName(PingYinUtil.getPingYin(friend.getName()));
			friendItems.add(friendItem);
		}
		return friendItems;
	}

	public static CommonResponse addFriend(String firendId, String msg) {
		CommonResponse comResponse = null;
		JSONObject jsonParams = new JSONObject();
		try {
			jsonParams.put("access_token", HttpUtil.Token);
			jsonParams.put("user_id", HttpUtil.UsreId);
			jsonParams.put("friend_user_id", firendId);
			jsonParams.put("capthca ", msg);
			System.err.println(jsonParams);
			comResponse = HttpUtil.post(MAIN_URL + REQUEST_RIREND_URL,
					jsonParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comResponse;
	}

	public static List<Friend> getFriend() {
		List<Friend> friends = new ArrayList<Friend>();

		return friends;
	}

	public static List<Friend> toFrineds(String jsonStr) {
		List<Friend> friends = new ArrayList<Friend>();
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			JSONArray jsonArray = new JSONArray(jsonObject.getString("friends"));
			for (int i = 0; i < jsonArray.length(); i++) {
				Friend friend = toFriend(jsonArray.getJSONObject(i));
				friends.add(friend);
				System.out.println(friend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friends;
	}

	public static Friend toFriend(JSONObject jsonObject) {
		Friend friend = new Friend();
		try {
			friend.setCellPhone1(jsonObject.getString("phone_one"));
			friend.setCellPhone2(jsonObject.getString("phone_two"));
			friend.setName(jsonObject.getString("name"));
			friend.setId(jsonObject.getInt("user_id"));
			friend.setEamil(jsonObject.getString("todo_one"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friend;
	}

	public static FriendState checkFriend(String json) {
		FriendState friendState = new FriendState();
		try {
			JSONObject j = new JSONObject(json);
			if (j.isNull("name") != true) {
				friendState.setName(j.getString("name"));
			}
			if (j.isNull("isAlreadyFriend") != true) {
				friendState.setIsAlreadyFriend(j.getInt("isAlreadyFriend"));
			}
			if (j.isNull("user_id") != true) {
				friendState.setUserId(j.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(friendState);
		return friendState;
	}

}
