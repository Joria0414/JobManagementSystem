package com.ics.employeemanager.diz;

import java.util.ArrayList;

import com.ics.employeemanager.dao.NoticeDao;
import com.ics.employeemanager.entity.NoticeEntity;
import com.ics.employeemanager.utils.ResultPage;

/*
 * 公告业务逻辑层
 */
public class NoticeBiz {

	NoticeDao dao=new NoticeDao();
	public void UpdateNotice(String noticeid, String noticename, String noticecontent) {
		try {
			dao.UpdateNotice(noticeid,noticename,noticecontent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
	}
	
	public void findAllNotice(ResultPage<NoticeEntity> pageInfo, String noticename, String noticecontent, String noticeempname) {
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<NoticeEntity> notices;
			notices = dao.findAllNotice(pageSize,firstCount,noticename,noticecontent,noticeempname);
			pageInfo.setLists(notices);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getNoticeCount(noticename,noticecontent,noticeempname);
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

	public NoticeEntity findExactNotice(int noticeid) {
		NoticeEntity notice=null;
		try {
			notice=dao.findExactNotice(noticeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return notice;
	}

	public ArrayList<NoticeEntity> SearchNotice(String empname, String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delNotice(String[] ids) {
		try {
			dao.delNotice(ids); // 调用dao层的方法
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			dao.closeConnection();
		}
		
	}

	public boolean insertNotice(int empid, String title, String content) {
		int res=0;
		try {
			res=dao.insertNotice(empid,title,content);
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
