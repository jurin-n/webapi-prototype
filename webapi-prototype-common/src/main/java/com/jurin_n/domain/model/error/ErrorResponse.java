package com.jurin_n.domain.model.error;

import javax.ws.rs.core.Response.Status;

public class ErrorResponse {
	private Status status;
	private String errorCode;
	private String message;
	
	public ErrorResponse(Status status, String errorCode, String message) {
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
}
