package com.revolut.moneytransfer.service.user;

import java.util.List;

import com.revolut.moneytransfer.dto.RequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;
import com.revolut.moneytransfer.models.UserModel;

/**
 * <p>
 * User-service skeleton
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 */
public interface UserService {

	// Get-all user from DB
	List<UserModel> getAll();

	// Get user by id from DB
	UserModel getById(String id) throws IllegalArgumentException;

	// Create new user and returned newly created user
	ResponseDto create(RequestDto user);

	// Update user and return updated user
	UserModel update(UserModel user);

	// Check is user exists by id
	boolean exists(String id);

	// Delete user by id
	UserModel delete(String id);
}
