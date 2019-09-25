package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.JobBiz;

/**
 * Servlet implementation class UpdateJobSvl
 */
public class UpdateJobSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateJobSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post生效，修改编码模式
		//1.接收参数
		String jobid=request.getParameter("id");
		String jobname=request.getParameter("name");
		String jobdetail=request.getParameter("remark");
		//System.out.println(jobid);
		//System.out.println(depid+" "+depname+" "+depdetail);
		//2.创建业务层对象，调用方法，得到返回值
		JobBiz biz=new JobBiz();
		biz.updateDep(jobid,jobname,jobdetail);
		//3.请求转发到FindAllDepSvl
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
