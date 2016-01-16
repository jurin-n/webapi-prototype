package com.jurin_n.jax_rs.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

@Path("/download")
public class Download {
	@GET
	@Produces("application/zip")
	public StreamingOutput get(){
		return new StreamingOutput(){
			@Override
			public void write(java.io.OutputStream os) throws IOException, WebApplicationException {
				InputStream is= new FileInputStream(
							new File("xxxx.zip"));
				byte[] buf = new byte[1000];
				for (int nChunk = is.read(buf); nChunk!=-1; nChunk = is.read(buf))
				{
				    os.write(buf, 0, nChunk);
				}
				is.close();
			}
		};
	}
}
