package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.JobBiz;

/**
 * Servlet implementation class DelJobSvl
 */
public class DelJobSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelJobSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收页面参数
		String[] ids = request.getParameterValues("ids");
		// 2.创建业务层对象，调用方法
		JobBiz biz = new JobBiz();
		biz.delJob(ids);
		// 3.请求转发到FindAllDepSvl
		request.getRequestDispatcher("FindAllJobtSvl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
