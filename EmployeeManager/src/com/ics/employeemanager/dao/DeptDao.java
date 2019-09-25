package com.ics.employeemanager.dao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import com.ics.employeemanager.entity.DepEntity;

public class DeptDao extends BaseDao{
//分页查看所有信息
	public ArrayList<DepEntity> findAllDept(int pageSize, int firstCount) throws Exception {
		//1.2打开连接
		openConnection();
		//3.写sql
		String sql="select * from t_dep limit ?,?";
		//4.创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);//？？
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		//5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs=pst.executeQuery();
		//6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<DepEntity> deps =new ArrayList<DepEntity>();
		DepEntity dep=null;
		while(rs.next()){
			dep=new DepEntity();
			dep.setDepid(rs.getInt(1));
			dep.setDedetail(rs.getString(3));
			dep.setDepname(rs.getString(2));
			deps.add(dep);
		}
		//closeConnection();
		return deps;
	}

	public int getDeptCount() throws Exception {
		// TODO Auto-generated method stub
		//1.2打开连接
		openConnection();
		//3.写sql
		String sql="select count(*) from t_dep";
		//4.创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int count=0;
		while(rs.next()){
			count=rs.getInt(1);
		}
		return count;
	}

	public void delDep(String[] ids) throws Exception {
		//1.2打开连接
		openConnection();
		//3.写sql
		String wherein="where depid in(";
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){
				wherein+=ids[i];
			}
			else
			wherein+=ids[i]+",";
		}
		wherein+=");";
		String sql="delete from t_dep "+wherein;
		//System.out.println(sql);
		//4.创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		//5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
	}

	public boolean checkDepName(String depname) throws Exception {
		
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_dep where depname=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		int depid=0;
		while (rs.next()) {
			depid=rs.getInt(1);
		}
		boolean result=false; //是否可以注册的标识符
		if(depid==0){ //如果有id就证明数据库中存在此名称，反之，可以注册。
			result=true;
		}
		return result;
	}

	public int insertDep(String depname, String depdetail) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "insert into t_dep(depname,depdetail) values(?,?)";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		int res=pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		return res;
	}

	public void updateDep(String depid, String depname, String depdetail) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "update t_dep set depname=?,depdetail=? where depid=?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, depname);
		pst.setString(2, depdetail);
		pst.setInt(3, Integer.parseInt(depid));
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		pst.executeUpdate();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
	}

	public ArrayList<DepEntity> getAllDep() throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql = "select * from t_dep";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);// 
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<DepEntity> deps = new ArrayList<DepEntity>();
		DepEntity dep = null;
		while (rs.next()) {
			dep = new DepEntity();
			dep.setDepid(rs.getInt(1));
			dep.setDedetail(rs.getString(3));
			dep.setDepname(rs.getString(2));
			deps.add(dep);
		}
		return deps;
	}

	public ArrayList<DepEntity> findExactDept(int pageSize, int firstCount, String deptname) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		if(deptname!=null&&!"".equals(deptname))
		{
			sql1=" where depname like '%"+deptname+"%'";
		}
		String sql = "select * from t_dep"+sql1+" limit ?,?";
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstCount);
		pst.setInt(2, pageSize);
		//pst.setString(1, deptname);//
		// 5.执行sql 查询：executeQuery-返回resultset结果集 增删改：executeUpdate——返回受影响行数
		ResultSet rs = pst.executeQuery();
		// 6.如果查询处理resultset结果集 1.选择什么类型接受数据 2.next（）3.getXX
		ArrayList<DepEntity> deps = new ArrayList<DepEntity>();
		DepEntity dep = null;
		while (rs.next()) {
			dep = new DepEntity();
			dep.setDepid(rs.getInt(1));
			dep.setDedetail(rs.getString(3));
			dep.setDepname(rs.getString(2));
			deps.add(dep);
		}
		return deps;
	}

	public int getExactDeptCount(String deptname) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3.写sql
		String sql1="";
		if(deptname!=null&&!"".equals(deptname))
		{
			sql1=" where depname like '%"+deptname+"%'";
		}
		String sql = "select count(*) from t_dep "+sql1;
		// 4.创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		//pst.setString(1, deptname);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}
}
