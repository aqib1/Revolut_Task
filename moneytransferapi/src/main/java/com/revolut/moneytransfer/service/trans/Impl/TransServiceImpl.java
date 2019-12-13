package com.revolut.moneytransfer.service.trans.Impl;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.trans.TransDao;
import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.service.trans.TransService;
/**
 * <p>
 * Transaction service implementation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */
public class TransServiceImpl implements TransService{

	private TransDao transDao;
	
	@Inject
	public TransServiceImpl(TransDao transDao) {
		this.transDao = transDao;
	}
	@Override
	public ResponseDto transfer(TransRequestDto request) {
		return ResponseDto.builder().withStatusType(StatusType.SUCCESS)
				.withMessage("Amount transfer successfully")
				.withData(transDao.transfer(request)).build();
	}

}
