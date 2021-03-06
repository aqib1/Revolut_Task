package com.revolut.moneytransfer.dto.status;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 */
public enum StatusType {
	SUCCESS("Success"), ERROR("Error"), INFORMATIONAL("Information"), REDIRECT("Redirect");

	private String message;

	/**
	 * @param message
	 */
	private StatusType(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
