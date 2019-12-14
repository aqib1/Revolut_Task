package com.revolut.moneytransfer.unit.service.trans;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.service.trans.TransService;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/15/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class TransServiceUnitTest {

	@Mock
	private TransService transService;

	@Before
	public void init() {
		Mockito.when(transService.transfer(Mockito.any(TransRequestDto.class)))
				.thenReturn(TestHelper.getResponse());
	}

	@Test
	public void testTransfer() {
		ResponseDto response = transService.transfer(TestHelper.getTransRequest());
		Mockito.verify(transService, Mockito.times(1)).transfer(Mockito.any(TransRequestDto.class));
		assertEquals(response.getStatusType(), StatusType.SUCCESS);
	}
}
