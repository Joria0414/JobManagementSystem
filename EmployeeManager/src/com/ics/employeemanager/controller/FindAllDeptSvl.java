package com.ics.employeemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.DeptBiz;
import com.ics.employeemanager.entity.DepEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * 查看所有部门
 * Servlet implementation class FindAllDeptSvl
 */
public class FindAllDeptSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllDeptSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//①获取页面传递过来的参数——current（第一次访问的时候是没有的，so赋初值），可以有也可以没有                                                        
		String current=request.getParameter("current");
		int currentPage=1;//当前页默认是第一页
		//非空判断current有没有值，没有值的话，默认是第1页
		if(current!=null&&!"".equals(current)){
			currentPage=Integer.parseInt(current);//类型转换，String-int			
		}
		// ②创建biz对象，调用方法，得到返回值
		DeptBiz biz=new DeptBiz();
		ResultPage<DepEntity> pageInfo=new ResultPage<DepEntity>();//创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllDept(pageInfo);//传给biz
		//System.out.println(pageInfo);
		//4.把数据保存到请求域
		request.setAttribute("pageinfo",pageInfo);
		//5.请求转发
		request.getRequestDispatcher("dept/dept.jsp").forward(request, response);
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
