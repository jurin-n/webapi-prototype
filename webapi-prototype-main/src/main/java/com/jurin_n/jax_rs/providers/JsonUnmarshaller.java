package com.jurin_n.jax_rs.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.jackson.map.ObjectMapper;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonUnmarshaller implements MessageBodyReader<BaseJsonMarshaller> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType
			, Annotation[] annotation, MediaType mediaType) {
		for(int i=0 ;i < type.getInterfaces().length;i++){
			if(type.getInterfaces()[i].equals(BaseJsonMarshaller.class)){
				return true;
			}
		}
		return false;
	}

	@Override
	public BaseJsonMarshaller readFrom(Class<BaseJsonMarshaller> type, Type genericType, Annotation[] annotation
					,MediaType mediaType, MultivaluedMap<String, String> httpHeaders
					,InputStream inputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputStream,type);
	}
}
