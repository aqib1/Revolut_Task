package com.revolut.moneytransfer.integration.accountapi.rules;

import static com.revolut.moneytransfer.utils.Helper.DELETE_ACCOUNT_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.GET_ACCOUNTS;
import static com.revolut.moneytransfer.utils.Helper.GET_ACCOUNT_BALANCE;
import static com.revolut.moneytransfer.utils.Helper.GET_ACCOUNT_BY_ID;
import static com.revolut.moneytransfer.utils.Helper.OPTION_ACCOUNT_EXIST;
import static com.revolut.moneytransfer.utils.Helper.POST_ACCOUNT_CREATE;
import static com.revolut.moneytransfer.utils.Helper.POST_DEPOSIT;
import static com.revolut.moneytransfer.utils.Helper.POST_WITHDRAW;
import static com.revolut.moneytransfer.utils.Helper.PUT_ACCOUNT_UPDATE;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.service.account.AccountService;
import com.revolut.moneytransfer.utils.Helper;

import spark.Service;

public class AccountApiRules {

	private AccountApiRules() {

	}

	public static void userApiRules(Service http, AccountService accountService) {
		http.port(Helper.PORT_8080);
		http.before((request, response) -> {
			response.type(Helper.RESPONSE_TYPE_JSON);
		});
		http.get(GET_ACCOUNTS, (request, response) -> {
			return Helper.getJson(accountService.getAll());
		});
		http.get(GET_ACCOUNT_BY_ID, (request, response) -> {
			return Helper.getJson(accountService.getById(request.params(":id")));
		});
		http.post(POST_ACCOUNT_CREATE, (request, response) -> {
			return Helper.getJson(accountService
					.create(new Gson().fromJson(request.body(), AccountRequestDto.class)));
		});
		http.delete(DELETE_ACCOUNT_BY_ID, (request, response) -> {
			return Helper.getJson(accountService.delete(request.params(":id")));
		});
		http.put(PUT_ACCOUNT_UPDATE, (request, response) -> {
			return Helper.getJson(accountService
					.update(new Gson().fromJson(request.body(), AccountRequestDto.class)));
		});
		http.options(OPTION_ACCOUNT_EXIST, (request, response) -> {
			return Helper.getJson(accountService.exists(request.params(":id")));
		});
		http.post(POST_WITHDRAW, (request, response) -> {
			return Helper.getJson(accountService
					.withdraw(new Gson().fromJson(request.body(), WithdrawRequestDto.class)));
		});
		http.post(POST_DEPOSIT, (request, response) -> {
			return Helper.getJson(accountService
					.deposit(new Gson().fromJson(request.body(), DepositRequest.class)));
		});
		http.get(GET_ACCOUNT_BALANCE, (request, response) -> {
			return Helper.getJson(accountService.balance(request.params(":id")));
		});
	}
}
