package com.revolut.moneytransfer.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;

import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.requests.UserRequestDto;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.responses.TransResponseDto;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
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
	public static AccountModel getRecieverAccount() {
		return AccountModel.builder().withAccountTitle("Test-Account")
				.withBalance(BigDecimal.valueOf(10000)).withId("ac11").withUserId("1")
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

	/**
	 * @return
	 */
	public static TransRequestDto getTransRequest() {
		return TransRequestDto.builder().withAmount(BigDecimal.valueOf(500)).withFromAccount("ac11")
				.withToAccount("ac12").build();
	}

	/**
	 * @return
	 */
	public static AccountModel getSenderAccount() {
		return AccountModel.builder().withAccountTitle("Test-Account1")
				.withBalance(BigDecimal.valueOf(10000)).withId("ac22").withUserId("1")
				.withCurrency(Currency.getInstance("PKR")).build();
	}

	/**
	 * @return
	 */
	public static TransResponseDto getTransResponse() {
		return TransResponseDto.builder().withSender(getSenderAccount())
				.withReciever(getRecieverAccount()).build();
	}

	/**
	 * @return
	 */
	public static ResponseDto getResponse() {
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS).build();
	}

	/**
	 * @return
	 */
	public static AccountRequestDto getAccountRequest() {
		return AccountRequestDto.builder().withAccount(getTestAccount()).build();
	}

	/**
	 * @return
	 */
	public static ResponseDto getIsExistsResponse() {
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS).withData(true)
				.withMessage("Success").build();
	}

	/**
	 * @return
	 */
	public static ResponseDto getBalanceResponse() {
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS).withData(1000)
				.withMessage("Success").build();
	}

	/**
	 * @return
	 */
	public static UserRequestDto getUserRequest() {
		return UserRequestDto.builder().withUser(getTestUser()).build();
	}

}
