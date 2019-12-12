package com.revolut.moneytransfer.utils;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dto.UserRequestDto;
import com.revolut.moneytransfer.exception.BadRequestParamsException;
import com.revolut.moneytransfer.exception.InvalidRequestException;

/**
 * <p>
 * Helper class for project
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 * 
 */
public class Helper {

	public static final Gson GSON = new Gson();
	/**************** User API ***********/
	private static final String USER_API = "/user";
	public static final String GET_USERS = USER_API + "/all";
	public static final String GET_USER_BY_ID = USER_API + "/:id";
	public static final String POST_USER_CREATE = USER_API + "/";
	public static final String DELETE_USER_BY_ID = USER_API + "/:id";
	public static final String PUT_USER_UPDATE = USER_API + "/";
	public static final String OPTION_USER_EXIST = USER_API + "/:id";

	/******************* Account API ***********************/
	private static final String ACCOUNT_API = "/account";
	public static final String GET_ACCOUNTS = ACCOUNT_API + "/all";
	public static final String GET_ACCOUNT_BY_ID = ACCOUNT_API + "/:id";

	/************ API properties ***********/
	public static final String RESPONSE_TYPE_JSON = "application/json";
	public static final int DEFAULT_SPARK_PORT = 4567;
	public static final int PORT_8080 = 8080;

	/************* User table constant ************/
	public static final String ID_COL = "id";
	public static final String FIRST_NAME_COL = "firstName";
	public static final String LAST_NAME_COL = "lastName";
	public static final String EMAIL_COL = "email";
	public static final String CNIC_COL = "CNIC";
	public static final String ADDRESS_COL = "address";
	public static final String CONTACT_NUMB_COL = "contactNumber";

	/**
	 * @param str
	 * @return
	 */
	public static final boolean isNullOrEmptyString(String str) {
		return Objects.isNull(str) || str.isEmpty();
	}

	private Helper() {

	}

	/**
	 * @param req
	 */
	public static void validateUserForCreation(UserRequestDto req) {
		commonValidateForRequest(req);
		validateUserForUpdation(req);
		if (isNullOrEmptyString(req.getUser().getContactNumber()))
			throw new BadRequestParamsException("Request does not contain USER[contactNumber]");
		if (isNullOrEmptyString(req.getUser().getLastName()))
			throw new BadRequestParamsException("Request does not contain USER[lastName]");
	}

	/**
	 * @param req
	 */
	public static void validateUserForUpdation(UserRequestDto req) {
		commonValidateForRequest(req);
		if (isNullOrEmptyString(req.getUser().getCNIC()))
			throw new BadRequestParamsException("Request does not contain USER[CNIC]");
		if (isNullOrEmptyString(req.getUser().getEmail()))
			throw new BadRequestParamsException("Request does not contain USER[email]");
		if (isNullOrEmptyString(req.getUser().getFirstName()))
			throw new BadRequestParamsException("Request does not contain USER[firstName]");
	}

	private static void commonValidateForRequest(UserRequestDto req) {
		if (Objects.isNull(req) || Objects.isNull(req.getUser()))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(req.getUser().getId()))
			throw new BadRequestParamsException("Request does not contain USER[id]");
	}

	public static String getJson(Object obj) {
		return GSON.toJson(obj);
	}
}
