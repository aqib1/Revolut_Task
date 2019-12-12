package com.revolut.moneytransfer.dao.user.Impl;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.utils.data.DataUtils;

/**
 * ===== Database information ==============
 * <p>
 * This UserDaoImpl class is used to provide user details and IO operation
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
 * 
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
 * @since 12/10/2019
 * 
 */
public class UserDaoImpl implements UserDao {
	private volatile static UserDaoImpl userDaoImpl = null;
	private Map<String, UserModel> userData = null;
	private StampedLock stampedLock = new StampedLock();

	private UserDaoImpl() {
		userData = DataUtils.getInstance().getUserData();
	}

	/**
	 * <p>
	 * This method is use to return all Users. We used optimistic read lock from
	 * StampedLock
	 * </p>
	 * 
	 * @return {@link Collection<UserModel>}
	 */
	@Override
	public Collection<UserModel> getAll() {
		// return zero if it acquire by a write lock (exclusive locked)
		long stamp = stampedLock.tryOptimisticRead();
		// Synchronization overhead is very low if validate() succeeds
		// Always return true if stamp is non zero (as not acquired by write lock)
		if (stampedLock.validate(stamp))
			return getAllUsers();
		// Only in the case when write lock is acquired we need to apply read lock
		stamp = stampedLock.readLock();
		try {
			return getAllUsers();
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}

	private Collection<UserModel> getAllUsers() {
		if (userData.isEmpty())
			throw new DataNotFoundException("User details not exists in database");
		return userData.values();
	}

	/**
	 * <p>
	 * This method is use to return user by id. We used optimistic read lock from
	 * StampedLock
	 * </p>
	 * 
	 * @param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel getById(String id) {
		// return zero if it acquire by a write lock (exclusive locked)
		long stamp = stampedLock.tryOptimisticRead();
		// Synchronization overhead is very low if validate() succeeds
		// Always return true if stamp is non zero (as not acquired by write lock)
		if (stampedLock.validate(stamp))
			return getUserById(id);
		// Only in the case when write lock is acquired we need to apply read lock
		stamp = stampedLock.readLock();
		try {
			return getUserById(id);
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}

	private UserModel getUserById(String id) {
		if (!userData.containsKey(id))
			throw new DataNotFoundException("User not exists against id [" + id + "]");
		return userData.get(id);
	}

	/**
	 * @param user
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel create(UserModel user) {
		// Check is user already exists and throw exception
		if (userData.containsKey(user.getId()))
			throw new DataDuplicationException(
					"User already exists against id [" + user.getId() + "]");
		// Acquire a write lock
		long stemp = stampedLock.writeLock();
		try {
			userData.put(user.getId(), user);
		} finally {
			stampedLock.unlockWrite(stemp);
		}
		return user;
	}

	/**
	 * <p>
	 * this method return updated value
	 * </p>
	 * 
	 * @param user
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel update(UserModel user) {
		if (!userData.containsKey(user.getId()))
			throw new DataNotFoundException(
					"User Id [" + user.getId() + "] not exists in database");
		userData.put(user.getId(), user);
		return user;
	}

	/**
	 * @param id
	 * @return {@link Boolean}
	 */
	@Override
	public boolean exists(String id) {
		if (!userData.containsKey(id))
			throw new DataNotFoundException("User Id [" + id + "] not exists in database");
		return userData.containsKey(id);
	}

	/**
	 * <p>
	 * This method return the value which is deleted from data
	 * </p>
	 * 
	 * @param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel delete(String id) {
		if (!userData.containsKey(id))
			throw new DataNotFoundException("User Id [" + id + "] not exists in database");
		return userData.remove(id);
	}

	// Double check locking singleton pattern
	public static UserDaoImpl getInstance() {
		if (Objects.isNull(userDaoImpl)) {
			synchronized (UserDaoImpl.class) {
				if (Objects.isNull(userDaoImpl)) {
					userDaoImpl = new UserDaoImpl();
				}
			}
		}
		return userDaoImpl;
	}
}
