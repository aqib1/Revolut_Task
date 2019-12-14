package com.revolut.moneytransfer.unit.dao.trans;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revolut.moneytransfer.dao.trans.TransDao;
import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.TransResponseDto;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.utils.TestHelper;

/**
 * @author AQIB JAVED
 * @since 12/15/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class TransDaoUnitTest {

	@Mock
	private TransDao transDao;

	@Before
	public void init() {
		mockTransfer();
	}

	private void mockTransfer() {
		Mockito.when(transDao.transfer(Mockito.any(TransRequestDto.class)))
				.thenReturn(TestHelper.getTransResponse());
	}

	@Test
	public void testTransfer() {
		TransResponseDto resposne = transDao.transfer(TestHelper.getTransRequest());
		Mockito.verify(transDao, Mockito.times(1)).transfer(Mockito.any(TransRequestDto.class));
		assertEquals(resposne.getReciever().getAccountTitle(), "Test-Account");
		assertEquals(resposne.getReciever().getId(), "ac11");
		assertEquals(resposne.getReciever().getUserId(), "1");
		assertEquals(resposne.getSender().getAccountTitle(), "Test-Account1");
		assertEquals(resposne.getSender().getId(), "ac22");
		assertEquals(resposne.getSender().getUserId(), "1");
	}

	private void mockTransferInvalidAmountException() {
		Mockito.when(transDao.transfer(Mockito.any(TransRequestDto.class)))
				.thenThrow(new InvalidAmountException());
	}

	@Test(expected = InvalidAmountException.class)
	public void testTransferInvalidAmountException() {
		mockTransferInvalidAmountException();
		transDao.transfer(TestHelper.getTransRequest());
		Mockito.verify(transDao, Mockito.times(1)).transfer(Mockito.any(TransRequestDto.class));
	}
}
