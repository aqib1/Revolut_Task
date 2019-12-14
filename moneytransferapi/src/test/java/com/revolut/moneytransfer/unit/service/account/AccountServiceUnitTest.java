package com.revolut.moneytransfer.unit.service.account;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.exception.BadRequestParamsException;
import com.revolut.moneytransfer.exception.InvalidRequestException;
import com.revolut.moneytransfer.service.account.AccountService;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/15/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceUnitTest {

	@Mock
	private AccountService accountService;

	@Before
	public void init() {
		mockGetAll();
		mockGetById();
		mockCreate();
		mockUpdate();
		mockExists();
		mockdelete();
		mockWithdraw();
		mockDeposit();
		mockBalance();
	}

	private void mockBalance() {
		Mockito.when(accountService.balance(Mockito.anyString()))
				.thenReturn(TestHelper.getBalanceResponse());
	}

	private void mockDeposit() {
		Mockito.when(accountService.deposit(Mockito.any(DepositRequest.class)))
				.thenReturn(TestHelper.getResponse());
	}

	private void mockWithdraw() {
		Mockito.when(accountService.withdraw(Mockito.any(WithdrawRequestDto.class)))
				.thenReturn(TestHelper.getResponse());
	}

	private void mockdelete() {
		Mockito.when(accountService.delete(Mockito.anyString()))
				.thenReturn(TestHelper.getResponse());

	}

	private void mockExists() {
		Mockito.when(accountService.exists(Mockito.anyString()))
				.thenReturn(TestHelper.getIsExistsResponse());

	}

	private void mockUpdate() {
		Mockito.when(accountService.update(Mockito.any(AccountRequestDto.class)))
				.thenReturn(TestHelper.getResponse());

	}

	private void mockCreate() {
		Mockito.when(accountService.create(Mockito.any(AccountRequestDto.class)))
				.thenReturn(TestHelper.getResponse());

	}

	private void mockGetAll() {
		Mockito.when(accountService.getAll()).thenReturn(TestHelper.getResponse());
	}

	@Test
	public void testGetAll() {
		ResponseDto response = accountService.getAll();
		Mockito.verify(accountService, Mockito.times(1)).getAll();
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockGetById() {
		Mockito.when(accountService.getById(Mockito.anyString()))
				.thenReturn(TestHelper.getResponse());
	}

	@Test
	public void testGetById() {
		ResponseDto response = accountService.getById("1");
		Mockito.verify(accountService, Mockito.times(1)).getById(Mockito.anyString());
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockGetByIdIllegalArgumentException() {
		Mockito.when(accountService.getById(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetByIdIllegalArgumentException() {
		mockGetByIdIllegalArgumentException();
		accountService.getById(null);
		Mockito.verify(accountService, Mockito.times(1)).getById(null);
	}

	@Test
	public void testCreate() {
		ResponseDto response = accountService.create(TestHelper.getAccountRequest());
		Mockito.verify(accountService, Mockito.times(1))
				.create(Mockito.any(AccountRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockCreateInvalidRequestException() {
		Mockito.when(accountService.create(Mockito.any(AccountRequestDto.class)))
				.thenThrow(new InvalidRequestException());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCreateInvalidRequestException() {
		mockCreateInvalidRequestException();
		accountService.create(null);
		Mockito.verify(accountService, Mockito.times(1)).create(null);
	}

	private void mockCreateBadRequestParamsException() {
		Mockito.when(accountService.create(Mockito.any(AccountRequestDto.class)))
				.thenThrow(new BadRequestParamsException());
	}

	@Test(expected = BadRequestParamsException.class)
	public void testCreateBadRequestParamsException() {
		mockCreateBadRequestParamsException();
		accountService.create(null);
		Mockito.verify(accountService, Mockito.times(1)).create(null);
	}

	@Test
	public void testUpdate() {
		ResponseDto response = accountService.update(TestHelper.getAccountRequest());
		Mockito.verify(accountService, Mockito.times(1))
				.update(Mockito.any(AccountRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testExists() {
		ResponseDto response = accountService.exists("1");
		Mockito.verify(accountService, Mockito.times(1)).exists(Mockito.anyString());
		assertEquals(response.getData().getAsString(), "true");
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockExistsIllegalArgumentException() {
		Mockito.when(accountService.exists(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExistsIllegalArgumentException() {
		mockExistsIllegalArgumentException();
		accountService.exists(null);
		Mockito.verify(accountService, Mockito.times(1)).exists(null);
	}

	@Test
	public void testDelete() {
		ResponseDto response = accountService.delete("1");
		Mockito.verify(accountService, Mockito.times(1)).delete(Mockito.anyString());
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	private void mockDeleteIllegalArgumentException() {
		Mockito.when(accountService.delete(Mockito.anyString()))
				.thenThrow(new IllegalArgumentException());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteIllegalArgumentException() {
		mockDeleteIllegalArgumentException();
		accountService.delete(null);
		Mockito.verify(accountService, Mockito.times(1)).delete(null);
	}

	@Test
	public void testWithdraw() {
		ResponseDto response = accountService.withdraw(TestHelper.getWithdrawRequestDto());
		Mockito.verify(accountService, Mockito.times(1))
				.withdraw(Mockito.any(WithdrawRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testDeposit() {
		ResponseDto response = accountService.deposit(TestHelper.getDepositRequest());
		Mockito.verify(accountService, Mockito.times(1)).deposit(Mockito.any(DepositRequest.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}

	@Test
	public void testBalance() {
		ResponseDto response = accountService.balance("1");
		Mockito.verify(accountService, Mockito.times(1)).balance(Mockito.anyString());
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
		assertEquals(response.getData().getAsInt(), 1000);
	}

}
