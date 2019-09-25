package com.ics.employeemanager.diz;
import java.util.ArrayList;

import com.ics.employeemanager.dao.EmployeeDao;
import com.ics.employeemanager.entity.EmployeeEntity;

/*
 * 业务层：所有和员工相关的业务逻辑处理---调用数据层进行业务处理
 */
public class EmployeeBiz {
	EmployeeDao dao=new EmployeeDao();//创建数据层对象
	//登录功能
	public EmployeeEntity login(String empname,String password){
		EmployeeEntity emp=null;
		try {
			emp=dao.login(empname, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//无论是否有异常，都会执行finally模块
			dao.closeConnection();
		}
		return emp;
	}
	//添加员工
	public int addEmployee(EmployeeEntity emp){
		int res=0;
		try {
			res=dao.addEmployee(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
		return res;
	}
	//查看所有员工
	public ArrayList<EmployeeEntity> findAllEmp(){
		ArrayList<EmployeeEntity> emp=null;
		try {
			emp=dao.findAllEmp();
			//int res=dao.getEmpCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return emp;
		
	}
	//查看员工详情
	public EmployeeEntity findEmpDetail(int empid){
		EmployeeEntity emp=null;
		try {
			emp=dao.findEmpDetail(empid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	//根据条件搜索员工
	public ArrayList<EmployeeEntity> searchEmp(String jobid, String empname, String cardnumber, String phone,
			String depid, String sex) {
		ArrayList<EmployeeEntity> emps=null;
		try {
			emps = dao.searchEmp(jobid,empname,cardnumber,phone,depid,sex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
		return emps;
	}
	public void delEmp(String[] empids) {
		try {
			dao.delEmp(empids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}
	public EmployeeEntity findEmpDetail(Integer empid) {
		// TODO Auto-generated method stub
		EmployeeEntity emp=null;
		try {
			emp = dao.findEmpDetail(empid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return emp;
	}
	public void UpdateEmp(EmployeeEntity emp) {
		try {
			dao.UpdateEmp(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}
	public boolean editPassword(int empid, String newpassword, String oldpassword) {
		boolean result=false;
		try {
			result = dao.editPassword(empid,newpassword,oldpassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return result;
	}
	 //人脸注册
	public void upDataFaceUrlByName(Integer empid, String urlPath, String path) {
		try {
			dao.upDataFaceUrlByName(empid,urlPath,path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}
}
