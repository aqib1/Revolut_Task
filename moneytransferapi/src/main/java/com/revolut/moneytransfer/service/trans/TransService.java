package com.revolut.moneytransfer.service.trans;

import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;

/**
 * <p>
 * Transaction-service skeleton
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */
public interface TransService {

	ResponseDto transfer(TransRequestDto request);
}
