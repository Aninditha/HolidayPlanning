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
import javax.servlet.http.HttpSession;
import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class LogIn
 */

@WebServlet("/LogIn")

public class LogIn extends HttpServlet {
	
	boolean Exist = false;
	String username;
	
	private static final long serialVersionUID = 1L;
	private ServicesDao serviceDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public LogIn(ServicesDao serviceDao2, RequestDispatcher rd, HttpSession session, userbean user) {
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		ServletContext context = getServletContext();
		databaseFactory factory = databaseFactory.getInstance(context.getInitParameter("environment"));
		serviceDao = factory.createServiceDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userbean User = new userbean();
		try{
		    User.setUsername(request.getParameter("username"));
		    User.setPassword(request.getParameter("password"));
		    username = User.getUsername();
		    String password = User.getPassword();
		    Exist = serviceDao.logIn(username, password);
		} catch(Exception e){
			e.printStackTrace();
		}
		if(Exist){
			HttpSession Session = request.getSession();
			Session.setAttribute("username", User.getUsername());
			
			RequestDispatcher rd = request.getRequestDispatcher("/home_user.jsp");
			rd.forward(request,response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/LoginPageError.jsp");
			rd.forward(request,response);
		}
	}
}
