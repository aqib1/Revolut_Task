package com.revolut.moneytransfer.utils;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.UserRequestDto;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
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
	public static final String POST_ACCOUNT_CREATE = ACCOUNT_API + "/";
	public static final String DELETE_ACCOUNT_BY_ID = ACCOUNT_API + "/:id";
	public static final String PUT_ACCOUNT_UPDATE = ACCOUNT_API + "/";
	public static final String OPTION_ACCOUNT_EXIST = ACCOUNT_API + "/:id";
	public static final String POST_WITHDRAW = ACCOUNT_API + "/withdraw/";
	public static final String POST_DEPOSIT = ACCOUNT_API + "/deposit/";

	/****************** Transaction API *******************/
	public static final String POST_TRANSACTION = "/trans/";

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
	public static void validateUser(UserRequestDto req) {
		if (Objects.isNull(req) || Objects.isNull(req.getUser()))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(req.getUser().getId()))
			throw new BadRequestParamsException("Request does not contain USER[id]");
		if (isNullOrEmptyString(req.getUser().getCNIC()))
			throw new BadRequestParamsException("Request does not contain USER[CNIC]");
		if (isNullOrEmptyString(req.getUser().getEmail()))
			throw new BadRequestParamsException("Request does not contain USER[email]");
		if (isNullOrEmptyString(req.getUser().getFirstName()))
			throw new BadRequestParamsException("Request does not contain USER[firstName]");
		if (isNullOrEmptyString(req.getUser().getContactNumber()))
			throw new BadRequestParamsException("Request does not contain USER[contactNumber]");
		if (isNullOrEmptyString(req.getUser().getLastName()))
			throw new BadRequestParamsException("Request does not contain USER[lastName]");
	}

	/**
	 * @param req
	 */
	public static void validateAccount(AccountRequestDto req) {
		if (Objects.isNull(req) || Objects.isNull(req.getAccount()))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(req.getAccount().getId()))
			throw new BadRequestParamsException("Request does not contain Account[id]");
		if (isNullOrEmptyString(req.getAccount().getAccountTitle()))
			throw new BadRequestParamsException("Request does not contain Account[accountTitle]");
		if (Objects.isNull(req.getAccount().getBalance()))
			throw new BadRequestParamsException("Request does not contain Account[balance]");
		if (Objects.isNull(req.getAccount().getUserId()))
			throw new BadRequestParamsException("Request does not contain Account[userId]");
		if (Objects.isNull(req.getAccount().getCurrency()))
			throw new BadRequestParamsException("Request does not contain Account[currency]");
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj) {
		return GSON.toJson(obj);
	}

	/**
	 * @param withdrawRequestDto
	 */
	public static void validateWithDrawRequestDto(WithdrawRequestDto withdrawRequestDto) {
		if (Objects.isNull(withdrawRequestDto))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(withdrawRequestDto.getAccountId()))
			throw new BadRequestParamsException("Request does not contain Account[id]");
		if (Objects.isNull(withdrawRequestDto.getAmount()))
			throw new BadRequestParamsException("Request does not contain Account[amount]");
	}

	/**
	 * @param deposit
	 */
	public static void validateDepositRequest(DepositRequest deposit) {
		if (Objects.isNull(deposit))
			throw new InvalidRequestException("Request cannot be null");
		if (isNullOrEmptyString(deposit.getAccountId()))
			throw new BadRequestParamsException("Request does not contain Account[id]");
		if (Objects.isNull(deposit.getAmount()))
			throw new BadRequestParamsException("Request does not contain Account[amount]");

	}
}
