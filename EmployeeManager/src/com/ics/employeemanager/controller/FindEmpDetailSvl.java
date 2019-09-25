package com.ics.employeemanager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.DeptBiz;
import com.ics.employeemanager.diz.EmployeeBiz;
import com.ics.employeemanager.diz.JobBiz;
import com.ics.employeemanager.entity.DepEntity;
import com.ics.employeemanager.entity.EmployeeEntity;
import com.ics.employeemanager.entity.JobEntity;

/**
 * Servlet implementation class FindEmpDetailSvl
 */
public class FindEmpDetailSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindEmpDetailSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收数据——empid
		String empid=request.getParameter("empid");
		//2.根据用户id查看用户详细信息
		EmployeeBiz empbiz=new EmployeeBiz();
		EmployeeEntity emp=empbiz.findEmpDetail(Integer.parseInt(empid));
		//3.所有职位和部门
		
		JobBiz jobbiz = new JobBiz();
		ArrayList<JobEntity> jobs = jobbiz.getAllJob();
		
		DeptBiz depbiz = new DeptBiz();
		ArrayList<DepEntity> deps = depbiz.getAllDep();
		
		request.setAttribute("deps", deps);
		request.setAttribute("jobs", jobs);
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("employee/showUpdateEmployee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
