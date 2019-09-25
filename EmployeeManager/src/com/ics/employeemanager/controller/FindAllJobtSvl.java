package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.JobBiz;
import com.ics.employeemanager.entity.JobEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllJobtSvl
 */
public class FindAllJobtSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllJobtSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String jobname=request.getParameter("name");
//		1.获取页面传递过来的参数——current（第一次访问的时候是没有的，so赋初值），可以有也可以没有 
		String current=request.getParameter("current");
		int currentPage=1;//当前页默认是第一页
		if(current!=null&&!"".equals(current)){
			currentPage=Integer.parseInt(current);//类型转换，String-int			
		}
//		2.创建biz对象，调用方法，得到返回值
		JobBiz biz=new JobBiz();
		ResultPage<JobEntity> pageInfo=new ResultPage<JobEntity>();//创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllJob(pageInfo,jobname);//传给biz
		//System.out.println(pageInfo);
//		3.把数据保存到请求域
		request.setAttribute("pageinfo",pageInfo);
		request.setAttribute("jobname", jobname);
//		4.请求转发
		request.getRequestDispatcher("job/job.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
