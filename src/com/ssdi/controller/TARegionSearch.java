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
 * Servlet implementation class TARegionSearch
 */
@WebServlet("/TARegionSearch")
public class TARegionSearch extends HttpServlet {
	static List<taBean> taList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TARegionSearch() {
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
		
		boolean Exist = false;
		
		try{
			taBean location = new taBean();
		    location.setLocation(request.getParameter("regionName"));
		    String region = location.getLocation();
		    Exist = taModel.checkRegion(region);
		    taList = taModel.searchAttractions(region);
		    if(Exist){
		    	request.setAttribute("taList",taList);
				if (request.getSession().getAttribute("username") != null){
					RequestDispatcher rd = request.getRequestDispatcher("/taAttractionList_user.jsp");
					rd.forward(request,response);
				}
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/taAttractionList.jsp");
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
