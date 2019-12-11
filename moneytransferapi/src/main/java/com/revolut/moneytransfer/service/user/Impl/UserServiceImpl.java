package com.revolut.moneytransfer.service.user.Impl;

import java.util.List;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.dto.RequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
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
	public ResponseDto getById(String id) throws IllegalArgumentException {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		UserModel model = userDao.getById(id);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User [%s] deleted successfully", model).withData(model).build();
	}

	/**
	 * <p>
	 * create method will create and send the newly created user
	 * </p>
	 * 
	 * @param request
	 * @return {@link UserModel}
	 */
	@Override
	public ResponseDto create(RequestDto request) {
		Helper.validateUserForCreation(request);
		UserModel user = userDao.create(request.getUser());
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User [%s] created successfully", user).withData(user).build();
	}

	@Override
	public ResponseDto update(RequestDto request) {
		Helper.validateUserForUpdation(request);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User [%s] updated successfully", userDao.update(request.getUser())).build();
	}

	@Override
	public boolean exists(String id) {
		return userDao.exists(id);
	}

	// Delete user by id, return deleted User
	@Override
	public ResponseDto delete(String id) {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User [%s] deleted successfully", userDao.delete(id)).build();
	}

}
