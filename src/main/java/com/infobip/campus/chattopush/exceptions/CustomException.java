package com.infobip.campus.chattopush.exceptions;


public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorCode error;
	
	public CustomException(ErrorCode error) {
		this.error = error;
	}
	
	public int getErrorCode() {
		return error.getValue();
	}
	
	public String getErrorMessage() {
		return error.getReason();
	}

}
