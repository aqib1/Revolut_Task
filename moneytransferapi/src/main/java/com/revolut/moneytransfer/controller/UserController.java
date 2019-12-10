package com.revolut.moneytransfer.controller;

import static com.revolut.moneytransfer.utils.Helper.GET_USERS;
import static spark.Spark.get;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dagger2.components.DaggerUserServiceComponent;
import com.revolut.moneytransfer.dagger2.components.UserServiceComponent;
import com.revolut.moneytransfer.dto.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;

/**
 * @author AQIB JAVED
 * @since 12/9/2019
 * @version 1.0
 *          <p>
 *          User controller, this class will define all routes for User-API
 *          </p>
 */
public class UserController {
	private UserServiceComponent userServiceComponent = DaggerUserServiceComponent.create();
	private static UserController userController = null;
	
	private UserController() {

	}

	/**
	 * <p>
	 * This method is used to register get all-user api
	 * </p>
	 * 
	 * @return {@link UserController}
	 */
	public UserController registerGetAllUserAPI() {
		get(GET_USERS, (request, response) -> {
			return new Gson().toJson(ResponseDto.builder().withStatusType(StatusType.SUCCESS).build());
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
