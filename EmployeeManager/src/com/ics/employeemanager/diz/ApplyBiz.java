package com.ics.employeemanager.diz;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.ics.employeemanager.dao.ApplyDao;
import com.ics.employeemanager.entity.ApplyEntity;
import com.ics.employeemanager.utils.ResultPage;

public class ApplyBiz {
	ApplyDao dao=new ApplyDao();

	public void FindAllAbsent(ResultPage<ApplyEntity> pageInfo, String applytype, String empname, String applystatus) {
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<ApplyEntity> applies;
			applies = dao.FindAllAbsent(pageSize,firstCount,applytype,empname,applystatus);
			pageInfo.setLists(applies);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getAbsentCount(applytype,empname,applystatus);
			pageInfo.setTotalCount(totalCount);
			//4.计算总页数
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}

	public ApplyEntity findExactApply(String applyid) {
		ApplyEntity apply=null;
		try {
			apply=dao.findExactApply(applyid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return apply;
	}

	public void ApproveSvl(String[] ids, int approverid) {
		try {
			dao.Approve(ids,approverid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}

	public boolean disapprove(String refusereason, String applyid, int approverid) {
		int res=0;
		try {
			res=dao.disapprove(refusereason,applyid,approverid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
	}

	public boolean insertApply(int empid, String applycontent, String type, Timestamp starttime, Timestamp endtime) {
		int res=0;
		try {
			res=dao.insertApply(empid,applycontent,type,starttime,endtime);
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
	}

}
