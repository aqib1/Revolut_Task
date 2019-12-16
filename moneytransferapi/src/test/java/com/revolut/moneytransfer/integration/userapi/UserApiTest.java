package com.revolut.moneytransfer.integration.userapi;

import static com.revolut.moneytransfer.utils.Helper.GET_USERS;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.revolut.moneytransfer.dagger2.components.DaggerUserServiceComponent;
import com.revolut.moneytransfer.dagger2.components.UserServiceComponent;
import com.revolut.moneytransfer.integration.userapi.rules.UserApiRules;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.sparkapprule.SparkServerRule;
import com.revolut.moneytransfer.utils.Helper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

/**
 * @author AQIB JAVED
 * @since 12/16/2019
 * @version 1.0
 */
public class UserApiTest {

	// Getting dagger2 component for user service
	private UserServiceComponent userServiceComponent = DaggerUserServiceComponent.create();
	// Getting service from component
	private UserService userService = userServiceComponent.buildUserServiceImpl();
	@Rule
	public final SparkServerRule SPARK_SERVER = new SparkServerRule(http -> {
		UserApiRules.userApiRules(http, userService);
	});

	@Before
	public void before() throws InterruptedException {
		RestAssured.baseURI = Helper.BASE_URL;
	}

	@Test
	public void testGetAllUser() {
		RestAssured.given().contentType(ContentType.JSON).request(Method.GET, GET_USERS).then()
				.statusCode(200);
	}

	@After
	public void after() {
	}
}
