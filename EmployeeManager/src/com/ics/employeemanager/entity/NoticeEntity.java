package com.ics.employeemanager.entity;

import java.sql.Timestamp;

public class NoticeEntity {
	private int noticeid,empid;
	private String noticecontent,empname,noticename;
	private Timestamp createtime;

	public int getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getNoticename() {
		return noticename;
	}
	public void setNoticename(String noticename) {
		this.noticename = noticename;
	}
	@Override
	public String toString() {
		return "NoticeEntity [noticeid=" + noticeid + ", empid=" + empid + ", noticecontent=" + noticecontent
				+ ", empname=" + empname + ", createtime=" + createtime + ", getNoticeid()=" + getNoticeid()
				+ ", getEmpid()=" + getEmpid() + ", getNoticecontent()=" + getNoticecontent() + ", getCreatetime()="
				+ getCreatetime() + ", getEmpname()=" + getEmpname() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
