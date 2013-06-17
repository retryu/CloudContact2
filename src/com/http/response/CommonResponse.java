package com.http.response;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-6 ÏÂÎç02:48:01 file declare:
 */
public class CommonResponse {

	private int stateCode;
	private String response;

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "CommonResponse [stateCode=" + stateCode + ", response="
				+ response + "]";
	}

}
