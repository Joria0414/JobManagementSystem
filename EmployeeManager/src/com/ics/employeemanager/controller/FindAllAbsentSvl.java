package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.ApplyBiz;
import com.ics.employeemanager.entity.ApplyEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllAbsentSvl
 */
public class FindAllAbsentSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllAbsentSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String applytype=request.getParameter("applytype");
		String empname=request.getParameter("name");
		String applystatus=request.getParameter("applystatus");
		String current=request.getParameter("current");
		int currentPage=1;//当前页默认是第一页
		if(current!=null&&!"".equals(current)){
			currentPage=Integer.parseInt(current);//类型转换，String-int			
		}
		ApplyBiz biz=new ApplyBiz();
		ResultPage<ApplyEntity> pageInfo=new ResultPage<ApplyEntity>();
		pageInfo.setCurrentPage(currentPage);
		
		biz.FindAllAbsent(pageInfo,applytype,empname,applystatus);
		//System.out.println(pageInfo+" "+applytype+" "+empname+" "+applystatus);
		request.setAttribute("pageinfo", pageInfo);
		if(Integer.parseInt(applytype)==1){
			request.getRequestDispatcher("apply/Askapplyq.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("apply/Askapplyw.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
