package com.revolut.moneytransfer.utils.data;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.revolut.moneytransfer.models.AccountModel;
import com.revolut.moneytransfer.models.UserModel;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * This class will be hold data/act like a database
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 * 
 */

@Getter
@ToString
public class DataUtils {
	// make it volatile to avoid thread cached visibility issues
	private static volatile DataUtils dataUtils = null;
	private Map<String, UserModel> userData = new ConcurrentHashMap<>();
	private Map<String, AccountModel> accountData = new ConcurrentHashMap<>();
	
	private DataUtils() {

	}

	public static DataUtils getInstance() {
		if (Objects.isNull(dataUtils))
			synchronized (DataUtils.class) {
				if (Objects.isNull(dataUtils))
					dataUtils = new DataUtils();
			}
		return dataUtils;
	}
}
