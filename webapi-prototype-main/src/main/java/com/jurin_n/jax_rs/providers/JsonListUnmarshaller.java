package com.jurin_n.jax_rs.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jackson.map.ObjectMapper;

import com.jurin_n.domain.model.BaseEntity;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonListUnmarshaller implements MessageBodyReader<List<BaseEntity>> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType) {
		return type==List.class;
	}

	@Override
	public List<BaseEntity> readFrom(Class<List<BaseEntity>> type, Type genericType, Annotation[] annotation
					,MediaType mediaType, MultivaluedMap<String, String> httpHeaders
					,InputStream inputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputStream, List.class);
	}
}
