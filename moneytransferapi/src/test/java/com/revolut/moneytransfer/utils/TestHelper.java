package com.revolut.moneytransfer.utils;

import java.util.Arrays;
import java.util.Collection;
import com.revolut.moneytransfer.models.UserModel;

/**
 * @author AQIB JAVED
 * @since 12/14/2019
 * @version 1.0
 */
public class TestHelper {
	private TestHelper() {

	}

	/**
	 * @return
	 */
	public static UserModel getTestUser() {
		return UserModel.builder().withId("1").withFirstName("aqib").withLastName("javed")
				.withCNIC("1234-12212-11").withAddress("H# 1, st# 2, budapest")
				.withContactNumber("+32145777789").withEmail("aqib_javed@gmail.com").build();
	}

	/**
	 * @return
	 */
	public static Collection<UserModel> getTestAllUsers() {
		return Arrays.asList(getTestUser());
	}

	/**
	 * @return
	 */
	public static UserModel getDeletedUser() {
		return UserModel.builder().withId("1").withFirstName("aqib").withLastName("javed")
				.withCNIC("1234-12212-11").withAddress("H# 1, st# 2, budapest")
				.withContactNumber("+32145777789").withEmail("aqib_javed@gmail.com").build();
	}

	/**
	 * @return
	 */
	public static UserModel getTestUserUpdate() {
		return UserModel.builder().withId("1").withFirstName("aqib").withLastName("javed")
				.withCNIC("1234-12212-11").withAddress("H# 1, st# 2, budapest")
				.withContactNumber("+321477818177").withEmail("aqib_javed@gmail.com").build();
	}
}
