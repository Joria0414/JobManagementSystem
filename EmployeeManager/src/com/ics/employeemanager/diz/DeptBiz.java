package com.ics.employeemanager.diz;
import java.util.ArrayList;

import com.ics.employeemanager.dao.DeptDao;
import com.ics.employeemanager.entity.DepEntity;
import com.ics.employeemanager.utils.ResultPage;

/*
 * 部门相关的业务逻辑层
 */
public class DeptBiz {
	DeptDao dao=new DeptDao();
	//分页查看所有部门的信息
	public void findAllDept(ResultPage<DepEntity> pageInfo){		
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<DepEntity> deps;
			deps = dao.findAllDept(pageSize,firstCount);
			pageInfo.setLists(deps);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getDeptCount();
			pageInfo.setTotalCount(totalCount);
			//4.计算总页数
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.关闭连接
			dao.closeConnection();
		}
	}
	public void delDep(String[] ids) {
		try{
		dao.delDep(ids); //调用dao层的方法
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭连接
			dao.closeConnection();
		}
	}
	//检查部门名称是否添加过
	public boolean checkDepName(String depname) throws Exception {
		boolean result=dao.checkDepName(depname);
		return result;
	}
	public boolean insertDep(String depname, String depdetail) {
		int res=0;
		try {
			res = dao.insertDep(depname,depdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
		
	}
	//修改部门
	public void updateDep(String depid, String depname, String depdetail) {
		// TODO Auto-generated method stub
		try {
			dao.updateDep(depid,depname,depdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
	}
	//查询所有部门信息的不分页写法
	public ArrayList<DepEntity> getAllDep() {
		ArrayList<DepEntity> deps=null;
		try {
			deps = dao.getAllDep();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return deps;
	}
	public void findExcatDept(ResultPage<DepEntity> pageInfo, String deptname) {
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<DepEntity> deps;
			deps = dao.findExactDept(pageSize,firstCount,deptname);
			pageInfo.setLists(deps);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getExactDeptCount(deptname);
			pageInfo.setTotalCount(totalCount);
			//4.计算总页数
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.关闭连接
			dao.closeConnection();
		}
		
	}

}
