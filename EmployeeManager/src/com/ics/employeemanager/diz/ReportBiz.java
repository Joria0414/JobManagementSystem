package com.ics.employeemanager.diz;

import java.util.ArrayList;

import com.ics.employeemanager.dao.ReportDao;
import com.ics.employeemanager.entity.ReportEntity;
import com.ics.employeemanager.utils.ResultPage;

public class ReportBiz {

	ReportDao dao=new ReportDao();
	public void findAllReport(ResultPage<ReportEntity> pageInfo, String reportname, String reportcontent,
			String reportempname, String reporttime, String reporttype) {
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<ReportEntity> reports;
			reports= dao.findAllDoc(pageSize,firstCount,reportname,reportcontent,reportempname,reporttime,reporttype);
			pageInfo.setLists(reports);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getDocCount(reportname,reportcontent,reportempname,reporttime,reporttype);
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
	public ReportEntity findExactReport(int reportid) {
		ReportEntity report=null;
		try {
			report=dao.findExactReport(reportid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return report;
	}
	public boolean insertReport(int empid, String reporttitle, String reportcontent, String reporttype) {
		int res=0;
		try {
			res=dao.insertReport(empid, reporttitle,reportcontent,reporttype);
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
