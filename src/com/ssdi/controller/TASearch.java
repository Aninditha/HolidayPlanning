package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ssdi.POJO.taBean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

/**
 * Servlet implementation class TASearch
 */

@WebServlet("/TASearch")

public class TASearch extends HttpServlet {
	
	static List<taBean> taList = new ArrayList<>();
	private ServicesDao serviceDao;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public TASearch() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean Exist = false;
		
		try{
			taBean location = new taBean();
		    location.setLocation(request.getParameter("country"));
		    String country = location.getLocation();
		    Exist = serviceDao.checkCountry(country);
		    taList = serviceDao.searchRegions(country);
		    
		    if(Exist){
		    	request.setAttribute("taList",taList);
		    	System.out.println(request.getSession().getAttribute("username"));
				if (request.getSession().getAttribute("username") != null){
					System.out.println("jhgf");
					RequestDispatcher rd = request.getRequestDispatcher("/taSearchList_user.jsp");
					rd.forward(request,response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/taSearchList.jsp");
					rd.forward(request,response);
				}
		    }
		    else{
		    	if (request.getSession().getAttribute("username") != null){
					RequestDispatcher rd = request.getRequestDispatcher("/taError_user.jsp");
					rd.forward(request,response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/taError.jsp");
					rd.forward(request,response);
				}
		    }
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}