package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ics.employeemanager.entity.ReportEntity;

public class ReportDao extends BaseDao{

	public ArrayList<ReportEntity> findAllDoc(int pageSize, int firstCount, String reportname, String reportcontent,
			String reportempname, String reporttime, String reporttype) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4="";
		String sql5="";
		if (reportname != null && !"".equals(reportname)) {
			sql1 = " and r.reporttitle like '%" + reportname + "%'";
		}
		if (reportcontent != null && !"".equals(reportcontent)) {
			sql2 = " and r.reportcontent like '%" + reportcontent + "%'";
		}
		if (reportempname != null && !"".equals(reportempname)) {
			sql3 = " and e.empname like '%" + reportempname + "%'";
		}
		if (reporttime != null && !"".equals(reporttime)) {
			sql4 = " and r.reporttime like '%" + reporttime + "%'";
		}
		if (reporttype != null && !"".equals(reporttype)) {
			sql5 = " and r.reporttype=" + reporttype;
		}
		// String sql = "select count(*) from t_notice n,t_employee e where
		// e.empid=n.empid and e.status=1"+sql1+sql2+sql3;
		String sql = "select * from t_report r,t_employee e where r.empid=e.empid and e.status=1" + sql1 + sql2 + sql3 + sql4+sql5+" order by reporttime asc limit ?,? ";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);// ？？
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<ReportEntity> reports = new ArrayList<ReportEntity>();
		ReportEntity report = null;
		String content = null;
		String subcontent = null;
		while (rs.next()) {
			report = new ReportEntity();
			report.setEmpname(rs.getString("empname"));
			report.setReportcontent(rs.getString("reportcontent"));
			report.setReportid(rs.getInt("reportid"));
			report.setReporttime(rs.getTimestamp("reporttime"));
			report.setReporttitle(rs.getString("reporttitle"));
			report.setReporttype(rs.getString("reporttype"));
			if (report.getReportcontent().length() > 11) {
				content = report.getReportcontent();
				subcontent = content.substring(0, 10) + "...";
				report.setReportcontent(subcontent);
			}
			reports.add(report);
		}
		// closeConnection();
		return reports;
	}

	public int getDocCount(String reportname, String reportcontent, String reportempname, String reporttime, String reporttype) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4="";
		String sql5="";
		if (reportname != null && !"".equals(reportname)) {
			sql1 = " and r.reporttitle like '%" + reportname + "%'";
		}
		if (reportcontent != null && !"".equals(reportcontent)) {
			sql2 = " and r.reportcontent like '%" + reportcontent + "%'";
		}
		if (reportempname != null && !"".equals(reportempname)) {
			sql3 = " and e.empname like '%" + reportempname + "%'";
		}
		if (reporttime != null && !"".equals(reporttime)) {
			sql4 = " and r.reporttime like '%" + reporttime + "%'";
		}
		if (reporttype != null && !"".equals(reporttype)) {
			sql5 = " and r.reporttype=" + reporttype;
		}
		// String sql = "select count(*) from t_notice n,t_employee e where
		// e.empid=n.empid and e.status=1"+sql1+sql2+sql3;
		String sql = "select count(*) from t_report r,t_employee e where r.empid=e.empid and e.status=1" + sql1 + sql2 + sql3 + sql4+sql5;
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
		
	}

	public ReportEntity findExactReport(int reportid) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_report r,t_employee e where r.empid=e.empid and reportid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, reportid);
		ResultSet rs = pst.executeQuery();
		ReportEntity report = new ReportEntity();
		while (rs.next()) {
			report.setReportcontent(rs.getString("reportcontent"));
			report.setReporttitle(rs.getString("reporttitle"));
			report.setEmpname(rs.getString("empname"));
			report.setReporttime(rs.getTimestamp("reporttime"));
			report.setReporttype(rs.getString("reporttype"));
		}
		return report;
	}

	public int insertReport(int empid, String reporttitle, String reportcontent, String reporttype) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_report(empid,reporttitle,reportcontent,reporttype,reporttime) values(?,?,?,?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);

		Timestamp nowtime = new Timestamp(new Date().getTime());
		pst.setInt(1, empid);
		pst.setString(2, reporttitle);
		pst.setString(3, reportcontent);
		pst.setString(4, reporttype);
		pst.setTimestamp(5, nowtime);
		/*
		 * pst.setString(1, depname); pst.setString(2, depdetail);
		 */
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res = pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}

}
