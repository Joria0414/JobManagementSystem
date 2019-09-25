package com.ics.employeemanager.diz;

import java.util.ArrayList;

import com.ics.employeemanager.dao.JobDao;
import com.ics.employeemanager.entity.JobEntity;
import com.ics.employeemanager.utils.ResultPage;

public class JobBiz {
	JobDao dao=new JobDao();
	//分页查看所有工作的信息
	public void findAllJob(ResultPage<JobEntity> pageInfo, String jobname) {
		try {
			//1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize=2;//每页展示的条数
			int firstCount=(pageInfo.getCurrentPage()-1)*pageSize; //起始条数
			//2.获取当前页展示的数据——调用数据层方法
			ArrayList<JobEntity> jobs;
			jobs = dao.findAllJob(pageSize,firstCount,jobname);
			pageInfo.setLists(jobs);
			//3.获取总条数——调用数据层方法
			int totalCount=dao.getJobCount(jobname);
			//System.out.println(totalCount);
			pageInfo.setTotalCount(totalCount);
			//4.计算总页数
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.关闭连接
			dao.closeConnection();
		}
	}
	public void delJob(String[] ids) {
		try{
			dao.delJob(ids); //调用dao层的方法
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				//关闭连接
				dao.closeConnection();
			}
		
	}
	public boolean checkDepName(String jobname) {
		boolean result = false;
		try {
			result = dao.checkJobName(jobname);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			dao.closeConnection();
		}
		return result;
	}
	/*public boolean insertDep(String jobname, String jobdetail) {
		int res=0;
		try {
			res = dao.insertDep(jobname,jobdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
	}*/
	public void updateDep(String jobid, String jobname, String jobdetail) {
		try {
			//System.out.println(jobid);
			dao.updateJob(jobid,jobname,jobdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
	}
	public ArrayList<JobEntity> getAllJob() {
		ArrayList<JobEntity> jobs=null;
		try {
			jobs = dao.getAllJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return jobs;
	}
	public boolean insertJob(String jobname, String jobdetail) {
		int res=0;
		try {
			res = dao.insertJob(jobname,jobdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
	}
	
}
