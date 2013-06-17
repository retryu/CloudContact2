package com.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.http.response.ErrorResponse;

public class CommonApi {
	
	//http://172.16.3.43:9999/CloudContact/
	//http://172.16.15.31:8080/CloudContact/
	
	public final static String MAIN_URL = "http://172.16.3.43:9999/CloudContact/";
	public final static String USER_ID = "123";
	public final static String ACCESS_TOKEN = "xx";

	public static ErrorResponse toErrorResponse(String str) {
		JSONObject errorJson;
		ErrorResponse errorResponse = new ErrorResponse();
		try {
			errorJson = new JSONObject(str);
			String message = (String) errorJson.get("message");
			int errorType = Integer.parseInt((String) errorJson
					.get("error_type"));
			errorResponse.setError_type(errorType);
			errorResponse.setMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("" + errorResponse);
		return errorResponse;
	}
}
