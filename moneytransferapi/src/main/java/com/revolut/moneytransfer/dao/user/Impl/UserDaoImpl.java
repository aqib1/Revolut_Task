package com.revolut.moneytransfer.dao.user.Impl;

import java.util.List;
import java.util.Objects;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.models.UserModel;

public class UserDaoImpl implements UserDao {
	private static UserDaoImpl userDaoImpl = null;

	private UserDaoImpl() {

	}

	@Override
	public List<UserModel> getAll() {
		return null;
	}

	@Override
	public UserModel getById(String id) {
		return null;
	}

	@Override
	public UserModel create(UserModel user) {
		return null;
	}

	@Override
	public UserModel update(UserModel user) {
		return null;
	}

	@Override
	public boolean exists(String id) {
		return false;
	}

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
