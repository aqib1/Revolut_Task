package com.revolut.moneytransfer.bdd.moneytransfer;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.responses.TransResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.utils.Helper;
import com.revolut.moneytransfer.utils.TestHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class MoneyTransferTest {

	private TransRequestDto transRequestDto = null;

	@Before
	public void init() throws InterruptedException {
		TestHelper.startSparkTestApp();
		RestAssured.baseURI = Helper.BASE_URL;
	}

	@Given("Transaction request with sender account id, reciever account and amount")
	public void givenMoneyTransferTest() {
		transRequestDto = TestHelper.getTransRequestForBDD();
	}

	@When("transfer action is called")
	public void whenMoneyTransferTest() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(transRequestDto).request(Method.POST, Helper.POST_TRANSACTION).then()
				.statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		TransResponseDto transResponse = Helper.GSON.fromJson(response.getData(),
				TransResponseDto.class);
		verifyRecieverAccount(transResponse.getReciever());
		verifySenderAccount(transResponse.getSender());
	}

	@Then("amount is deducted from sender account")
	public void thenMoneyTransferTest() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, "/account/a1").then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		verifySenderAccount(account);
	}

	private void verifySenderAccount(AccountModel account) {
		assertEquals(account.getId(), "a1");
		assertEquals(account.getAccountTitle(), "account-A");
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		// Amount detected from sender
		assertEquals(account.getBalance(), BigDecimal.valueOf(4500));
	}

	@And("reciever will recieve amount")
	public void andMoneyTransferTest() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, "/account/a2").then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		AccountModel account = Helper.GSON.fromJson(response.getData(), AccountModel.class);
		verifyRecieverAccount(account);
	}

	private void verifyRecieverAccount(AccountModel account) {
		assertEquals(account.getId(), "a2");
		assertEquals(account.getAccountTitle(), "account-B");
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		// Amount transfer successfully
		assertEquals(account.getBalance(), BigDecimal.valueOf(2000));
	}

	@After
	public void afterTests() {
		TestHelper.stopSparTestkApp();
	}
}
