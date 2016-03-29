package com.ssdi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssdi.POJO.userbean;
import com.ssdi.model.RegisterModel;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 userbean user = new userbean();
		
		try
		   {      

			
		     user.setUsername(request.getParameter("username"));
		     user.setEmail(request.getParameter("email"));
		     user.setPassword(request.getParameter("password"));
		     user = RegisterModel.login(user);
		     
		   }
		                 
		      
		catch (Throwable theException)       
		{
		     System.out.println(theException);
		} 
		
		
	
		request.setAttribute("username",user.getUsername());
		
		RequestDispatcher rd = request.getRequestDispatcher("/RegisterRedirect.jsp");
		rd.forward(request,response);
		
		
		//CheckPasswordReq(request.getParameter("password"));
	
		 
	System.out.println("register success, chec db");
	
	
	
	
		 
		
	}

	private void CheckPasswordReq(String password) {
		
	
		
	}
	
	

}
