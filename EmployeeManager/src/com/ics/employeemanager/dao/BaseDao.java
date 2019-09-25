package com.ics.employeemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * BaseDao  数据库连接工具
 * @author samsung
 *
 */
public class BaseDao {
	//数据库驱动包
	protected Connection conn;
    //打开连接
	public void openConnection() throws Exception
	{  //只有Connection对象为null或者被关闭的情况才会创建连接
		if(conn==null||conn.isClosed()){//加载JDBC驱动
		Class.forName("com.mysql.jdbc.Driver");
		//建立数据库连接Connection,连接本地数据库localhost或者127.0.0.1
		String url="jdbc:mysql://localhost:3306/employmanage?useUnicode=true&characterEncoding=utf8";
		String username="root";
		String password="wyl8321359";
		conn=DriverManager.getConnection(url,username,password);
		}
	}
	//关闭连接
	public void closeConnection(){
		//连接不为空时，才要关闭
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//关闭连接
		}
	}
}
