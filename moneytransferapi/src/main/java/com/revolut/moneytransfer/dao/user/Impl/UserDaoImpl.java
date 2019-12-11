package com.revolut.moneytransfer.dao.user.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.models.UserModel;

/**
 * =====Database information==============
 * <p>
 * This UserDaoImpl class is used to provide user details and IO operation
 * against data. For the simplicity of task as it mentioned in requirement II i am
 * not using any database instead of that i am using map, in future we can
 * replace that structure with actual database
 * </p>
 * =========Thread safety information=======
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
 * we can also use java 1.7 ReentrantLock @see {@link ReentrantLock}
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 * 
 */
public class UserDaoImpl implements UserDao {
	private volatile static UserDaoImpl userDaoImpl = null;
	private Map<String, UserModel> userData = new ConcurrentHashMap<>();

	private UserDaoImpl() {

	}

	/**
	 * @return {@link List<UserModel>}
	 */
	@Override
	public List<UserModel> getAll() {
		return Arrays.asList(UserModel.builder().withCNIC("1234").build());
	}

	/**
	 * @param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel getById(String id) {
		return UserModel.builder().withCNIC("1234").build();
	}

	/**
	 * @param user
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel create(UserModel user) {

		return user;
	}

	/**
	 * @param user
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel update(UserModel user) {
		return null;
	}

	/**
	 * @param id
	 * @return {@link Boolean}
	 */
	@Override
	public boolean exists(String id) {
		return false;
	}

	/**
	 * @param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel delete(String id) {
		return null;
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
