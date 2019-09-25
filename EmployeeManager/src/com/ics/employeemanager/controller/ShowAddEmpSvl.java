package com.ics.employeemanager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ics.employeemanager.diz.DeptBiz;
import com.ics.employeemanager.diz.JobBiz;
import com.ics.employeemanager.entity.DepEntity;
import com.ics.employeemanager.entity.JobEntity;

/**
 * Servlet implementation class ShowAddEmpSvl
 */
public class ShowAddEmpSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAddEmpSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JobBiz jobbiz = new JobBiz();
		ArrayList<JobEntity> jobs = jobbiz.getAllJob();
		
		DeptBiz depbiz = new DeptBiz();
		ArrayList<DepEntity> deps = depbiz.getAllDep();
		
		request.setAttribute("deps", deps);
		request.setAttribute("jobs", jobs);
		
		request.getRequestDispatcher("employee/showAddEmployee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
