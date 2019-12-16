package com.revolut.moneytransfer.integration.sparkserver.rules;

import com.revolut.moneytransfer.utils.Helper;

import spark.Service;

public class SparkServerRules {

	private SparkServerRules() {
		
	}
	
	public static void sparkServerRules(Service http) {
		http.port(Helper.PORT_8080);
		http.get("/health", (request, response) -> "healthy");
	}
}
