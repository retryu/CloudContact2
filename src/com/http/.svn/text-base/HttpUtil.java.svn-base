package com.http;

import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.db.model.User;
import com.http.response.CommonResponse;

public class HttpUtil {

	// response state is sucess
	public final static int CODE_SUCESS = 200;
	// response statie is fail
	public final static int CODE_FAIL = 400;
	public  final  static  int  CODE_NULL=204;

	public static String UsreId = "4";

	public static String Token = "KF0zTaKnA7";

	static HttpClient client = new DefaultHttpClient();

	public static CommonResponse get(String url, JSONObject params) {
		String body = null;
		CommonResponse comResponse = null;

		try {
			HttpGet httpGet = null;
			String paramsStr = "";
			if (params != null) {
				httpGet = new HttpGet(url + jsonToUrl(params));
				httpGet.addHeader("Content-Type", "text/html; charset=utf-8");
			}

			System.out.println("url:" + httpGet.getURI());
			HttpResponse httpResponse = client.execute(httpGet);
			comResponse = new CommonResponse();
			int state = httpResponse.getStatusLine().getStatusCode();
			comResponse.setStateCode(state);
			if(state!=CODE_NULL){
			String response = EntityUtils.toString(httpResponse.getEntity());
			comResponse.setResponse(response);
			HttpEntity httpEntity = httpResponse.getEntity();
			System.err.println("body:" + response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comResponse;
	}

	public static String jsonToUrl(JSONObject json) {
		String url = "?";
		Iterator<String> nameStr = json.keys();
		while (nameStr.hasNext()) {
			String p = nameStr.next().toString();
			System.out.print(p);
			try {
				url += p + "=" + json.getString(p) + "&";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		url = url.substring(0, url.length() - 1);
		return url;
	}

	public static CommonResponse post(String url, JSONObject params) {
		String response = null;
		CommonResponse comResponse = new CommonResponse();
		System.out.println("post url:" + url);
		System.out.println("params:"+params);
		try {
			HttpPost post = new HttpPost(url);
			if (params != null) {
				StringEntity strEntity = new StringEntity(params.toString());
				post.setEntity(strEntity);
			}
			HttpResponse httpResponse = client.execute(post);
			int stateCode = httpResponse.getStatusLine().getStatusCode();
			response = EntityUtils.toString(httpResponse.getEntity());
			comResponse.setStateCode(stateCode);
			comResponse.setResponse(response);
			System.err.println("" + comResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comResponse;
	}

	public static String request(String url, List<NameValuePair> params) {
		String body = null;
		try {
			HttpClient client = HttpUtil.getHttpClient();
			if (params != null) {
				String paramsStr = EntityUtils
						.toString(new UrlEncodedFormEntity(params));
				url += paramsStr;
			}
			System.out.println("request:" + url);

			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			body = EntityUtils.toString(response.getEntity());
			System.out.println("body:" + body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static String get(HttpClient httpClient, String url,
			List<NameValuePair> params) {
		String body = null;
		try {
			String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(
					params));

			HttpGet httpGet = new HttpGet(url + paramsStr);

			System.out.println("paramsStr:" + paramsStr);
			System.out.println("url:" + httpGet.getURI());
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			body = EntityUtils.toString(httpEntity);
			System.out.println("body:" + body);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	private static HttpClient createHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		HttpProtocolParams.setUseExpectContinue(params, true);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager(
				params, schReg);
		return new DefaultHttpClient(connMgr, params);
	}

	// 关闭连接管理器并释放资源
	public static void shutdownclient() {
		if (client != null && client.getConnectionManager() != null) {
			client.getConnectionManager().shutdown();
			client = null;
		}
	}

	// 对外提供client实例
	public static HttpClient getHttpClient() {
		if (client == null) {
			client = createHttpClient();
		}
		return client;
	}

	public static String getUsreId() {
		return UsreId;
	}

	public static void setUsreId(String usreId) {
		UsreId = usreId;
	}

	public static String getToken() {
		return Token;
	}

	public static void setToken(String token) {
		Token = token;
	}
	
	public  static  void  setUser(User  user)	{
		String  userId=user.getUserId();
		String  token=user.getAccess_token();
		setUsreId(userId);
		setToken(token);
	}
	
	
	
	
	

}
