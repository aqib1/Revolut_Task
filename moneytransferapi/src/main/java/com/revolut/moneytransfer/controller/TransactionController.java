package com.revolut.moneytransfer.controller;

import static com.revolut.moneytransfer.utils.Helper.POST_TRANSACTION;
import static spark.Spark.get;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dagger2.components.DaggerTransServiceComponent;
import com.revolut.moneytransfer.dagger2.components.TransServiceComponent;
import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.service.trans.TransService;
import com.revolut.moneytransfer.utils.Helper;

/**
 * <p>
 * Transaction controller, this class will define all routes for Transaction-API
 * </p>
 * 
 * @author AQIB JAVED
 * @since 12/13/2019
 * @version 1.0
 */
public class TransactionController {

	private TransServiceComponent transServiceComponent = DaggerTransServiceComponent.create();
	private TransService transService = transServiceComponent.buildTransServiceImpl();
	private volatile static TransactionController transactionController = null;

	private TransactionController() {

	}

	/**
	 * <p>
	 * This method is used to register transaction API
	 * </p>
	 * 
	 * @return {@link AccountController}
	 */
	public TransactionController registerTransactionAPI() {
		get(POST_TRANSACTION, (request, response) -> {
			return Helper.getJson(transService
					.transfer(new Gson().fromJson(request.body(), TransRequestDto.class)));
		});
		return this;
	}

	public static TransactionController getInstance() {
		if (Objects.isNull(transactionController))
			synchronized (TransactionController.class) {
				if (Objects.isNull(transactionController))
					transactionController = new TransactionController();
			}
		return transactionController;
	}
}
