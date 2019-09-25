package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ics.employeemanager.diz.JobBiz;

/**
 * Servlet implementation class AddJobSvl
 */
public class AddJobSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddJobSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收页面参数
		String jobname = request.getParameter("jobname");
		String jobdetail = request.getParameter("jobdetail");
		// System.out.println(depname+" "+depdetail);
		// 2.调用biz方法
		JobBiz biz = new JobBiz();
		boolean result = biz.insertJob(jobname, jobdetail);
		// 3.响应数据
		response.getWriter().print(result);
		//request.getRequestDispatcher("FindExactEmpSvl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
