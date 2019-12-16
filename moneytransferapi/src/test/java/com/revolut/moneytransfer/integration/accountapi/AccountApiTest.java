package com.revolut.moneytransfer.integration.accountapi;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.revolut.moneytransfer.dagger2.components.AccountServiceComponent;
import com.revolut.moneytransfer.dagger2.components.DaggerAccountServiceComponent;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.integration.accountapi.rules.AccountApiRules;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.service.account.AccountService;
import com.revolut.moneytransfer.sparkapprule.SparkServerRule;
import com.revolut.moneytransfer.utils.Helper;
import com.revolut.moneytransfer.utils.TestHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

/**
 * @author AQIB JAVED
 * @since 12/16/2019
 * @version 1.0
 */
public class AccountApiTest {
	// Getting dagger2 component for account service
	private AccountServiceComponent accountServiceComponent = DaggerAccountServiceComponent
			.create();
	// Getting service from component
	private AccountService accountService = accountServiceComponent.buildAccountServiceImpl();
	@Rule
	public final SparkServerRule SPARK_SERVER = new SparkServerRule(http -> {
		AccountApiRules.userApiRules(http, accountService);
	});

	@Before
	public void before() throws InterruptedException {
		RestAssured.baseURI = Helper.BASE_URL;
	}

	@Test
	public void testGetById() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, TestHelper.GET_ACCOUNT_BY_ID).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		System.out.println(account);
		assertEquals(account.getId(), "a1");
		assertEquals(account.getBalance(), BigDecimal.valueOf(5000));
		assertEquals(account.getAccountTitle(), "account-A");
	}
}
