package com.jurin_n.jax_rs.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
//import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonListMarshaller implements MessageBodyWriter<List<BaseJsonMarshaller>> {

	@Override
	public long getSize(List<BaseJsonMarshaller> arg0, Class<?> type
						, Type genericType, Annotation[] annotation
						, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type
			, Type genericType
			, Annotation[] annotation
			, MediaType mediaType) {
		
		boolean isWritable;
		if (List.class.isAssignableFrom(type)
					    && genericType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericType;
			Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
			
			isWritable = (actualTypeArgs.length == 1
					&& actualTypeArgs[0].equals(BaseJsonMarshaller.class));
		} else {
			isWritable = false;
		}

		return isWritable;
	}

	@Override
	public void writeTo(List<BaseJsonMarshaller> target, Class<?> type, Type genericType
					, Annotation[] annotation, MediaType mediaType
					, MultivaluedMap<String, Object> httpHeaders
					, OutputStream outputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.writeValue(outputStream, target);
	}
}
