package com.ssdi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssdi.POJO.adminBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class Admin_LoginServlet
 */

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		ServletContext context = getServletContext();
		databaseFactory factory = databaseFactory.getInstance(context.getInitParameter("environment"));
		serviceDao = factory.createServiceDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		adminBean adminBean = new adminBean();
		adminBean.setEmail(username);
		adminBean.setPassword(password);

		serviceDao.isValidAdmin(adminBean);

		RequestDispatcher rd = null;
		if (serviceDao.isValidAdmin(adminBean)) {
			System.out.println("Valid");
			rd = request.getRequestDispatcher("/adminHome");
			rd.include(request, response);
		} else {
			System.out.println("Error");
			request.setAttribute("loginError", "Invalid Credentials!! Please enter valid username or password.");
			rd = request.getRequestDispatcher("/AdminLogin.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
