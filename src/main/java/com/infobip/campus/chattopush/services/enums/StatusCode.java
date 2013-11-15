package com.infobip.campus.chattopush.services.enums;

public enum StatusCode {

	SUCCESS(200, "Success"),
	/**
     */
	PASSERROR(401, "Wrong password"),
	/**
     */
	NOUSER(403, "User not found"),
	/**
     */
	EXISTS(406, "User already exists"),
	/**
     */
	EXC(409, "Conflict"),
	/**
     */
	NORESPONSE(444, "No reponse"),
	/**
     */
	INTERNALSERVERERROR(500, "Internal Server Error");
	/**
	 * 
	 */

	private final int value;

	private final String reason;

	private StatusCode(int value, String _reason) {
		this.value = value;
		this.reason = _reason;
	}

	public int getValue() {
		return value;
	}

	public String getReason() {
		return reason;
	}

}
