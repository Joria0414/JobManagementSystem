package com.ics.employeemanager.entity;
/*
 * 部门实体类
 */
public class DepEntity {
	private int depid;
	private String depname;
	private String dedetail;
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getDedetail() {
		return dedetail;
	}
	public void setDedetail(String dedetail) {
		this.dedetail = dedetail;
	}
	@Override
	public String toString() {
		return "DepEntity [depid=" + depid + ", depname=" + depname + ", dedetail=" + dedetail + "]";
	}
	
}
