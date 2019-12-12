package com.revolut.moneytransfer.controller;

import java.util.Objects;

/**
 * <p>
 * Account controller, this class will define all routes for Account-API
 * </p>
 * 
 * @author AQIB JAVED
 * @since 12/12/2019
 * @version 1.0
 */
public class AccountController {

	private volatile static AccountController accountController = null;

	private AccountController() {

	}
	
	public AccountController registerGetAllAccountAPI() {
		
		return this;
	}

	public static AccountController getInstance() {
		if (Objects.isNull(accountController))
			synchronized (AccountController.class) {
				if (Objects.isNull(accountController))
					accountController = new AccountController();
			}
		return accountController;
	}
}
