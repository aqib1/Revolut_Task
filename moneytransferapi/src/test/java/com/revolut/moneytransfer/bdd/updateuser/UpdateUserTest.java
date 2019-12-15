package com.revolut.moneytransfer.bdd.updateuser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateUserTest {

	@Given("A updated user details")
	public void givenUpdatedUserTest() {
		System.out.println("test");
	}
	
	@When("update action is called")
	public void whenUpdatedUserTest() {
		System.out.println("test1");
	}
	
	@Then("will update user and send updated user back")
	public void thenReturnUpdatedUser() {
		System.out.println("test2");
	}
}
