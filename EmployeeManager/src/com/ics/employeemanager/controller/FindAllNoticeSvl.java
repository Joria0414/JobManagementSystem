package com.ics.employeemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.NoticeBiz;
import com.ics.employeemanager.entity.NoticeEntity;
import com.ics.employeemanager.utils.ResultPage;

/**
 * Servlet implementation class FindAllNoticeSvl
 */
public class FindAllNoticeSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllNoticeSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		//1.获取公告名称，公告内容，公告人，创建时间
		String current=request.getParameter("current");
		String noticename=request.getParameter("title");
		String noticecontent=request.getParameter("content");
		String noticeempname=request.getParameter("empname");
		//String day=request.getParameter("day");	
		int currentPage=1;//当前页默认是第一页
		if(current!=null&&!"".equals(current)){
			currentPage=Integer.parseInt(current);//类型转换，String-int			
		}
		NoticeBiz biz=new NoticeBiz();
		ResultPage<NoticeEntity> pageInfo=new ResultPage<NoticeEntity>();//创建工具类对象
		pageInfo.setCurrentPage(currentPage);
		biz.findAllNotice(pageInfo,noticename,noticecontent,noticeempname);
		//2.将列表保存到请求域
		request.setAttribute("pageinfo",pageInfo);
		//3.请求转发
		request.getRequestDispatcher("notice/notice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
