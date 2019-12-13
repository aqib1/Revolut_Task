package com.revolut.moneytransfer.controller;

import java.util.Objects;

public class TransactionController {

	private volatile static TransactionController transactionController = null;

	private TransactionController() {

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
