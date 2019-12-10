package com.revolut.moneytransfer.service.user.Impl;

import java.util.List;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.models.UserModel;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.utils.Helper;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Inject
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserModel> getAll() {
		return userDao.getAll();
	}

	//Get user by its id
	@Override
	public UserModel getById(String id) throws IllegalArgumentException {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		return userDao.getById(id);
	}

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
