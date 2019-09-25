package com.ics.employeemanager.entity;

public class JobEntity {
	private int jobid;
	private String jobname;
	private String jobdetail;
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getJobdetail() {
		return jobdetail;
	}
	public void setJobdetail(String jobdetail) {
		this.jobdetail = jobdetail;
	}
	@Override
	public String toString() {
		return "JobEntity [jobid=" + jobid + ", jobname=" + jobname + ", jobdetail=" + jobdetail + "]";
	}
	
}
