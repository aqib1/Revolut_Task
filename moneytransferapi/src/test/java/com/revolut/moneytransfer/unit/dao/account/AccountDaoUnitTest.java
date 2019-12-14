package com.revolut.moneytransfer.unit.dao.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/14/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountDaoUnitTest {

	@Mock
	private AccountDao accountDao;

	@Before
	public void init() {
		mockGetAll();
		mockTestGetById();
		mockTestCreate();
		mockTestUpdate();
		mockTestExists();
		mockTestDelete();
		mockTestWithdraw();
		mockTestDeposit();
		mockBalance();
	}

	private void mockBalance() {
		Mockito.when(accountDao.balance(Mockito.anyString())).thenReturn(BigDecimal.valueOf(10000));

	}

	private void mockGetAll() {
		Mockito.when(accountDao.getAll()).thenReturn(TestHelper.getTestAllAccount());
	}

	@Test
	public void testGetAll() {
		Collection<AccountModel> accounts = accountDao.getAll();
		Mockito.verify(accountDao, Mockito.times(1)).getAll();
		AccountModel account = accounts.iterator().next();
		assertEquals(account.getAccountTitle(), "Test-Account");
		assertEquals(account.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		assertEquals(account.getId(), "ac11");
		assertEquals(account.getUserId(), "1");
	}

	private void mockGetAllDataNotFoundException() {
		Mockito.when(accountDao.getAll()).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetAllDataNotFoundException() {
		mockGetAllDataNotFoundException();
		accountDao.getAll();
		Mockito.verify(accountDao, Mockito.times(1)).getAll();
	}

	private void mockTestGetById() {
		Mockito.when(accountDao.getById(Mockito.anyString()))
				.thenReturn(TestHelper.getTestAccount());
	}

	@Test
	public void testGetById() {
		AccountModel account = accountDao.getById("ac11");
		Mockito.verify(accountDao, Mockito.times(1)).getById(Mockito.anyString());
		assertEquals(account.getAccountTitle(), "Test-Account");
		assertEquals(account.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		assertEquals(account.getId(), "ac11");
		assertEquals(account.getUserId(), "1");
	}

	private void mockTestGetByIdDataNotFoundException() {
		Mockito.when(accountDao.getById(Mockito.anyString()))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetByIdDataNotFoundException() {
		mockTestGetByIdDataNotFoundException();
		accountDao.getById("ac11");
		Mockito.verify(accountDao, Mockito.times(1)).getById(Mockito.anyString());
	}

	private void mockTestCreate() {
		Mockito.when(accountDao.create(Mockito.any(AccountModel.class)))
				.thenReturn(TestHelper.getTestAccount());
	}

	// create method will create and return created account
	@Test
	public void testCreate() {
		AccountModel account = accountDao.create(TestHelper.getTestAccount());
		Mockito.verify(accountDao, Mockito.times(1)).create(Mockito.any(AccountModel.class));
		assertEquals(account.getAccountTitle(), "Test-Account");
		assertEquals(account.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		assertEquals(account.getId(), "ac11");
		assertEquals(account.getUserId(), "1");
	}

	private void mockTestCreateDataNotFoundException() {
		Mockito.when(accountDao.create(Mockito.any(AccountModel.class)))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testTestCreateDataNotFoundException() {
		mockTestCreateDataNotFoundException();
		accountDao.create(TestHelper.getTestAccount());
		Mockito.verify(accountDao, Mockito.times(1)).create(Mockito.any(AccountModel.class));
	}

	private void mockTestUpdate() {
		Mockito.when(accountDao.update(Mockito.any(AccountModel.class)))
				.thenReturn(TestHelper.getTestUpdateAccount());
	}

	@Test
	public void testUpdate() {
		AccountModel account = accountDao.update(TestHelper.getTestAccount());
		Mockito.verify(accountDao, Mockito.times(1)).update(Mockito.any(AccountModel.class));
		assertEquals(account.getAccountTitle(), "Test-Account");
		assertEquals(account.getBalance(), BigDecimal.valueOf(20000));
		assertEquals(account.getCurrency(), Currency.getInstance("USD"));
		assertEquals(account.getId(), "ac11");
		assertEquals(account.getUserId(), "1");
	}

	private void mockTestUpdateDataNotFoundException() {
		Mockito.when(accountDao.update(Mockito.any(AccountModel.class)))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testUpdateDataNotFoundException() {
		mockTestUpdateDataNotFoundException();
		accountDao.update(TestHelper.getTestAccount());
		Mockito.verify(accountDao, Mockito.times(1)).update(Mockito.any(AccountModel.class));
	}

	private void mockTestExists() {
		Mockito.when(accountDao.exists(Mockito.anyString())).thenReturn(Boolean.TRUE);
	}

	@Test
	public void testExists() {
		assertTrue(accountDao.exists("ac11"));
		Mockito.verify(accountDao, Mockito.times(1)).exists(Mockito.anyString());
	}

	private void mockTestExistsDataNotFound() {
		Mockito.when(accountDao.exists(Mockito.anyString())).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testExistsDataNotFound() {
		mockTestExistsDataNotFound();
		accountDao.exists("ac11");
		Mockito.verify(accountDao, Mockito.times(1)).exists(Mockito.anyString());
	}

	private void mockTestDelete() {
		Mockito.when(accountDao.delete(Mockito.anyString()))
				.thenReturn(TestHelper.getTestDeleteAccount());
	}

	// delete API will delete and send deleted object
	@Test
	public void testDelete() {
		AccountModel accountModel = accountDao.delete("1");
		Mockito.verify(accountDao, Mockito.times(1)).delete(Mockito.anyString());
		assertEquals(accountModel.getAccountTitle(), "Test-Account");
		assertEquals(accountModel.getBalance(), BigDecimal.valueOf(10000));
		assertEquals(accountModel.getCurrency(), Currency.getInstance("USD"));
		assertEquals(accountModel.getId(), "ac11");
		assertEquals(accountModel.getUserId(), "1");
	}

	private void mockTestDeleteDataNotFound() {
		Mockito.when(accountDao.delete(Mockito.anyString())).thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testDeleteDataNotFound() {
		mockTestDeleteDataNotFound();
		accountDao.delete("1");
		Mockito.verify(accountDao, Mockito.times(1)).delete(Mockito.anyString());
	}

	private void mockTestWithdraw() {
		Mockito.when(accountDao.withDraw(Mockito.any(WithdrawRequestDto.class)))
				.thenReturn(TestHelper.getWithdrawResponse());
	}

	@Test
	public void testWithdraw() {
		WithdrawResponseDto request = accountDao.withDraw(TestHelper.getWithdrawRequestDto());
		Mockito.verify(accountDao, Mockito.times(1))
				.withDraw(Mockito.any(WithdrawRequestDto.class));
		assertEquals(request.getCurrentAmount(), BigDecimal.valueOf(2000));
		assertEquals(request.getWithDrawAmount(), BigDecimal.valueOf(1000));
	}

	private void mockTestWithdrawDataNotFoundException() {
		Mockito.when(accountDao.withDraw(Mockito.any(WithdrawRequestDto.class)))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testWithDrawDataNotFoundException() {
		mockTestWithdrawDataNotFoundException();
		accountDao.withDraw(TestHelper.getWithdrawRequestDto());
		Mockito.verify(accountDao, Mockito.times(1))
				.withDraw(Mockito.any(WithdrawRequestDto.class));
	}

	private void mockTestWithdrawInvalidAmountException() {
		Mockito.when(accountDao.withDraw(Mockito.any(WithdrawRequestDto.class)))
				.thenThrow(new InvalidAmountException());
	}

	@Test(expected = InvalidAmountException.class)
	public void testWithdrawInvalidAmountException() {
		mockTestWithdrawInvalidAmountException();
		accountDao.withDraw(TestHelper.getWithdrawRequestDto());
		Mockito.verify(accountDao, Mockito.times(1))
				.withDraw(Mockito.any(WithdrawRequestDto.class));
	}

	private void mockTestDeposit() {
		Mockito.when(accountDao.deposit(Mockito.any(DepositRequest.class)))
				.thenReturn(TestHelper.getDepositResponse());
	}

	@Test
	public void testDeposit() {
		DepositResponse response = accountDao.deposit(TestHelper.getDepositRequest());
		Mockito.verify(accountDao, Mockito.times(1)).deposit(Mockito.any(DepositRequest.class));
		assertEquals(response.getCurrentAmount(), BigDecimal.valueOf(2000));
		assertEquals(response.getDepositAmount(), BigDecimal.valueOf(1000));
	}

	private void mockTestDepositDataNotFoundException() {
		Mockito.when(accountDao.deposit(Mockito.any(DepositRequest.class)))
				.thenThrow(new DataNotFoundException());
	}

	@Test(expected = DataNotFoundException.class)
	public void testDepositDataNotFoundException() {
		mockTestDepositDataNotFoundException();
		accountDao.deposit(TestHelper.getDepositRequest());
		Mockito.verify(accountDao, Mockito.times(1)).deposit(Mockito.any(DepositRequest.class));
	}

	private void mockTestDepositInvalidAmountException() {
		Mockito.when(accountDao.deposit(Mockito.any(DepositRequest.class)))
				.thenThrow(new InvalidAmountException());
	}

	@Test(expected = InvalidAmountException.class)
	public void testDepositInvalidAmountException() {
		mockTestDepositInvalidAmountException();
		accountDao.deposit(TestHelper.getDepositRequest());
		Mockito.verify(accountDao, Mockito.times(1)).deposit(Mockito.any(DepositRequest.class));
	}
	
	@Test
	public void testBalance() {
		BigDecimal amount = accountDao.balance("1");
		assertEquals(amount, BigDecimal.valueOf(10000));
	}
}
