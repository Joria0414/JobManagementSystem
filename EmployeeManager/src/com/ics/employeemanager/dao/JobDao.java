package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ics.employeemanager.entity.JobEntity;

public class JobDao extends BaseDao{

	public ArrayList<JobEntity> findAllJob(int pageSize, int firstCount, String jobname) throws Exception {
		// TODO Auto-generated method stub
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		if(jobname!=null&&!"".equals(jobname))
		{
			sql1=" where jobname like '%"+jobname+"%'";
		}
		String sql = "select * from t_job"+sql1+" limit ?,?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);// ？？
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs;
		rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<JobEntity> jobs = new ArrayList<JobEntity>();
		JobEntity job = null;
		while (rs.next()) {
			job = new JobEntity();
			job.setJobid(rs.getInt(1));
			job.setJobdetail(rs.getString(3));
			job.setJobname(rs.getString(2));
			jobs.add(job);
		}
		// closeConnection();
		return jobs;
	}

	public int getJobCount(String jobname) throws Exception {
		// TODO Auto-generated method stub
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		if(jobname!=null&&!"".equals(jobname))
		{
			sql1=" where jobname like '%"+jobname+"%'";
		}
		String sql = "select count(*) from t_job"+sql1;
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	
	}

	public void delJob(String[] ids) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String wherein = "where jobid in(";
		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				wherein += ids[i];
			} else
				wherein += ids[i] + ",";
		}
		wherein += ");";
		String sql = "delete from t_job " + wherein;
		//System.out.println(sql);
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		
	}

	public boolean checkJobName(String jobname) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_job where jobname=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, jobname);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		int jobid = 0;
		while (rs.next()) {
			jobid = rs.getInt(1);
		}
		boolean result = false; // 是否可以注册的标识符
		if (jobid == 0) { // 如果有id就证明数据库中存在此名称，反之，可以注册。
			result = true;
		}
		return result;
	}

/*	public int insertDep(String jobname, String jobdetail) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_job(jobname,jobdetail) values(?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, jobname);
		pst.setString(2, jobdetail);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}*/

	public void updateJob(String jobid, String jobname, String jobdetail) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "update t_job set jobname=?,jobdetail=? where jobid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, jobname);
		pst.setString(2, jobdetail);
		pst.setString(3, jobid);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX

	}

	public ArrayList<JobEntity> getAllJob() throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_job";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);// ？？
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs;
		rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<JobEntity> jobs = new ArrayList<JobEntity>();
		JobEntity job = null;
		while (rs.next()) {
			job = new JobEntity();
			job.setJobid(rs.getInt(1));
			job.setJobdetail(rs.getString(3));
			job.setJobname(rs.getString(2));
			jobs.add(job);
		}
		return jobs;
	}

	public int insertJob(String jobname, String jobdetail) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_job(jobname,jobdetail) values(?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, jobname);
		pst.setString(2, jobdetail);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}

}
