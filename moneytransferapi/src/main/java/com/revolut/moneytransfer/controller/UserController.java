package com.revolut.moneytransfer.controller;

import java.util.Objects;

public class UserController {
	private static UserController userController = null;

	private UserController() {

	}

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
