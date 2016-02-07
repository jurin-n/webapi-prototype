package com.jurin_n.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class StringServletOutputStream extends ServletOutputStream {
	final StringBuilder out = new StringBuilder();

	@Override
	public void write(int b) throws IOException {
		out.append((char)b);
	}
	
	public String getOutput(){
		return out.toString();
	}
}
