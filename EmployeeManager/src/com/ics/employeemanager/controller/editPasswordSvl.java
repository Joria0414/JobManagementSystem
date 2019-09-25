package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ics.employeemanager.diz.EmployeeBiz;

/**
 * Servlet implementation class editPasswordSvl
 */
public class editPasswordSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPasswordSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newpassword=request.getParameter("newPassword");
		String oldpassword=request.getParameter("oldPassword");
		HttpSession session=request.getSession();
		int empid=(Integer) session.getAttribute("empid");
		/*System.out.println(newpassword);
		System.out.println(empid);
		System.out.println(oldpassword);*/
		EmployeeBiz biz=new EmployeeBiz();
		boolean result=biz.editPassword(empid,newpassword,oldpassword);
		response.getWriter().println(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
