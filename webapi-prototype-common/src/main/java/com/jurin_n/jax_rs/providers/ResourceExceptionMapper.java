package com.jurin_n.jax_rs.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jurin_n.jax_rs.resources.ResourceException;

@Provider
public class ResourceExceptionMapper implements ExceptionMapper<ResourceException> {

	@Override
	public Response toResponse(ResourceException e) {
		//レスポンス
		return Response
				.status(e.getResponse().getStatus())
				.entity(
						  "code=" + e.getResponse().getErrorCode()
						+ ",message=" + e.getResponse().getMessage())
				.build(); 
	}
}
