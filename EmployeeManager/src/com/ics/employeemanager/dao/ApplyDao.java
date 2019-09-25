package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ics.employeemanager.entity.ApplyEntity;

public class ApplyDao extends BaseDao {

	public ArrayList<ApplyEntity> FindAllAbsent(int pageSize, int firstCount, String applytype, String empname,
			String applystatus) throws Exception {
		openConnection();
		String sql1="";
		String sql2="";
		String sql3="";
		if(applytype!=null&&!"".equals(applytype)){
			sql1=" and a.applytype="+applytype;
		}
		if(empname!=null&&!"".equals(empname)){
			sql2=" and e1.empname like '%"+empname+"%'";
		}
		if(applystatus!=null&&!"".equals(applystatus)&&Integer.parseInt(applystatus)!=0){
			sql3=" and a.applystatus="+applystatus;
		}
		String sql = "select * from ((t_apply a left join t_employee e1  on e1.empid =a.empid) left join t_employee e2 on a.approverid = e2.empid) where e1.status=1"+sql1+sql2+sql3+" limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		ResultSet rs = pst.executeQuery();
		ArrayList<ApplyEntity> applies=new ArrayList<ApplyEntity>();
		ApplyEntity apply=null;
		while(rs.next()){
			apply=new ApplyEntity();
			apply.setApplyid(rs.getInt("applyid"));
			apply.setApplyreason(rs.getString("applyreason"));
			apply.setApplystatus(rs.getString("applystatus"));
			apply.setApplytime(rs.getTimestamp("applytime"));
			apply.setApplytype(rs.getString("applytype"));
			apply.setApproverid(rs.getInt("approverid"));
			apply.setApprovername(rs.getString("e2.empname"));
			apply.setEmpid(rs.getInt("empid"));
			apply.setEmpname(rs.getString("empname"));
			apply.setEndtime(rs.getTimestamp("endtime"));
			apply.setRefusereason(rs.getString("refusereason"));
			apply.setStarttime(rs.getTimestamp("starttime"));
			applies.add(apply);
		}
		return applies;
	}

	public int getAbsentCount(String applytype, String empname, String applystatus) throws Exception {
		openConnection();
		String sql1="";
		String sql2="";
		String sql3="";
		if(applytype!=null&&!"".equals(applytype)){
			sql1=" and a.applytype="+applytype;
		}
		if(empname!=null&&!"".equals(empname)){
			sql2=" and e1.empname like '%"+empname+"%'";
		}
		if(applystatus!=null&&!"".equals(applystatus)&&Integer.parseInt(applystatus)!=0){
			sql3=" and a.applystatus="+applystatus;
		}
		String sql = "select count(*) from ((t_apply a left join t_employee e1 on e1.empid =a.empid) left join t_employee e2 on a.approverid = e2.empid) where e1.status=1"+sql1+sql2+sql3;
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public ApplyEntity findExactApply(String applyid) throws Exception {
		openConnection();
		String sql="select * from t_apply a,t_employee e where e.status=1 and a.empid=e.empid and applyid="+applyid;
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		ApplyEntity apply=new ApplyEntity();
		while(rs.next()){
			apply.setApplyreason(rs.getString("applyreason"));
			apply.setEndtime(rs.getTimestamp("endtime"));
			apply.setStarttime(rs.getTimestamp("starttime"));
			apply.setEmpname(rs.getString("empname"));
			apply.setApplytype(rs.getString("applytype"));
		}
		return apply;
	}

	public void Approve(String[] ids, int approverid) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		Timestamp nowtime=new Timestamp(new Date().getTime());
		String wherein = "where applyid in(";
		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				wherein += ids[i];
			} else
				wherein += ids[i] + ",";
		}
		wherein += ");";
		String sql = "update  t_apply set applystatus=1, approverid=? ,approvertime=? " + wherein;
		//System.out.println(sql+"   "+approverid);
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, approverid);
		pst.setTimestamp(2, nowtime);
		//System.out.println(pst);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();	
	}

	public int disapprove(String refusereason, String applyid, int approverid) throws Exception {
		openConnection();
		Timestamp nowtime=new Timestamp(new Date().getTime());
		//System.out.println(applyid+" "+approverid+" "+refusereason);
		String sql="update t_apply set applystatus=2,approvertime=?,refusereason=?,approverid=? where applyid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setTimestamp(1, nowtime);
		pst.setString(2, refusereason);
		pst.setInt(3, approverid);
		pst.setString(4, applyid);
		int res=pst.executeUpdate();
		return res;
	}

	public int insertApply(int empid, String applycontent, String type, Timestamp starttime, Timestamp endtime) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_apply(empid,applyreason,applytype,starttime,endtime,applytime,applystatus) values(?,?,?,?,?,?,3)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);

		Timestamp nowtime = new Timestamp(new Date().getTime());
		pst.setInt(1, empid);
		pst.setString(2, applycontent);
		pst.setString(3, type);
		pst.setTimestamp(4, starttime);
		pst.setTimestamp(5, endtime);
		pst.setTimestamp(6,nowtime);
		/*
		 * pst.setString(1, depname); pst.setString(2, depdetail);
		 */
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}

}
