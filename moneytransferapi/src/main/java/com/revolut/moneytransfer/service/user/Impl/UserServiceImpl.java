package com.revolut.moneytransfer.service.user.Impl;

import java.util.List;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.utils.Helper;

/**
 * <p>
 * User service implementation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 * 
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	/**
	 * <p>
	 * User DAO injection using dagger2
	 * </p>
	 * 
	 * @param userDao
	 */
	@Inject
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * <p>
	 * Get all users
	 * </p>
	 * 
	 * @return {@link List<UserModel>}
	 */
	@Override
	public List<UserModel> getAll() {
		return userDao.getAll();
	}

	/**
	 * <p>
	 * Get user by its id
	 * </p>
	 * 
	 * @Param id
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel getById(String id) throws IllegalArgumentException {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		return userDao.getById(id);
	}

	/**
	 * <p>
	 * create method will create and send the newly created user
	 * </p>
	 * 
	 * @param user
	 * @return {@link UserModel}
	 */
	@Override
	public UserModel create(UserModel user) {
		return userDao.create(user);
	}

	@Override
	public UserModel update(UserModel user) {
		return userDao.update(user);
	}

	@Override
	public boolean exists(String id) {
		return userDao.exists(id);
	}

	// Delete user by id, return deleted User
	@Override
	public UserModel delete(String id) {
		return userDao.delete(id);
	}

}
