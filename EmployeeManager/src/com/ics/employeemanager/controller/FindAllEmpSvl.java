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
 * 查询员工的所有信息
 * Servlet implementation class FindAllEmpSvl
 */
public class FindAllEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllEmpSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取所有部门的列表
		DeptBiz depbiz =new DeptBiz();
		ArrayList<DepEntity> deps=depbiz.getAllDep();
		//2.获取所有员工列表
		EmployeeBiz empbiz=new EmployeeBiz();
		ArrayList<EmployeeEntity> emps=empbiz.findAllEmp();
		//3.获取所有职位列表
		JobBiz jobbiz =new JobBiz();
		ArrayList<JobEntity> jobs=jobbiz.getAllJob();
		//4.把所有的列表保存到请求域
		request.setAttribute("deps", deps);
		request.setAttribute("jobs", jobs);
		request.setAttribute("emps", emps);
		//System.out.println(deps);
		//System.out.println(jobs);
		//System.out.println(emps);
		//5.请求转发employee.jsp
		request.getRequestDispatcher("employee/employee.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
