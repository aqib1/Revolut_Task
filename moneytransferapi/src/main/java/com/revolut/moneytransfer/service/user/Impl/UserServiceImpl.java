package com.revolut.moneytransfer.service.user.Impl;

import java.util.Collection;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.dto.UserRequestDto;
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
	public ResponseDto getAll() {
		Collection<UserModel> users = userDao.getAll();
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Users recieved with length [%s]", users.size()).withData(users).build();
	}

	/**
	 * <p>
	 * Get user by its id
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 * @Param id
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto getById(String id) throws IllegalArgumentException {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		UserModel model = userDao.getById(id);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User for ID [%s] found successfully", model.getId()).withData(model).build();
	}

	/**
	 * <p>
	 * create method will create and send the newly created user
	 * </p>
	 * 
	 * @param request
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto create(UserRequestDto request) {
		Helper.validateUserForCreation(request);
		UserModel user = userDao.create(request.getUser());
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User for ID [%s] created successfully", user.getId()).withData(user).build();
	}

	/**
	 * <p>
	 * User update and return updated user
	 * </p>
	 * 
	 * @param request
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto update(UserRequestDto request) {
		Helper.validateUserForUpdation(request);
		UserModel user = userDao.update(request.getUser());
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User for ID [%s] updated successfully", user.getId()).withData(user).build();
	}

	/**
	 * <p>
	 * check user exists against id
	 * </p>
	 * 
	 * @param id
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto exists(String id) {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS).withMessage("User for id [%s] exists ", id)
				.withData(userDao.exists(id)).build();
	}

	/**
	 * <p>
	 * delete user against id
	 * </p>
	 * 
	 * @param id
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto delete(String id) {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("User id can't be null or empty");
		}
		UserModel user = userDao.delete(id);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("User for ID [%s] deleted successfully", user.getId()).withData(user).build();
	}

}
