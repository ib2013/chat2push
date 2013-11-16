package com.infobip.campus.chattopush.services.exceptions;

import com.infobip.campus.chattopush.services.enums.StatusCode;

public class CustomException extends RuntimeException{

	StatusCode error;
	
	public CustomException(StatusCode error) {
		this.error = error;
	}
	
	public int getErrorCode() {
		return error.getValue();
	}
	
	public String getErrorMessage() {
		return error.getReason();
	}
}
