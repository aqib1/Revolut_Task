package com.revolut.moneytransfer.controller;

import static com.revolut.moneytransfer.utils.Helper.DELETE_USER_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.GET_USERS;
import static com.revolut.moneytransfer.utils.Helper.GET_USER_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.POST_USER_CREATE;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dagger2.components.DaggerUserServiceComponent;
import com.revolut.moneytransfer.dagger2.components.UserServiceComponent;
import com.revolut.moneytransfer.dto.RequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.utils.Helper;

/**
 * <p>
 * User controller, this class will define all routes for User-API
 * </p>
 * 
 * @author AQIB JAVED
 * @since 12/9/2019
 * @version 1.0
 */
public class UserController {
	// Getting dagger2 component for user service
	private UserServiceComponent userServiceComponent = DaggerUserServiceComponent.create();
	// Getting service from component
	private UserService userService = userServiceComponent.buildUserServiceImpl();
	private static UserController userController = null;

	private UserController() {

	}

	/**
	 * <p>
	 * This method is used to register get all-user API
	 * </p>
	 * 
	 * @return {@link UserController}
	 */
	public UserController registerGetAllUserAPI() {
		get(GET_USERS, (request, response) -> {
			return new Gson().toJson(ResponseDto.builder().withStatusType(StatusType.SUCCESS)
					.withMessage("User detailed recieved successfully")
					.withData(new Gson().toJsonTree(userService.getAll())).build());
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register get user by id API
	 * </p>
	 * 
	 * @return {@link UserController}
	 */
	public UserController registerGetUserbByIdAPI() {
		get(GET_USER_BY_ID, (request, response) -> {
			return Helper.getJson(userService.getById(request.params(":id")));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to delete user against given id
	 * </p>
	 * 
	 * @return {@link UserController}
	 */
	public UserController registerPostCreateUser() {
		post(POST_USER_CREATE, (request, response) -> {
			return Helper.getJson(userService.create(new Gson().fromJson(request.body(), RequestDto.class)));
		});
		return this;
	}

	public UserController registerDeleteUser() {
		delete(DELETE_USER_BY_ID, (request, response) -> {
			return Helper.getJson(userService.delete(request.params(":id")));
		});

		return this;
	}

	// Double check locking singleton pattern
	public static UserController getInstance() {
		if (Objects.isNull(userController)) {
			synchronized (UserController.class) {
				if (Objects.isNull(userController)) {
					userController = new UserController();
				}
			}
		}
		return userController;
	}
}
