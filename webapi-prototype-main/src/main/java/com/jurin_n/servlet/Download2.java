package com.jurin_n.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/servlet/download2")
public class Download2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final File file = new File("xxx.zip");
		OutputStream output = response.getOutputStream();
		
		InputStream input= new FileInputStream(file);
		response.setHeader("Content-Disposition", "attachment; filename=\"test.zip\"");
		writeOutput2(output,input);

		input.close();
	}
	
	private void writeOutput2(OutputStream output,InputStream is) throws IOException{
		OutputStream out = new BufferedOutputStream(output);
		InputStream in = new BufferedInputStream(is);
		
		int b;
		int i = 0;
		while ((b = in.read()) != -1)
		{
			System.out.println("writeOutput2[" + ++i + "]");
			out.write(b);
		}
		out.flush();
	}

}
