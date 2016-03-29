package com.ssdi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ssdi.POJO.taBean;
import com.ssdi.model.taModel;

/**
 * Servlet implementation class TASearch
 */
@WebServlet("/TASearch")
public class TASearch extends HttpServlet {
	static List<taBean> taList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TASearch() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		try{
			taBean location = new taBean();
		    location.setLocation(request.getParameter("location"));
		    String givenLocation = location.getLocation();
		    taList = taModel.search(givenLocation);
		    System.out.println("size in controller: "+taList.size());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("taList",taList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/taSearchList.jsp");
		rd.forward(request,response);
		
		
	}

}
