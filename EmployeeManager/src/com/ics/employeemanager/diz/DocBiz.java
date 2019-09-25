package com.ics.employeemanager.diz;

import java.util.ArrayList;

import com.ics.employeemanager.dao.DocDao;
import com.ics.employeemanager.entity.DocEntity;
import com.ics.employeemanager.utils.ResultPage;

public class DocBiz {

	DocDao dao=new DocDao();
	public int addDoc(DocEntity doc) {
		int flag=0;
		try {
			flag=dao.addDoc(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return flag;
	}
	//查看所有文档列表
	public ArrayList<DocEntity> findAllDoc(ResultPage<DocEntity> pageInfo, String title) {
		ArrayList<DocEntity> docs=null;
		try {
			// 1.定义两个变量：pageSize=2；first=（当前页-1）*pageSize
			int pageSize = 2;// 每页展示的条数
			int firstCount = (pageInfo.getCurrentPage() - 1) * pageSize; // 起始条数
			// 2.获取当前页展示的数据——调用数据层方法
			/*ArrayList<DocEntity> docs;*/
			docs = dao.findAllDoc(pageSize, firstCount, title);
			pageInfo.setLists(docs);
			// 3.获取总条数——调用数据层方法
			int totalCount = dao.getDocCount(title);
			pageInfo.setTotalCount(totalCount);
			// 4.计算总页数
			int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
			pageInfo.setTotalPage(totalPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dao.closeConnection();
		}

		return docs;
	}
	public void delDoc(String[] ids) {
		try {
			dao.delDoc(ids); // 调用dao层的方法
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			dao.closeConnection();
		}
		
	}

}
