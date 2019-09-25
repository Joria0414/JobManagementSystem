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
 * 搜索员工信息
 * Servlet implementation class SearchEmpSvl
 */
public class SearchEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.接受6个参数
		String jobid=request.getParameter("job_id");
		String empname=request.getParameter("name");
		String sex=request.getParameter("sex");
		String depid=request.getParameter("dept_id");
		String phone=request.getParameter("phone");
		String cardnumber=request.getParameter("cardId");
//		2.创建业务层对象，调用方法，得到一个满足搜索条件的列表
		EmployeeBiz biz = new EmployeeBiz();
		ArrayList<EmployeeEntity> emps = biz.searchEmp(jobid, empname, cardnumber, phone, depid, sex);

		DeptBiz depbiz = new DeptBiz();
		ArrayList<DepEntity> deps = depbiz.getAllDep();

		JobBiz jobbiz = new JobBiz();
		ArrayList<JobEntity> jobs = jobbiz.getAllJob();
/*
		int nowjob=0;
		if(!"0".equals(jobid))
		{
			nowjob=Integer.parseInt(jobid);
		}
		request.setAttribute("jobid", jobid);
		*/
		request.setAttribute("deps", deps);
		request.setAttribute("jobs", jobs);
		request.setAttribute("emps", emps);
		request.getRequestDispatcher("employee/employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
