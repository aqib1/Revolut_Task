package com.revolut.moneytransfer.dao.user;

import java.util.Collection;

import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.models.UserModel;

/**
 * <p>
 * User-DAO skeleton
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 */
public interface UserDao {
	// Get-all user from DB
	Collection<UserModel> getAll();

	// Get user by id from DB
	UserModel getById(String id);

	// Create new user and returned newly created user
	UserModel create(UserModel user) throws DataDuplicationException;

	// Update user and return updated user
	UserModel update(UserModel user) throws DataNotFoundException;

	// Check is user exists by id
	boolean exists(String id) throws DataNotFoundException;

	// Delete user by id
	UserModel delete(String id) throws DataNotFoundException;
}
