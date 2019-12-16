package com.revolut.moneytransfer.integration.userapi.rules;

import static com.revolut.moneytransfer.utils.Helper.DELETE_USER_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.GET_USERS;
import static com.revolut.moneytransfer.utils.Helper.GET_USER_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.OPTION_USER_EXIST;
import static com.revolut.moneytransfer.utils.Helper.POST_USER_CREATE;
import static com.revolut.moneytransfer.utils.Helper.PUT_USER_UPDATE;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dto.requests.UserRequestDto;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.utils.Helper;

import spark.Service;

/**
 * @author AQIB JAVED
 * @since 12/16/2019
 * @version 1.0
 */
public class UserApiRules {

	public static void userApiRules(Service http, UserService userService) {
		http.port(Helper.PORT_8080);
		http.get(GET_USERS, (request, response) -> {
			return Helper.getJson(userService.getAll());
		});
		http.get(GET_USER_BY_ID, (request, response) -> {
			return Helper.getJson(userService.getById(request.params(":id")));
		});
		http.post(POST_USER_CREATE, (request, response) -> {
			return Helper.getJson(
					userService.create(new Gson().fromJson(request.body(), UserRequestDto.class)));
		});
		http.delete(DELETE_USER_BY_ID, (request, response) -> {
			return Helper.getJson(userService.delete(request.params(":id")));
		});
		http.put(PUT_USER_UPDATE, (request, response) -> {
			return Helper.getJson(
					userService.update(new Gson().fromJson(request.body(), UserRequestDto.class)));
		});
		http.options(OPTION_USER_EXIST, (request, response) -> {
			return Helper.getJson(userService.exists(request.params(":id")));
		});
	}

	private UserApiRules() {

	}
}
