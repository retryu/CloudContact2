package com.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.db.model.Contact;
import com.http.response.CommonResponse;

/**
 * @author chenyuruan 联系人请求类
 */
public class ContactApi extends CommonApi {

	private static final String URL = "v1/contact";

	public static CommonResponse backUpCpntacts(List<Contact> contacts) {
		CommonResponse response = null;
		JSONObject json = getContactsJson(contacts);
		response = HttpUtil.post(MAIN_URL + URL, json);
		return response;
	}

	public static CommonResponse restoreContacts() {
		CommonResponse response=null;
		JSONObject params = new JSONObject();
		try {
			params.put("access_token", HttpUtil.Token);
			params.put("user_id", HttpUtil.UsreId);
			response=HttpUtil.get(MAIN_URL+URL, params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static List<Contact> toContacts(String jsonContacts) {
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			JSONArray jsonArray = new JSONArray(jsonContacts);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				Contact contact = toContact(json);
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public static Contact toContact(JSONObject json) {
		Contact contact = new Contact();
		try {
			contact.setCellphone1(json.getString("phone_one"));
			contact.setCellphone2(json.getString("phone_two"));
			contact.setTodoOne(json.getString("todo_one"));
			contact.setTodoTwo(json.getString("todo_two"));
			contact.setTodoThree(json.getString("todo_three"));
			contact.setName(json.getString("contact_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}

	public static JSONObject getContactsJson(List<Contact> contacts) {

		JSONObject json = new JSONObject();
		List<JSONObject> contactsJson = new ArrayList<JSONObject>();
		for (int i = 0; i < contacts.size(); i++) {
			JSONObject ContactJson = getContactJson(contacts.get(i));
			contactsJson.add(ContactJson);
		}
		JSONArray jaContacts = new JSONArray(contactsJson);
		try {
			json.put("contactList", jaContacts);
			json.put("access_token", HttpUtil.Token);
			json.put("user_id", HttpUtil.UsreId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static JSONObject getContactJson(Contact contact) {
		JSONObject contactJson = new JSONObject();
		try {
			contactJson.put("contact_name", contact.getName());
			contactJson.put("phone_one", contact.getCellphone1());
			contactJson.put("phone_two", contact.getCellphone2());
			contactJson.put("todo_one", contact.getTodoOne());
			contactJson.put("todo_two", contact.getEmail());
			contactJson.put("todo_three", contact.getTodoThree());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return contactJson;
	}

}
