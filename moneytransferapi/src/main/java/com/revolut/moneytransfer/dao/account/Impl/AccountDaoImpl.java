package com.revolut.moneytransfer.dao.account.Impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.utils.data.DataUtils;

/**
 * * ===== Database information ==============
 * <p>
 * This AccountDaoImpl class is used to provide account details and IO operation
 * against data. For the simplicity of task as it mentioned in requirement II i
 * am not using any database instead of that i am using map, in future we can
 * replace that structure with actual database
 * </p>
 * ========= Thread safety information =======
 * <p>
 * To make it thread-safe we will use synchronization as we are using Map, and
 * that will be updated, with requests. we have to make our data synchronize for
 * the sake of multiple requests (threads) safety, in the case of database we
 * have isolation to prevent our database
 * </p>
 * <p>
 * Synchronization can be acquired on method level (Coarse grain locking
 * mechanism) but this will make our method slow as no thread else can enter to
 * other methods (in database term we can say highest level of isolation) we
 * will use fine grain locking mechanism separately for read mechanism and
 * writing mechanism
 * </p>
 * <p>
 * I am using java 1.8 StampedLock @see {@link StampedLock} <br>
 * <strong> Reason of using StampedLock is one of its feature optimistic locking
 * in this lock as per documentation said, we do not need to apply full-fledged
 * read lock every time, some time lock is not held by any write operation, we
 * use tryOptimisticRead to check if the lock is hold by write operation and
 * then check result with validate method. </strong> <br>
 * Java 1.8 StampedLock is much more efficient and fast as compared to
 * ReentrantLock specially optimistic locking which make synchronization
 * overhead very slow. You can also use ReentrantLock but it very slow as
 * compared to new java 1.8 stamped lock
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/13/2019
 * 
 */
public class AccountDaoImpl implements AccountDao {
	private volatile static AccountDaoImpl accountdaoImpl = null;
	private Map<String, AccountModel> accountData = null;
	private StampedLock stampedLock = new StampedLock();

	private AccountDaoImpl() {
		accountData = DataUtils.getInstance().getAccountData();
	}

	/**
	 * <p>
	 * This method is use to return all account. We used optimistic read lock from
	 * StampedLock
	 * </p>
	 * 
	 * @return {@link Collection<AccountModel>}
	 */
	@Override
	public Collection<AccountModel> getAll() {
		// return zero if it acquire by a write lock (exclusive locked)
		long stamp = stampedLock.tryOptimisticRead();
		// Synchronization overhead is very low if validate() succeeds
		// Always return true if stamp is non zero (as not acquired by write lock)
		if (stampedLock.validate(stamp))
			return getAllAccounts();
		// Only in the case when write lock is acquired we need to apply read lock
		stamp = stampedLock.readLock();
		try {
			return getAllAccounts();
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}

	private Collection<AccountModel> getAllAccounts() {
		if (accountData.isEmpty())
			throw new DataNotFoundException("Account details not exists in database");
		return accountData.values();
	}

	/**
	 * <p>
	 * This method is use to return account by id. We used optimistic read lock from
	 * StampedLock
	 * </p>
	 * 
	 * @param id
	 * @return {@link AccountModel}
	 */
	@Override
	public AccountModel getById(String id) {
		// return zero if it acquire by a write lock (exclusive locked)
		long stamp = stampedLock.tryOptimisticRead();
		// Synchronization overhead is very low if validate() succeeds
		// Always return true if stamp is non zero (as not acquired by write lock)
		if (stampedLock.validate(stamp))
			return getAccountById(id);
		// Only in the case when write lock is acquired we need to apply read lock
		stamp = stampedLock.readLock();
		try {
			return getAccountById(id);
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}

	/**
	 * @param id
	 * @return
	 */
	private AccountModel getAccountById(String id) {
		if (!accountData.containsKey(id))
			throw new DataNotFoundException("Account not exists against id [" + id + "]");
		return accountData.get(id);
	}

	/**
	 * @param account
	 * @return {@link AccountModel}
	 */
	@Override
	public AccountModel create(AccountModel account) {
		// Acquire a write lock
		long stemp = stampedLock.writeLock();
		try {
			// Check is account already exists and throw exception
			if (accountData.containsKey(account.getId()))
				throw new DataDuplicationException(
						"Account already exists against id [" + account.getId() + "]");
			if (!DataUtils.getInstance().getUserData().containsKey(account.getUserId()))
				throw new IllegalArgumentException("User id not exists in database");

			accountData.put(account.getId(), account);
		} finally {
			stampedLock.unlockWrite(stemp);
		}
		return account;
	}

	/**
	 * <p>
	 * this method return updated value
	 * </p>
	 * 
	 * @param account
	 * @return {@link AccountModel}
	 */
	@Override
	public AccountModel update(AccountModel account) {
		// Acquire a write lock
		long stemp = stampedLock.writeLock();
		try {
			if (!accountData.containsKey(account.getId()))
				throw new DataNotFoundException(
						"Account Id [" + account.getId() + "] not exists in database");
			accountData.put(account.getId(), account);
		} finally {
			stampedLock.unlockWrite(stemp);
		}
		return account;
	}

	/**
	 * @param id
	 * @return {@link Boolean}
	 */
	@Override
	public boolean exists(String id) {
		if (!accountData.containsKey(id))
			throw new DataNotFoundException("Account Id [" + id + "] not exists in database");
		// return zero if it acquire by a write lock (exclusive locked)
		long stamp = stampedLock.tryOptimisticRead();
		// Synchronization overhead is very low if validate() succeeds
		// Always return true if stamp is non zero (as not acquired by write lock)
		if (stampedLock.validate(stamp))
			return accountData.containsKey(id);
		// If write lock is acquired then we will apply read lock
		stamp = stampedLock.readLock();
		try {
			return accountData.containsKey(id);
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}

	/**
	 * <p>
	 * This method return the value which is deleted from data
	 * </p>
	 * 
	 * @param id
	 * @return {@link AccountModel}
	 */
	@Override
	public AccountModel delete(String id) {
		long stemp = stampedLock.writeLock();
		try {
			if (!accountData.containsKey(id))
				throw new DataNotFoundException("Account Id [" + id + "] not exists in database");
			return accountData.remove(id);
		} finally {
			stampedLock.unlockWrite(stemp);
		}
	}

	/**
	 * <p>
	 * This method is used to withdraw amount from account
	 * </p>
	 * 
	 * @param id
	 * @return {@link WithdrawResponseDto}
	 */
	@Override
	public WithdrawResponseDto withDraw(WithdrawRequestDto withdrawRequestDto) {
		long stemp = stampedLock.writeLock();
		try {
			if (!accountData.containsKey(withdrawRequestDto.getAccountId())) {
				throw new DataNotFoundException("Account Id [" + withdrawRequestDto.getAccountId()
						+ "] not exists in database");
			}
			AccountModel account = accountData.get(withdrawRequestDto.getAccountId());
			if (account.getBalance().compareTo(withdrawRequestDto.getAmount()) < 0)
				throw new InvalidAmountException(
						"Invalid amount, you do not have sufficient balance");
			if (withdrawRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0)
				throw new InvalidAmountException(
						"Invalid amount, negative or zero value not acceptable");
			account.setBalance(account.getBalance().subtract(withdrawRequestDto.getAmount()));
			return new WithdrawResponseDto().withDrawAmount(withdrawRequestDto.getAmount())
					.currentAmount(account.getBalance());
		} finally {
			stampedLock.unlockWrite(stemp);
		}
	}

	@Override
	public DepositResponse deposit(DepositRequest depositRequest) {
		long stemp = stampedLock.writeLock();
		try {
			if (!accountData.containsKey(depositRequest.getAccountId()))
				throw new DataNotFoundException("Account Id [" + depositRequest.getAccountId()
						+ "] not exists in database");
			if (depositRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0)
				throw new InvalidAmountException(
						"Invalid amount, negative or zero value not acceptable");
			AccountModel account = accountData.get(depositRequest.getAccountId());
			account.setBalance(account.getBalance().add(depositRequest.getAmount()));
			return new DepositResponse().currentAmount(account.getBalance())
					.depositAmount(depositRequest.getAmount());
		} finally {
			stampedLock.unlockWrite(stemp);
		}
	}

	// Double check locking singleton pattern
	public static AccountDaoImpl getInstance() {
		if (Objects.isNull(accountdaoImpl))
			synchronized (AccountDaoImpl.class) {
				if (Objects.isNull(accountdaoImpl))
					accountdaoImpl = new AccountDaoImpl();
			}
		return accountdaoImpl;
	}

}
