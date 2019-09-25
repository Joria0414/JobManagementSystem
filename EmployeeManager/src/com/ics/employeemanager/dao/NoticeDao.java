package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ics.employeemanager.entity.NoticeEntity;
import com.ics.employeemanager.utils.ResultPage;

public class NoticeDao extends BaseDao{

	public ArrayList<NoticeEntity> FindAllNotice(ResultPage<NoticeEntity> pageInfo) throws Exception {
		//1.打开连接
		openConnection();
		//2.写sql语句
		String sql="select * from t_notice n ,t_employee e where e.empid=n.empid";
		//3.创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		//4.接收对象
		ResultSet rs=pst.executeQuery();
		//5.结果集
		ArrayList<NoticeEntity> notices=new ArrayList<NoticeEntity>();
		NoticeEntity notice=null;
		while(rs.next()){
			notice=new NoticeEntity();
			notice.setCreatetime(rs.getTimestamp("createtime"));
			notice.setEmpid(rs.getInt("empid"));
			notice.setEmpname(rs.getString("empname"));
			notice.setNoticecontent(rs.getString("noticecontent"));
			notice.setNoticeid(rs.getInt("noticeid"));
			notice.setNoticename(rs.getString("noticename"));
			notices.add(notice);
		}
		return notices;
	}

	public void UpdateNotice(String noticeid, String noticename, String noticecontent) throws Exception {
		// 1.打开连接
		openConnection();
		// 2.写sql语句
		String sql = "update t_notice set noticename=?,noticecontent=? where noticeid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, noticename);
		pst.setString(2, noticecontent);
		pst.setInt(3, Integer.parseInt(noticeid));
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		
	}

	public ArrayList<NoticeEntity> findAllNotice(int pageSize, int firstCount, String noticename, String noticecontent, String noticeempname) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		String sql2="";
		String sql3="";
		if(noticeempname!=null&&!"".equals(noticeempname)){
			sql1=" and e.empname like '%"+noticeempname+"%'";
		}
		if(noticecontent!=null&&!"".equals(noticecontent)){
			sql2=" and n.noticecontent like '%"+noticecontent+"%'";
		}
		if(noticename!=null&&!"".equals(noticename)){
			sql3=" and n.noticename like '%"+noticename+"%'";
		}
		//String sql = "select count(*) from t_notice n,t_employee e where e.empid=n.empid and e.status=1"+sql1+sql2+sql3;		
		String sql = "select * from t_notice n,t_employee e where n.empid=e.empid and e.status=1"+sql1+sql2+sql3+" limit ?,?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);// ？？
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<NoticeEntity> notices = new ArrayList<NoticeEntity>();
		NoticeEntity notice = null;
		String content=null;
		String subcontent=null;
		while (rs.next()) {
			notice = new NoticeEntity();
			notice.setNoticeid(rs.getInt("noticeid"));
			notice.setNoticename(rs.getString("noticename"));
			notice.setNoticecontent(rs.getString("noticecontent"));
			
			if(notice.getNoticecontent().length()>11){
				content=notice.getNoticecontent();
				subcontent=content.substring(0, 10)+"...";
				notice.setNoticecontent(subcontent);
			}
			
			
			notice.setEmpid(rs.getInt("empid"));
			notice.setCreatetime(rs.getTimestamp("createtime"));
			notice.setEmpname(rs.getString("empname"));
			notices.add(notice);
		}
		// closeConnection();
		return notices;
	}

	public int getNoticeCount(String noticename, String noticecontent, String noticeempname) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		String sql2="";
		String sql3="";
		if(noticeempname!=null&&!"".equals(noticeempname)){
			sql1=" and e.empname like '%"+noticeempname+"%'";
		}
		if(noticecontent!=null&&!"".equals(noticecontent)){
			sql2=" and n.noticecontent like '%"+noticecontent+"%'";
		}
		if(noticename!=null&&!"".equals(noticename)){
			sql3=" and n.noticename like '%"+noticename+"%'";
		}
		String sql = "select count(*) from t_notice n,t_employee e where e.empid=n.empid and e.status=1"+sql1+sql2+sql3;
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public NoticeEntity findExactNotice(int noticeid) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_notice where noticeid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, noticeid);
		ResultSet rs = pst.executeQuery();
		NoticeEntity notice=new NoticeEntity();
		while(rs.next()){
			notice.setNoticecontent(rs.getString("noticecontent"));
			notice.setNoticename(rs.getString("noticename"));
		}
		return notice;
	}

	public void delNotice(String[] ids) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String wherein = "where noticeid in(";
		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				wherein += ids[i];
			} else
				wherein += ids[i] + ",";
		}
		wherein += ");";
		String sql = "delete from t_notice " + wherein;
		// System.out.println(sql);
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		
	}

	public int insertNotice(int empid, String title, String content) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_notice(empid,noticename,noticecontent,createtime) values(?,?,?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		
		Timestamp nowtime=new Timestamp(new Date().getTime());
		pst.setInt(1, empid);
		pst.setString(2, title);
		pst.setString(3, content);
		pst.setTimestamp(4, nowtime);		
		/*pst.setString(1, depname);
		pst.setString(2, depdetail);*/
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}

}
