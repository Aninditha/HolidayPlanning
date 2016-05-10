package com.ssdi.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ssdi.POJO.userbean;
import com.ssdi.model.databaseFactory;
import com.ssdi.model.ServicesDao;

/**
 * Servlet implementation class Register
 */

@WebServlet("/Register")

public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;
	private RequestDispatcher rd = null;
	private HttpSession session = null;
	private userbean user = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Register(ServicesDao serviceDao2, RequestDispatcher rd, HttpSession session, userbean user) {
		this.serviceDao = serviceDao2;
		this.rd = rd;
		this.session = session;
		this.user = user;
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (user == null) {
			user = new userbean();
		}

		try {
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password1"));
			String email = user.getEmail();

			boolean exist = serviceDao.checkEmail(email);
			if (exist) {
				rd = request.getRequestDispatcher("/RegisterPageError.jsp");
				rd.forward(request, response);
			} else {
				user = serviceDao.registerUser(user);
				request.setAttribute("username", email);

				session = request.getSession();
				session.setAttribute("username", user.getEmail());

				rd = request.getRequestDispatcher("/RegisterRedirect.jsp");
				rd.forward(request, response);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}