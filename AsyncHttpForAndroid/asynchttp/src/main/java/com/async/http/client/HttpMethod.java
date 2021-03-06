package com.async.http.client;

public enum HttpMethod {

	/**
	 * get
	 */
	Get("GET"),
	/**
	 * get http header only
	 */
	Head("HEAD"),
	/**
	 * debug
	 */
	Trace("TRACE"),
	/**
	 * query
	 */
	Options("OPTIONS"),
	/**
	 * delete
	 */
	Delete("DELETE"),
	/**
	 * update
	 */
	Put("PUT"),
	/**
	 * add
	 */
	Post("POST"),
	/**
	 * incremental update
	 */
	Patch("PATCH");

	private String methodName;

	HttpMethod(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}
}
