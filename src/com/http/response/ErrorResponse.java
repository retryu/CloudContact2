package com.http.response;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time£º2013-6-6 ÏÂÎç03:23:24 file declare:
 */
public class ErrorResponse {
	private int error_type;
	private String message;

	public int getError_type() {
		return error_type;
	}

	public void setError_type(int error_type) {
		this.error_type = error_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [error_type=" + error_type + ", message="
				+ message + "]";
	}

}
