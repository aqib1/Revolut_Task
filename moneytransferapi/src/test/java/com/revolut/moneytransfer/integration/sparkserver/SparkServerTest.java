package com.revolut.moneytransfer.integration.sparkserver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.revolut.moneytransfer.integration.sparkserver.rules.SparkServerRules;
import com.revolut.moneytransfer.sparkapprule.SparkServerRule;
import com.revolut.moneytransfer.utils.Helper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class SparkServerTest {

	@Rule
	public final SparkServerRule SPARK_SERVER = new SparkServerRule(http -> {
		SparkServerRules.sparkServerRules(http);
	});

	@Before
	public void before() throws InterruptedException {
		RestAssured.baseURI = Helper.BASE_URL;
	}

	@Test
	public void checkHealth() {
		RestAssured.given().contentType(ContentType.JSON).request(Method.GET, Helper.GET_HEALTH)
				.then().statusCode(200);
	}

}
