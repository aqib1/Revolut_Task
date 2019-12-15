package com.revolut.moneytransfer.bdd.createuser;

import org.junit.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateUserTest {

	@Before
	public void init() {
	//	RestAssured.baseURI = Helper.POST_USER_CREATE;
//		SparkApp.getInstance().setPort(PORT_8080).setResponseType(RESPONSE_TYPE_JSON)
//				.registerExceptions().registerControllers();
	}

	@Given("A create user details")
	public void givenCreateUserTest() {
		//userRequestDto = TestHelper.getUserCreationRequestBDD();
		System.out.println("hi");
	}

	@When("create action is called")
	public void whenCreateUserTest() {
//		Response response = RestAssured.given().contentType(ContentType.JSON).body(userRequestDto)
//				.request(Method.POST, "/").then().statusCode(200).extract().response();
//		System.out.println(response);
		System.out.println("hi");
	}
	
	@Then("will create user and send created user back")
	public void thenCreatedUserTest() {
		//assertTrue(false);
		System.out.println("hi");
	}
}
