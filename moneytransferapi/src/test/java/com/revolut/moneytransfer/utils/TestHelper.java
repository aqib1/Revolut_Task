package com.revolut.moneytransfer.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

import com.revolut.moneytransfer.MoneyTransfer;
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

	public static final String GET_USER_BY_ID_TEST = "/user/u3";
	public static final String OPTION_USER_EXIST = "/user/u1";
	public static final String DELETE_USER_BY_ID = "/user/u4";
	public static final String GET_ACCOUNT_BY_ID = "/account/a1";

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

	/**
	 * @return
	 */
	public static UserRequestDto getUserCreationRequestBDD() {
		return UserRequestDto.builder()
				.withUser(UserModel.builder().withId("10").withFirstName("revolut")
						.withLastName("-").withCNIC("1234-12212-11")
						.withAddress("H# 1, st# 2, budapest").withContactNumber("+32145777789")
						.withEmail("revolut@gmail.com").build())
				.build();
	}

	/**
	 * @throws InterruptedException
	 */
	public static void startSparkTestApp() throws InterruptedException {
		MoneyTransfer.main(null);
		TimeUnit.SECONDS.sleep(1);
	}

	public static TransRequestDto getTransRequestForBDD() {
		return TransRequestDto.builder().withAmount(BigDecimal.valueOf(500)).withFromAccount("a1")
				.withToAccount("a2").build();
	}

	public static UserRequestDto getUserUpadteRequest() {
		return UserRequestDto.builder()
				.withUser(UserModel.builder().withAddress("H#1, ST#2, budapest")
						.withCNIC("1231-211-2111").withContactNumber("+93654559990")
						.withEmail("u1@gmail.com").withFirstName("USER").withLastName("Temp")
						.withId("u1").build())
				.build();
	}
	
	public static UserRequestDto getUserCreateRequest() {
		return UserRequestDto.builder()
				.withUser(UserModel.builder().withAddress("H#1, ST#12, budapest")
						.withCNIC("12455-65554-8888").withContactNumber("+97441788896")
						.withEmail("u4@gmail.com").withFirstName("ERAR").withLastName("UIPPL")
						.withId("u4").build())
				.build();
	}

	/**
	 * 
	 */
	public static void stopSparkTestApp() {
		MoneyTransfer.stopSparkApp();
	}

}
