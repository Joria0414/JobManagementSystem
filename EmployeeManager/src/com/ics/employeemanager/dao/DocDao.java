package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ics.employeemanager.entity.DocEntity;

public class DocDao extends BaseDao {

	public int addDoc(DocEntity doc) throws Exception {
		//1.2打开连接
		openConnection();
		//3.写sql
		String sql="insert into t_document values (null,?,?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, doc.getEmpid());
		pst.setString(2, doc.getTitle());
		pst.setString(3, doc.getFilepath());
		pst.setString(4, doc.getRemark());
		pst.setTimestamp(5,new Timestamp(new Date().getTime()));
		//5.执行sql
		int flag=pst.executeUpdate();
		//System.out.println(flag);
		return flag;
	}

	public ArrayList<DocEntity> findAllDoc(int pageSize, int firstCount, String title) throws Exception {
		openConnection();
		String sql1="";
		if(title!=null&&!"".equals(title))
		{
			sql1=" and d.title like '%"+title+"%'";
		}
		String sql="select * from t_document d,t_employee e where d.empid=e.empid"+sql1+" limit ?,?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		ResultSet rs=pst.executeQuery();
		ArrayList<DocEntity> docs=new ArrayList<DocEntity>();
		DocEntity doc=null;
		while(rs.next()){
			doc=new DocEntity();
			doc.setDocumentid(rs.getInt("documentid"));
			doc.setEmpid(rs.getInt("empid"));
			doc.setEmpname(rs.getString("empname"));
			doc.setTitle(rs.getString("title"));
			doc.setFilepath(rs.getString("filepath"));
			doc.setRemark(rs.getString("remark"));
			doc.setCreatetime(rs.getTimestamp("createtime"));
			docs.add(doc);
		}
		return docs;
	}

	public void delDoc(String[] ids) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String wherein = "where documentid in(";
		for (int i = 0; i < ids.length; i++) {
			if (i == ids.length - 1) {
				wherein += ids[i];
			} else
				wherein += ids[i] + ",";
		}
		wherein += ");";
		String sql = "delete from t_document " + wherein;
		// System.out.println(sql);
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		
	}

	public int getDocCount(String title) throws Exception {
		openConnection();
		String sql1="";
		if(title!=null&&!"".equals(title))
		{
			sql1=" where title like '%"+title+"%'";
		}
		String sql="select count(*) from t_document"+sql1;
		
		
		/*String sql = "select count(*) from t_notice n,t_employee e where e.empid=n.empid and e.status=1" + sql1 + sql2
				+ sql3;
*/		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

}
