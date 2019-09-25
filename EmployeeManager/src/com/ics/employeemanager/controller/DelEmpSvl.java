package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ics.employeemanager.diz.EmployeeBiz;

/**
 * Servlet implementation class DelEmpSvl
 */
public class DelEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelEmpSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收参数
		String[] empids=request.getParameterValues("empids");
		//System.out.println(empids);
		//2.创建对象 调用方法
		//HttpSession session=request.getSession();
		//Integer loginEmpid=(Integer)session.getAttribute("empid");//从session中把值取出
		EmployeeBiz biz=new EmployeeBiz();
		biz.delEmp(empids);
		//3.请求转发
		request.getRequestDispatcher("FindAllEmpSvl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
