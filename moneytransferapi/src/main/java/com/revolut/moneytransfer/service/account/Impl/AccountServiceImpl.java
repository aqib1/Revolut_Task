package com.revolut.moneytransfer.service.account.Impl;

import java.util.Collection;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.dto.requests.AccountRequestDto;
import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.service.account.AccountService;
import com.revolut.moneytransfer.utils.Helper;

/**
 * <p>
 * Account service implementation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 * 
 */
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	/**
	 * <p>
	 * Account DAO injection using dagger2
	 * </p>
	 * 
	 * @param accountDao
	 */
	@Inject
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * <p>
	 * Get all accounts
	 * </p>
	 * 
	 * @return {@link List<AccountModel>}
	 */
	@Override
	public ResponseDto getAll() {
		Collection<AccountModel> accounts = accountDao.getAll();
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Accounts recieved with length [%s]", accounts.size())
				.withData(accounts).build();
	}

	/**
	 * <p>
	 * Get account by its id
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 * @Param id
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto getById(String id) throws IllegalArgumentException {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("Account id can't be null or empty");
		}
		AccountModel model = accountDao.getById(id);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Account for ID [%s] found successfully", model.getId())
				.withData(model).build();
	}

	/**
	 * <p>
	 * create method will create and send the newly created account
	 * </p>
	 * 
	 * @param request
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto create(AccountRequestDto request) {
		Helper.validateAccount(request);
		AccountModel model = accountDao.create(request.getAccount());
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Account for ID [%s] created successfully", model.getId())
				.withData(model).build();

	}

	/**
	 * <p>
	 * Account update and return updated account
	 * </p>
	 * 
	 * @param request
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto update(AccountRequestDto request) {
		Helper.validateAccount(request);
		AccountModel model = accountDao.update(request.getAccount());
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Account for ID [%s] updated successfully", model.getId())
				.withData(model).build();
	}

	/**
	 * <p>
	 * check account exists against id
	 * </p>
	 * 
	 * @param id
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto exists(String id) {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("Account id can't be null or empty");
		}
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Account for id [%s] exists ", id).withData(accountDao.exists(id))
				.build();
	}

	/**
	 * <p>
	 * delete account against id
	 * </p>
	 * 
	 * @param id
	 * @return {@link ResponseDto}
	 */

	@Override
	public ResponseDto delete(String id) {
		if (Helper.isNullOrEmptyString(id)) {
			throw new IllegalArgumentException("Account id can't be null or empty");
		}
		AccountModel model = accountDao.delete(id);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Account for ID [%s] deleted successfully", model.getId())
				.withData(model).build();
	}

	/**
	 * <p>
	 * withdraw account
	 * </p>
	 * 
	 * @param withdrawRequestDto
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto withdraw(WithdrawRequestDto withdrawRequestDto) {
		Helper.validateWithDrawRequestDto(withdrawRequestDto);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Amount [%s] withdraw successfully", withdrawRequestDto.getAmount())
				.withData(accountDao.withDraw(withdrawRequestDto)).build();
	}

	/**
	 * <p>
	 * withdraw account
	 * </p>
	 * 
	 * @param deposit
	 * @return {@link ResponseDto}
	 */
	@Override
	public ResponseDto deposit(DepositRequest deposit) {
		Helper.validateDepositRequest(deposit);
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Amount [%s] deposit successfully", deposit.getAmount())
				.withData(accountDao.deposit(deposit)).build();
	}
}
