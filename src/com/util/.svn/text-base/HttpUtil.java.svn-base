package com.util;

import java.io.UnsupportedEncodingException;
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
import org.json.JSONObject;

public class HttpUtil {
	private static String UsreId;

	private static String Token;

	static HttpClient client = new DefaultHttpClient();

	public static String get(String url, List<NameValuePair> params) {
		String body = null;
		try {
			HttpPost post = new HttpPost();
			String paramsStr = "";
			if (params != null) {
				paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(
						params));

			}
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

	public static String post(String url, JSONObject params) {
		String response = null;
		try {
			HttpPost post = new HttpPost(url);
			if (params != null) {
				StringEntity strEntity = new StringEntity(params.toString());
				post.setEntity(strEntity);
			}
			HttpResponse httpResponse = client.execute(post);
			response=EntityUtils.toString(httpResponse.getEntity());
			System.out.print("response:"+response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
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

}
