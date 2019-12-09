package com.revolut.moneytransfer.utils;

public class Helper {

	private final static String USER_API = "/user";
	public final static String GET_USERS = USER_API + "/all";
	public final static String GET_USER_BY_ID = USER_API + "/:id";

	public final static String RESPONSE_TYPE_JSON = "application/json";
	public final static int DEFAULT_SPARK_PORT = 4567;
	public final static int PORT_8080 = 8080;

	private Helper() {

	}

}
