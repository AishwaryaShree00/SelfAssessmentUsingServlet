package com.practice;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JavaServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String technology = request.getParameter("technology");
		Connection connection = null;
		ResultSet rs = null;
		Statement statement = null;
		int count=0;
		try {
			//loading the driver class
		Class.forName("org.postgresql.Driver");
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			System.out.println("ClassNotFound: "+e);
			System.exit(0);
		}
		try
		{
			//getting connection
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LearnServlets","postgres", "Password");
		
		statement = connection.createStatement();
		//retrieving data from DB
		rs=statement.executeQuery("SELECT ques_id,question,answer,count FROM "+technology+" ORDER BY RANDOM()"); 
		if ( rs.next() ) {
            int ques_id = rs.getInt("ques_id");
            count = rs.getInt("count");
           
            String  question = rs.getString("question");
            out.println("<h2>"+question+"</h2>");
            
            String answer  = rs.getString("answer");
            out.println("<h3>Ans. "+answer+"</h3>");
    	    
            // go back to index page
            out.println("<form action='index.html' method='post'>");
            out.println("<button type= 'submit'>Home Page</button>");
    	    out.println("</form>");
    	    
    	    	
		}
		rs.close();
		statement.close();
		connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error occured : "+e);
			System.exit(0);
		}
		
		System.out.println("Successful");

}
}