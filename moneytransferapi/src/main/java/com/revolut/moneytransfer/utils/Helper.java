package com.revolut.moneytransfer.utils;

import java.util.Objects;

import com.revolut.moneytransfer.dto.RequestDto;
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

	private final static String USER_API = "/user";
	public final static String GET_USERS = USER_API + "/all";
	public final static String GET_USER_BY_ID = USER_API + "/:id";
	public final static String POST_USER_CREATE = USER_API + "/";

	public final static String RESPONSE_TYPE_JSON = "application/json";
	public final static int DEFAULT_SPARK_PORT = 4567;
	public final static int PORT_8080 = 8080;

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
	public static void validateUserForCreation(RequestDto req) {
		if (Objects.isNull(req) || Objects.isNull(req.getUser()))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(req.getUser().getId()))
			throw new BadRequestParamsException("Request does not containe USER[id]");
		if (isNullOrEmptyString(req.getUser().getCNIC()))
			throw new BadRequestParamsException("Request does not cotain USER[CNIC]");
		if (isNullOrEmptyString(req.getUser().getContactNumber()))
			throw new BadRequestParamsException("Request does not cotain USER[contactNumber]");
		if (isNullOrEmptyString(req.getUser().getEmail()))
			throw new BadRequestParamsException("Request does not cotain USER[email]");
		if (isNullOrEmptyString(req.getUser().getFirstName()))
			throw new BadRequestParamsException("Request does not cotain USER[firstName]");
		if (isNullOrEmptyString(req.getUser().getLastName()))
			throw new BadRequestParamsException("Request does not cotain USER[lastName]");
	}

	public static void validateUserForUpdation(RequestDto req) {
		
	}
}
