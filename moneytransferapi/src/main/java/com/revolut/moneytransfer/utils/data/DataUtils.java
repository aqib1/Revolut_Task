package com.revolut.moneytransfer.utils.data;

import java.math.BigDecimal;
import java.util.Currency;
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
	private volatile Map<String, UserModel> userData = new ConcurrentHashMap<>();
	private volatile Map<String, AccountModel> accountData = new ConcurrentHashMap<>();

	private DataUtils() {
		initUserData();
	}

	private void initUserData() {
		userData.put("u1",
				UserModel.builder().withAddress("H#1, ST#2, budapest").withCNIC("1231-211-2111")
						.withContactNumber("+93654555587").withEmail("u1@gmail.com")
						.withFirstName("USER").withLastName("Temp").withId("u1").build());
		userData.put("u2",
				UserModel.builder().withAddress("H#12, ST#11, budapest").withCNIC("9960-1110-2888")
						.withContactNumber("+97889451123").withEmail("u2@gmail.com")
						.withFirstName("TEST").withLastName("UMP").withId("u2").build());
		userData.put("u3",
				UserModel.builder().withAddress("H#8, ST#16, budapest").withCNIC("8888-3201-2888")
						.withContactNumber("+3647988852").withEmail("u3@gmail.com")
						.withFirstName("REMO").withLastName("PPM").withId("u3").build());
		accountData.put("a1",
				AccountModel.builder().withId("a1").withAccountTitle("account-A").withBalance(BigDecimal.valueOf(5000))
						.withCurrency(Currency.getInstance("USD")).withUserId("u1").build());
		accountData.put("a2",
				AccountModel.builder().withId("a2").withAccountTitle("account-B").withBalance(BigDecimal.valueOf(1500))
						.withCurrency(Currency.getInstance("USD")).withUserId("u2").build());
		accountData.put("a3",
				AccountModel.builder().withId("a3").withAccountTitle("account-C").withBalance(BigDecimal.valueOf(500))
						.withCurrency(Currency.getInstance("USD")).withUserId("u3").build());
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
