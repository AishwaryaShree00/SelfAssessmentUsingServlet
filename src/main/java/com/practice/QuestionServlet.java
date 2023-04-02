package com.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String name = req.getParameter("user_name");
		String technology = req.getParameter("technology");
		
		
		PrintWriter out=resp.getWriter();
		out.println("<h2 align=\"center\">You have selected "+ technology.toUpperCase() +"</h2>");
		
		  RequestDispatcher rd;
		  rd = req.getRequestDispatcher("JavaServlet"); 
		  rd.include(req, resp); 
				
	}
}