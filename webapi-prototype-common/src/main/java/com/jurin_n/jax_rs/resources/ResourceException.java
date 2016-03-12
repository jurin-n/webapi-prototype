package com.jurin_n.jax_rs.resources;

import com.jurin_n.domain.model.error.ErrorResponse;

public class ResourceException extends RuntimeException {

	private ErrorResponse response;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1738911474747084937L;
	
	
	public ResourceException(ErrorResponse response) {
		this.response = response;
	}

	public ErrorResponse getResponse() {
		return response;
	}
}
