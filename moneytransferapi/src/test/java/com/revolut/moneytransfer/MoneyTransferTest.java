package com.revolut.moneytransfer;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author AQIB JAVED
 * @since 12/14/2019
 * @version 1.0
 */
public class MoneyTransferTest {
	/**
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void contextLoads() throws NoSuchMethodException, SecurityException {
		String methodName = "main";
		Class<?> c = MoneyTransfer.class;
		Method method = c.getDeclaredMethod(methodName, String[].class);
		Assert.assertNotNull(method);
	}
}
