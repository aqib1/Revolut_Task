package com.revolut.moneytransfer.integration.userapi;

import static com.revolut.moneytransfer.utils.Helper.GET_USERS;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.revolut.moneytransfer.dagger2.components.DaggerUserServiceComponent;
import com.revolut.moneytransfer.dagger2.components.UserServiceComponent;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.integration.userapi.rules.UserApiRules;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.service.user.UserService;
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
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, GET_USERS).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		UserModel[] users = Helper.GSON.fromJson(response.getData(), UserModel[].class);
		// user u1
		assertEquals(users[0].getId(), "u1");
		assertEquals(users[0].getFirstName(), "USER");
		assertEquals(users[0].getLastName(), "Temp");
		assertEquals(users[0].getEmail(), "u1@gmail.com");
		assertEquals(users[0].getCNIC(), "1231-211-2111");

		// user u2
		assertEquals(users[1].getId(), "u2");
		assertEquals(users[1].getFirstName(), "TEST");
		assertEquals(users[1].getLastName(), "UMP");
		assertEquals(users[1].getEmail(), "u2@gmail.com");
		assertEquals(users[1].getCNIC(), "9960-1110-2888");
	}

	@Test
	public void testGetUserById() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.GET, TestHelper.GET_USER_BY_ID_TEST).then().statusCode(200)
				.extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		UserModel user = Helper.GSON.fromJson(response.getData(), UserModel.class);
		assertEquals(user.getId(), "u3");
		assertEquals(user.getFirstName(), "REMO");
		assertEquals(user.getLastName(), "PPM");
		assertEquals(user.getEmail(), "u3@gmail.com");
		assertEquals(user.getCNIC(), "8888-3201-2888");
	}

	@Test
	public void testUpdateUser() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getUserUpadteRequest()).request(Method.PUT, Helper.PUT_USER_UPDATE)
				.then().statusCode(200).extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		UserModel user = Helper.GSON.fromJson(response.getData(), UserModel.class);
		assertEquals(user.getId(), "u1");
		assertEquals(user.getFirstName(), "USER");
		assertEquals(user.getLastName(), "Temp");
		assertEquals(user.getEmail(), "u1@gmail.com");
		assertEquals(user.getCNIC(), "1231-211-2111");
		assertEquals(user.getContactNumber(), "+93654559990");
	}

	@Test
	public void testCreateUser() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.body(TestHelper.getUserCreateRequest())
				.request(Method.POST, Helper.POST_USER_CREATE).then().statusCode(200).extract()
				.as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		UserModel user = Helper.GSON.fromJson(response.getData(), UserModel.class);
		assertEquals(user.getId(), "u4");
		assertEquals(user.getFirstName(), "ERAR");
		assertEquals(user.getLastName(), "UIPPL");
		assertEquals(user.getEmail(), "u4@gmail.com");
		assertEquals(user.getCNIC(), "12455-65554-8888");
		assertEquals(user.getContactNumber(), "+97441788896");

		// As we are not using database so that we need to rollback operation for create
		// persistence
		rollBackCreateUserOperation();
	}

	private void rollBackCreateUserOperation() {
		RestAssured.given().contentType(ContentType.JSON).request(Method.DELETE,
				TestHelper.DELETE_USER_BY_ID);
	}

	@Test
	public void testUserExists() {
		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.OPTIONS, TestHelper.OPTION_USER_EXIST).then().statusCode(200)
				.extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		assertEquals(response.getData().getAsBoolean(), true);
	}

	@Test
	public void testUserDelete() {
		// As we are not using database so that we need to create operation for delete
		// persistence
		dataCreationForTestingdeleteOperation();

		ResponseDto response = RestAssured.given().contentType(ContentType.JSON)
				.request(Method.DELETE, TestHelper.DELETE_USER_BY_ID).then().statusCode(200)
				.extract().as(ResponseDto.class);
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		UserModel user = Helper.GSON.fromJson(response.getData(), UserModel.class);
		assertEquals(user.getId(), "u4");
		assertEquals(user.getFirstName(), "ERAR");
		assertEquals(user.getLastName(), "UIPPL");
		assertEquals(user.getEmail(), "u4@gmail.com");
		assertEquals(user.getCNIC(), "12455-65554-8888");
		assertEquals(user.getContactNumber(), "+97441788896");
	}

	private void dataCreationForTestingdeleteOperation() {
		RestAssured.given().contentType(ContentType.JSON).body(TestHelper.getUserCreateRequest())
				.request(Method.POST, Helper.POST_USER_CREATE);
	}

	@After
	public void after() {
	}
}
