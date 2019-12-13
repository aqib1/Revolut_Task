package com.revolut.moneytransfer.dao.trans.Impl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

import com.revolut.moneytransfer.dao.trans.TransDao;
import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.TransResponseDto;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.utils.data.DataUtils;

/**
 * * ===== Database information ==============
 * <p>
 * This TransDaoImpl class is used to provide transaction. For the simplicity of
 * task as it mentioned in requirement II i am not using any database instead of
 * that i am using map, in future we can replace that structure with actual
 * database
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
public class TransDaoImpl implements TransDao {

	private static TransDaoImpl transDao = null;
	private StampedLock stampedLock = new StampedLock();

	private TransDaoImpl() {

	}

	@Override
	public TransResponseDto transfer(TransRequestDto request) {
		// Acquire a write lock
		long stemp = stampedLock.writeLock();
		try {
			AccountModel sender = getAccount(request.getFromAccount());
			AccountModel reciever = getAccount(request.getToAccount());
			if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				throw new InvalidAmountException(
						"Invalid amount, negative or zero value not acceptable");
			}
			if (request.getAmount().compareTo(sender.getBalance()) > 0) {
				throw new InvalidAmountException(
						"Balance is not sufficient to make transaction against account id["
								+ sender.getId() + "]");
			}
			sender.setBalance(sender.getBalance().subtract(request.getAmount()));
			reciever.setBalance(reciever.getBalance().add(request.getAmount()));
			// as object are new so we need to update in DB
			setAccount(sender);
			setAccount(reciever);
			return TransResponseDto.builder().withReciever(reciever).withSender(sender).build();
		} finally {
			stampedLock.unlockWrite(stemp);
		}
	}

	private void setAccount(AccountModel sender) {
		DataUtils.getInstance().getAccountData().put(sender.getId(), sender);
	}

	// Will return new account objects (stream API is immutable (java 8))
	private AccountModel getAccount(final String fromAccount) {
		return DataUtils.getInstance().getAccountData().values().stream()
				.filter(x -> x.getId().equals(fromAccount)).findAny()
				.orElseThrow(() -> new DataNotFoundException(
						"Account not found against id[" + fromAccount + "]"));
	}

	// Double check locking
	public static TransDaoImpl getInstance() {
		if (Objects.isNull(transDao))
			synchronized (TransDaoImpl.class) {
				if (Objects.isNull(transDao))
					transDao = new TransDaoImpl();
			}
		return transDao;
	}

}
