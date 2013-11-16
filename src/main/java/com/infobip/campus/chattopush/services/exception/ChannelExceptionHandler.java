package com.infobip.campus.chattopush.services.exception;

import com.infobip.campus.chattopush.exceptions.ErrorCode;



public class ChannelExceptionHandler extends Exception {

	ErrorCode error;

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
