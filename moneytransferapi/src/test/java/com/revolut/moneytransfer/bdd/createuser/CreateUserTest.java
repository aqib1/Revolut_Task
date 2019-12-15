package com.revolut.moneytransfer.bdd.createuser;

import static com.revolut.moneytransfer.utils.Helper.PORT_8080;
import static com.revolut.moneytransfer.utils.Helper.RESPONSE_TYPE_JSON;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import com.revolut.moneytransfer.dto.requests.UserRequestDto;
import com.revolut.moneytransfer.sparkapp.SparkApp;
import com.revolut.moneytransfer.utils.Helper;
import com.revolut.moneytransfer.utils.TestHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class CreateUserTest {

	private UserRequestDto userRequestDto;

	@Before
	public void init() {
		RestAssured.baseURI = Helper.POST_USER_CREATE;
		SparkApp.getInstance().setPort(PORT_8080).setResponseType(RESPONSE_TYPE_JSON)
				.registerExceptions().registerControllers();
	}

	@Given("^that user details for new profile creation$")
	public void givenUserCreationRequest() {
		userRequestDto = TestHelper.getUserCreationRequestBDD();
	}

	@When("^the new user creation is requested$")
	public void whenUserCreationRequested() {
		Response response = RestAssured.given().contentType(ContentType.JSON).body(userRequestDto)
				.request(Method.POST, "/").then().statusCode(200).extract().response();
		System.out.println(response);
	}
	
	@Then("^a new user is created$")
	public void thenANewUserIsCreated() {
		assertTrue(false);
	}
}
