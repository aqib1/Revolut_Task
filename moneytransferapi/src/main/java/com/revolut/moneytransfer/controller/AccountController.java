package com.revolut.moneytransfer.controller;

import static com.revolut.moneytransfer.utils.Helper.DELETE_ACCOUNT_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.GET_ACCOUNTS;
import static com.revolut.moneytransfer.utils.Helper.GET_ACCOUNT_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.OPTION_ACCOUNT_EXIST;
import static com.revolut.moneytransfer.utils.Helper.POST_ACCOUNT_CREATE;
import static com.revolut.moneytransfer.utils.Helper.POST_DEPOSIT;
import static com.revolut.moneytransfer.utils.Helper.POST_WITHDRAW;
import static com.revolut.moneytransfer.utils.Helper.PUT_ACCOUNT_UPDATE;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dagger2.components.AccountServiceComponent;
import com.revolut.moneytransfer.dagger2.components.DaggerAccountServiceComponent;
import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.service.account.AccountService;
import com.revolut.moneytransfer.utils.Helper;

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
	// Getting dagger2 component for account service
	private AccountServiceComponent accountServiceComponent = DaggerAccountServiceComponent
			.create();
	// Getting service from component
	private AccountService accountService = accountServiceComponent.buildAccountServiceImpl();
	private volatile static AccountController accountController = null;

	private AccountController() {

	}

	/**
	 * <p>
	 * This method is used to register get all-account API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerGetAllAccountAPI() {
		get(GET_ACCOUNTS, (request, response) -> {
			return Helper.getJson(accountService.getAll());
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register get account by id API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerGetAccountByIdAPI() {
		get(GET_ACCOUNT_BY_ID, (request, response) -> {
			return Helper.getJson(accountService.getById(request.params(":id")));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register post account creation API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerPostCreateAccountAPI() {
		post(POST_ACCOUNT_CREATE, (request, response) -> {
			return Helper.getJson(accountService
					.create(new Gson().fromJson(request.body(), AccountRequestDto.class)));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register delete account creation API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerDeleteAccountAPI() {
		delete(DELETE_ACCOUNT_BY_ID, (request, response) -> {
			return Helper.getJson(accountService.delete(request.params(":id")));
		});

		return this;
	}

	/**
	 * <p>
	 * This method is used to register update account creation API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerUpdateAccountAPI() {
		put(PUT_ACCOUNT_UPDATE, (request, response) -> {
			return Helper.getJson(accountService
					.update(new Gson().fromJson(request.body(), AccountRequestDto.class)));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register check account exist API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerCheckAccountAPI() {
		options(OPTION_ACCOUNT_EXIST, (request, response) -> {
			return Helper.getJson(accountService.exists(request.params(":id")));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register check withdraw API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerWithdrawAPI() {
		post(POST_WITHDRAW, (request, response) -> {
			return Helper.getJson(accountService
					.withdraw(new Gson().fromJson(request.body(), WithdrawRequestDto.class)));
		});
		return this;
	}

	/**
	 * <p>
	 * This method is used to register check deposit API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public AccountController registerDepositAPI() {
		post(POST_DEPOSIT, (request, response) -> {
			return Helper.getJson(accountService
					.deposit(new Gson().fromJson(request.body(), DepositRequest.class)));
		});
		return this;
	}

	// Double check locking
	public static AccountController getInstance() {
		if (Objects.isNull(accountController))
			synchronized (AccountController.class) {
				if (Objects.isNull(accountController))
					accountController = new AccountController();
			}
		return accountController;
	}
}
