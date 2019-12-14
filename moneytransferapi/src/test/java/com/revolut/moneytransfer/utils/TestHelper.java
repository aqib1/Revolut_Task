package com.revolut.moneytransfer.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;

import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.models.AccountModel;
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

	/**
	 * @return
	 */
	public static AccountModel getTestAccount() {
		return AccountModel.builder().withAccountTitle("Test-Account")
				.withBalance(BigDecimal.valueOf(10000)).withId("ac11").withUserId("1")
				.withCurrency(Currency.getInstance("USD")).build();
	}

	/**
	 * @return
	 */
	public static Collection<AccountModel> getTestAllAccount() {
		return Arrays.asList(getTestAccount());
	}

	/**
	 * @return
	 */
	public static AccountModel getTestUpdateAccount() {
		return AccountModel.builder().withAccountTitle("Test-Account")
				.withBalance(BigDecimal.valueOf(20000)).withId("ac11").withUserId("1")
				.withCurrency(Currency.getInstance("USD")).build();
	}

	/**
	 * @return
	 */
	public static AccountModel getTestDeleteAccount() {
		return AccountModel.builder().withAccountTitle("Test-Account")
				.withBalance(BigDecimal.valueOf(10000)).withId("ac11").withUserId("1")
				.withCurrency(Currency.getInstance("USD")).build();
	}

	/**
	 * @return
	 */
	public static WithdrawRequestDto getWithdrawRequestDto() {
		return WithdrawRequestDto.builder().withAccountId("ac11")
				.withAmount(BigDecimal.valueOf(1000)).build();
	}

	/**
	 * @return
	 */
	public static WithdrawResponseDto getWithdrawResponse() {
		return WithdrawResponseDto.builder().withDrawAmount(BigDecimal.valueOf(1000))
				.currentAmount(BigDecimal.valueOf(2000)).build();
	}

	/**
	 * @return
	 */
	public static DepositRequest getDepositRequest() {
		return DepositRequest.builder().withAccountId("ac11").withAmount(BigDecimal.valueOf(1000))
				.build();
	}

	/**
	 * @return
	 */
	public static DepositResponse getDepositResponse() {
		return DepositResponse.builder().withCurrentAmount(BigDecimal.valueOf(2000))
				.withDepositAmount(BigDecimal.valueOf(1000)).build();
	}
}
