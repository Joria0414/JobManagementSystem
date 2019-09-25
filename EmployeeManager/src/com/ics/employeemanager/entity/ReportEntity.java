package com.ics.employeemanager.entity;

import java.sql.Timestamp;

public class ReportEntity {
	private int empid,reportid;
	private String reporttitle,reportcontent,empname,reporttype;
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	private Timestamp reporttime;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getReportid() {
		return reportid;
	}
	@Override
	public String toString() {
		return "ReportEntity [empid=" + empid + ", reportid=" + reportid + ", reporttype=" + reporttype
				+ ", reporttitle=" + reporttitle + ", reportcontent=" + reportcontent + ", reporttime=" + reporttime
				+ "]";
	}
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String string) {
		this.reporttype = string;
	}
	public String getReporttitle() {
		return reporttitle;
	}
	public void setReporttitle(String reporttitle) {
		this.reporttitle = reporttitle;
	}
	public String getReportcontent() {
		return reportcontent;
	}
	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
	}
	public Timestamp getReporttime() {
		return reporttime;
	}
	public void setReporttime(Timestamp reporttime) {
		this.reporttime = reporttime;
	}
}
