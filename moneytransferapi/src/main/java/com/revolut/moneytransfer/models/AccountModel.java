package com.revolut.moneytransfer.models;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author AQIB JAVED
 * @since 12/13/2019
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class AccountModel {

	private String id;
	private String accountTitle;
	private volatile BigDecimal balance;
	private Currency currency;
	private String userId;

	/**
	 * @param builder
	 */
	private AccountModel(Builder builder) {
		this.id = builder.id;
		this.accountTitle = builder.accountTitle;
		this.balance = builder.balance;
		this.currency = builder.currency;
		this.userId = builder.userId;
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * <p>
	 * Overriding equals method and fulfilling contract with hashCode
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return {@link Boolean}
	 */
	@Override
	public boolean equals(Object obj) {
		// null comparison, non instance comparison
		if (Objects.isNull(obj) || !(obj instanceof AccountModel))
			return false;
		// Reflexive
		if (this == obj)
			return true;
		AccountModel accountModel = (AccountModel) obj;
		return accountModel.id.equals(this.id)
				&& accountModel.accountTitle.equals(this.accountTitle);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return {@link Integer}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, accountTitle);
	}

	public static class Builder {
		private String id;
		private String accountTitle;
		private BigDecimal balance;
		private Currency currency;
		private String userId;

		private Builder() {

		}

		/**
		 * @param id
		 * @return
		 */
		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * @param accountTitle
		 * @return
		 */
		public Builder withAccountTitle(String accountTitle) {
			this.accountTitle = accountTitle;
			return this;
		}

		/**
		 * @param balance
		 * @return
		 */
		public Builder withBalance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		/**
		 * @param currency
		 * @return
		 */
		public Builder withCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		/**
		 * @param userId
		 * @return
		 */
		public Builder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		/**
		 * @return
		 */
		public AccountModel build() {
			return new AccountModel(this);
		}
	}
}
