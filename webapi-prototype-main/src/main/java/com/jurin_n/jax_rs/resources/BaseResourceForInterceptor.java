package com.jurin_n.jax_rs.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.jurin_n.domain.model.identity.AuthenticationTypes;

@DefaultProcess
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaseResourceForInterceptor {
	@Context HttpHeaders headers;
	AuthenticationTypes selectedAuthenticationType = AuthenticationTypes.Sha1Authentication;

	public Map<String, List<String>> getRequestHeaders() {
		Map<String, List<String>> returnValue = new HashMap<>();
		MultivaluedMap<String, String> multivaluedMap = headers.getRequestHeaders();
		
		for(String key : multivaluedMap.keySet()){
			returnValue.put(key, multivaluedMap.get(key));
		}
		return returnValue;
	}
	public AuthenticationTypes getSelectedAuthenticationType() {
		return selectedAuthenticationType;
	}
}
