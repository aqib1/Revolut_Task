package com.revolut.moneytransfer.models;

import java.util.Objects;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserModel {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String CNIC; // national identity number
	private String address;
	private String contactNumber;

	private UserModel(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.CNIC = builder.CNIC;
		this.address = builder.address;
		this.contactNumber = builder.contactNumber;
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
		if (Objects.isNull(obj) || !(obj instanceof UserModel))
			return false;
		// Reflexive
		if (this == obj)
			return true;
		UserModel userModel = (UserModel) obj;
		return userModel.CNIC.equals(this.CNIC) && userModel.email.equals(this.email);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return {@link Integer}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(CNIC, email);
	}

	public static class Builder {
		private String id;
		private String firstName;
		private String lastName;
		private String email;
		private String CNIC; // national identity number
		private String address;
		private String contactNumber;

		private Builder() {

		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withCNIC(String CNIC) {
			this.CNIC = CNIC;
			return this;
		}

		public Builder withAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder withContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
			return this;
		}
		
		public UserModel build() {
			return new UserModel(this);
		}
	}

}
