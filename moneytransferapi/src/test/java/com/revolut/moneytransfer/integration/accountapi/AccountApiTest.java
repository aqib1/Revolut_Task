package com.revolut.moneytransfer.integration.accountapi;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.revolut.moneytransfer.dagger2.components.AccountServiceComponent;
import com.revolut.moneytransfer.dagger2.components.DaggerAccountServiceComponent;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.integration.accountapi.rules.AccountApiRules;
import com.revolut.moneytransfer.models.AccountModel;
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
		AccountApiRules.accountApiRules(http, accountService);
	});

	@Before
	public void before() {
		RestAssured.baseURI = Helper.BASE_URL;
	}

	@Test
	public void testGetById() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, TestHelper.GET_ACCOUNT_BY_ID).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		assertEquals(account.getId(), "a1");
		assertEquals(account.getBalance(), BigDecimal.valueOf(5000));
		assertEquals(account.getAccountTitle(), "account-A");
	}

	@Test
	public void testGetAll() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, Helper.GET_ACCOUNTS).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel[] accounts = Helper.GSON.fromJson(response.getData(), AccountModel[].class);
		AccountModel account = accounts[0];
		assertEquals(account.getId(), "a1");
		assertEquals(account.getBalance(), BigDecimal.valueOf(5000));
		assertEquals(account.getAccountTitle(), "account-A");
		account = accounts[1];
		assertEquals(account.getId(), "a11");
		assertEquals(account.getAccountTitle(), "account-Z");
		assertEquals(account.getUserId(), "u11");
	}

	@Test
	public void testUpdate() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getAccountRequestForUpdate()).put(Helper.PUT_ACCOUNT_UPDATE).then()
				.statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		assertEquals(account.getId(), "a11");
		assertEquals(account.getAccountTitle(), "account-Z");
		assertEquals(account.getUserId(), "u11");
		assertEquals(account.getCurrency(), Currency.getInstance("PKR"));
	}

	@Test
	public void testCreate() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getAccountRequestForCreate()).post(Helper.POST_ACCOUNT_CREATE)
				.then().statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		assertEquals(account.getId(), "ac12");
		assertEquals(account.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(account.getAccountTitle(), "Test-Account1");
		assertEquals(account.getUserId(), "u1");
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));

		// For the simplicity of this task, i am not using database and
		// for maintaining data atomic state
		rollBackAccount();
	}

	private void rollBackAccount() {
		RestAssured.given().contentType(ContentType.JSON).delete(TestHelper.DELETE_ACCOUNT_BY_ID)
				.then().statusCode(200);

	}

	@Test
	public void testDelete() {
		// For the simplicity of this task, i am not using database and
		// for maintaining data atomic state
		createDataForDeletion();
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.delete(TestHelper.DELETE_ACCOUNT_BY_ID).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		assertEquals(account.getId(), "ac12");
		assertEquals(account.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(account.getAccountTitle(), "Test-Account1");
		assertEquals(account.getUserId(), "u1");
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));

	}

	private void createDataForDeletion() {
		RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getAccountRequestForCreate()).post(Helper.POST_ACCOUNT_CREATE)
				.then().statusCode(200);
	}

	@Test
	public void testIsExists() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.options(TestHelper.OPTION_ACCOUNT_EXIST).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		assertEquals(response.getData().getAsBoolean(), true);
	}

	@Test
	public void testWithDraw() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getWithDrawRequest()).post(Helper.POST_WITHDRAW).then()
				.statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		WithdrawResponseDto widthDrawResponse = Helper.GSON.fromJson(response.getData(),
				WithdrawResponseDto.class);
		assertEquals(widthDrawResponse.getCurrentAmount(), BigDecimal.valueOf(500));
		assertEquals(widthDrawResponse.getWithDrawAmount(), BigDecimal.valueOf(500));

		// For the simplicity of this task, i am not using database and
		// for maintaining data atomic state
		rollBackWithDraw();
	}

	private void rollBackWithDraw() {
		RestAssured.given().contentType(ContentType.JSON).body(TestHelper.getDepositRequestForA11())
				.post(Helper.POST_DEPOSIT);
	}

	@Test
	public void testDeposit() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getDepositRequestForA11()).post(Helper.POST_DEPOSIT).then()
				.statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		DepositResponse widthDrawResponse = Helper.GSON.fromJson(response.getData(),
				DepositResponse.class);
		assertEquals(widthDrawResponse.getDepositAmount(), BigDecimal.valueOf(500));
		assertEquals(widthDrawResponse.getCurrentAmount(), BigDecimal.valueOf(1500));
		// For the simplicity of this task, i am not using database and
		// for maintaining data atomic state
		rollBackDeposit();
	}

	private void rollBackDeposit() {
		RestAssured.given().contentType(ContentType.JSON).body(TestHelper.getWithDrawRequest())
				.post(Helper.POST_WITHDRAW);
	}

}
