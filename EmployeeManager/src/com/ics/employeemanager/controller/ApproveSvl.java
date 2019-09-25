package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.ApplyBiz;

/**
 * Servlet implementation class ApproveSvl
 */
public class ApproveSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids=request.getParameterValues("ids");
		String applytype=request.getParameter("applytype");
		/*String applystatus=request.getParameter("applystatus");
		String name=request.getParameter("name");*/
		ApplyBiz biz=new ApplyBiz();
		HttpSession session=request.getSession();
		int approverid=(Integer) session.getAttribute("empid");
		biz.ApproveSvl(ids,approverid);
		request.setAttribute("applytype", applytype);
		/*request.setAttribute("name", name);
		request.setAttribute("applystatus", applystatus);*/
		request.getRequestDispatcher("FindAllAbsentSvl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
