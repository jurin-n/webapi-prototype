package com.jurin_n.servlet;

import static org.junit.Assert.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;

public class HelloServletTest {
	private HelloServlet sut;
	private HttpServletRequest request;
	//private ServletOutputStream output;
	private StringServletOutputStream output;
	private HttpServletResponse response;

	@Before
	public void createHelloServlet(){
		sut = new HelloServlet();
	}
	
	@Before
	public void createServletRequestAndResponseMock(){
		request  = mock(HttpServletRequest.class);
		//output   = mock(ServletOutputStream.class);
		output   = new StringServletOutputStream();
		response = mock(HttpServletResponse.class);		
	}

	@Test
	public void test() throws ServletException, IOException {
		when(request.getParameter("name")).thenReturn("JUnit");
		when(response.getOutputStream()).thenReturn(output);
		
		sut.doGet(request, response);
		
		//verify(output).println("Hello JUnit");
		assertThat(output.getOutput() ,is("Hello JUnit\r\n"));
		verify(response).setContentType("text/plain; charset=UTF-8");
		verify(response).flushBuffer();
	}
}
