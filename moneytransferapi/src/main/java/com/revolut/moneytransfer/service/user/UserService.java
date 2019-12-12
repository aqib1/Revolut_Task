package com.revolut.moneytransfer.service.user;

import com.revolut.moneytransfer.dto.UserRequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;

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
	ResponseDto getAll();

	// Get user by id from DB
	ResponseDto getById(String id) throws IllegalArgumentException;

	// Create new user and returned newly created user
	ResponseDto create(UserRequestDto request);

	// Update user and return updated user
	ResponseDto update(UserRequestDto request);

	// Check is user exists by id
	ResponseDto exists(String id);

	// Delete user by id
	ResponseDto delete(String id);
}
