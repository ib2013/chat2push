package com.infobip.campus.chattopush.services.exception;

import com.infobip.campus.chattopush.services.enums.StatusCode;

public class ChannelExceptionHandler extends Exception {

	StatusCode error;

	public ChannelExceptionHandler(String e) {
		super(e);
	}

	public int getErrorCode() {
		return error.getValue();
	}

	public String getErrorMessage() {
		return error.getReason();
	}
}
