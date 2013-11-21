package com.infobip.campus.chattopush.exceptions;

public enum ErrorCode {
	PASSERROR(401, "Wrong password"), NOUSER(403, "User not found"), EXISTS(
			406, "User already exists"), EXC(409, "Conflict"), NORESPONSE(444,
			"No reponse"), CHANNEL_ALLREADY_EXISTS(406,
			"Channel allredy exists"), INTERNAL_SERVER_ERROR(500,
			"Internal Server Error"), CHANNEL_USER_EXISTS(406,
			"Channel/user allredy exists"), MISSING_REGISTRATION(476,
			"Missing registration"), WRONG_REGISTRATION_CODE(487,
			"Wrong registration");
	private final int value;

	private final String message;

	private ErrorCode(int value, String message) {
		this.value = value;
		this.message = message;
	}

	public int getValue() {
		return value;
	}

	public String getReason() {
		return message;
	}

}
