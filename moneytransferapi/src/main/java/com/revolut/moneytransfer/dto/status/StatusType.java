package com.revolut.moneytransfer.dto.status;

public enum StatusType {
	SUCCESS("Success"), ERROR("Error"), INFORMATIONAL("Information"), REDIRECT("Redirect");
	private String message;

	private StatusType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
