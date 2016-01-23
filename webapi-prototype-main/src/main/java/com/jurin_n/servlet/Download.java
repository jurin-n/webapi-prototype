package com.jurin_n.servlet;

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
@WebServlet("/servlet/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
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
		writeOutput(output,input);

		input.close();
	}
	
	private void writeOutput(OutputStream output,InputStream is) throws IOException{
		byte[] buf = new byte[1000];
		int i = 0;
		for (int nChunk = is.read(buf); nChunk!=-1; nChunk = is.read(buf))
		{
			System.out.println("writeOutput[" + ++i + "]");
			output.write(buf, 0, nChunk);
		}
	}
}
