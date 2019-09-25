package com.ics.employeemanager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.ics.employeemanager.entity.EmployeeEntity;

/**
 * 数据层：操作数据库，所有员工相关的操作
 * @author samsung
 *
 */
public class EmployeeDao extends BaseDao{
	//登录---写方法的两要素
	/*
	 * 方法两要素
	 * 1、方法有没有返回值：有--就写返回值类型，没有---void
	 * 2、有没有参数：有--有哪些参数就写形参 没有---（）
	 *
	 */
	public EmployeeEntity login(String empname,String password) throws Exception{
		//1.2打开连接
		openConnection();//类里面自己调用不需要变量
		//3.写sql语句
		String sql="select * from t_employee where empname=? and password=? and status=1";//可以直接拼接参数，现在凡是遇到变量都写问号
		//4.创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		//如果有参数（？），就拼参数；如果没有，就可以执行sql了
		pst.setString(1,empname);//把问号给改了，setXX（？的顺序，变量值：要拼在sql语句里的值）xx的类型和变量要一致
		pst.setString(2, password);
		//5.选择执行sql语句的方法 增删改：enecuteupdate返回的是受影响的行数（本步骤是最后一步）  查询：excuteQuery,返回结果集（需要解析结果集）
		ResultSet rs=pst.executeQuery();
		
		//6.如果是查询就需要处理结果集 （1）选择容器 (2）遍历next（），移动到下一行
		EmployeeEntity emp=null;
		while(rs.next()){ //移动到下一行，循环
			emp=new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));//getXX（可以是索引---表里字段的顺序/表里字段名在（本例））用来取值，获取数据库里的数据值：1、类型和数据库里此字段的类型一致2、
			emp.setPassword(rs.getString("password"));
			emp.setRole(rs.getString("role"));
			emp.setEmpname(rs.getString("empname"));
		}
		//7.释放资源
		closeConnection();
		return emp;
	}
	//添加员工
	public int addEmployee(EmployeeEntity emp) throws Exception{
		//1、2打开连接
		openConnection();
		//3、写sql语句
		String sql="insert into t_employee(depid,jobid,empname,cardnumber,sex,education,email,phone,tel,party,qq,address,postcode,birthday,race,speciality,hobby,remark,createtime,password,role,status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
		//4、创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, emp.getDepId());
		pst.setInt(2, emp.getJobId());
		pst.setString(3, emp.getEmpname());
		pst.setString(4, emp.getCardunmber());
		pst.setString(5, emp.getSex());
		pst.setString(6, emp.getEducation());
		pst.setString(7, emp.getEmail());
		pst.setString(8, emp.getPhone());
		pst.setString(9, emp.getTel());
		pst.setString(10, emp.getParty());
		pst.setString(11, emp.getQq());
		pst.setString(12, emp.getAddress());
		pst.setString(13, emp.getPostcode());
		pst.setTimestamp(14, emp.getBirthday());
		pst.setString(15, emp.getRace());
		pst.setString(16, emp.getSpeciality());
		pst.setString(17, emp.getHobby());
		pst.setString(18, emp.getRemark());
		pst.setTimestamp(19,new Timestamp(new Date().getTime()));
		pst.setString(20, emp.getPassword());
		//pst.setString(21,emp.getStatus());
		pst.setString(21, emp.getRole());
		//5、执行sql语句，增删改：executrupdate---返回int类型 查询 ：executequery----返回resultset
		int res=pst.executeUpdate();
		//6、需要处理结果集吗？不需要 只有查询需要处理结果集
		return res;
		
	}
	//查看所有员工
	public ArrayList<EmployeeEntity> findAllEmp() throws Exception{
		//1.2打开连接
		openConnection();
		//3、写sql
		String sql="select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=1";
		//4、创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		//5、执行sql executequery
		ResultSet rs=pst.executeQuery();
		//6、处理结果集 1.选择用什么来接受参数 next getXX
		ArrayList<EmployeeEntity> list=new ArrayList<EmployeeEntity>();//创建集合
		EmployeeEntity emp = null;
		while(rs.next()){ //遍历
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));//getXX（索引--表里字段的顺序或者数据表里的字段名获得在数据库里的值：1.）
			emp.setEmpname(rs.getString("empname"));
			emp.setJobname(rs.getString("jobname"));
			emp.setDepname(rs.getString("depname"));
		    emp.setAddress(rs.getString("address"));
			emp.setEducation(rs.getString("education"));
		    emp.setCreateTime(rs.getTimestamp("createtime"));
			emp.setEmail(rs.getString("email"));
			emp.setCardunmber(rs.getString("cardnumber"));
			emp.setPhone(rs.getString("phone"));
			emp.setSex(rs.getString("sex"));
			emp.setFacePath(rs.getString("facepath"));
			emp.setFaceUrl(rs.getString("faceurl"));
			emp.setStatus(rs.getString("status"));
			emp.setRole(rs.getString("role"));
			list.add(emp);  //把创建好的对象存放到集合中
		}
		return list;
	    }
	//查看员工详情,根据员工id查看员工详情
	public EmployeeEntity findEmpDetail(int empid) throws Exception{
		//1.2打开连接
		openConnection();
		//3、写sql
		String sql="select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and empid=?";
		//4、创建预编译对象
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, empid);
		//5、执行sql executequery
		ResultSet rs=pst.executeQuery();
		//6、处理结果集 1.选择用什么来接受参数 next getXX
		EmployeeEntity emp=null;
		while(rs.next()){
			emp=new EmployeeEntity();
			emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));//getXX（索引--表里字段的顺序或者数据表里的字段名获得在数据库里的值：1.）
			emp.setEmpname(rs.getString("empname"));
			emp.setRole(rs.getString("role"));
			emp.setJobId(rs.getInt("jobid"));
			emp.setDepId(rs.getInt("depid"));
			emp.setJobname(rs.getString("jobname"));
			emp.setDepname(rs.getString("depname"));
			emp.setPassword(rs.getString("password"));
		    emp.setAddress(rs.getString("address"));
		    emp.setBirthday(rs.getTimestamp("birthday"));
			emp.setEducation(rs.getString("education"));
		    emp.setCreateTime(rs.getTimestamp("createtime"));
			emp.setEmail(rs.getString("email"));
		    emp.setFaceUrl(rs.getString("facepath"));
		    emp.setFacePath(rs.getString("faceurl")); 
			emp.setTel(rs.getString("tel"));
			emp.setPostcode(rs.getString("postcode"));
			emp.setCardunmber(rs.getString("cardnumber"));
			emp.setQq(rs.getString("qq"));
			emp.setParty(rs.getString("party"));
			emp.setPhone(rs.getString("phone"));
			emp.setSpeciality(rs.getString("speciality"));
			emp.setSex(rs.getString("sex"));
			emp.setStatus(rs.getString("status"));
			emp.setRace(rs.getString("race"));
			emp.setRemark(rs.getString("remark"));
			emp.setHobby(rs.getString("hobby"));
		}
		return emp;
	}
	
	//获取员工数量
	public int getEmpCount() throws Exception{
			//1.2打开连接
			openConnection();
			//3、写sql
			String sql="select count(*) from t_employee e,t_dep d , t_job j where status=1";
			//4、创建预编译对象
			PreparedStatement pst=conn.prepareStatement(sql);
			//5、执行sql executequery
			ResultSet rs=pst.executeQuery();
			//6、处理结果集 1.选择用什么来接受参数 next getXX
			int res=0;
			while(rs.next()){
				res=rs.getInt(1);
			}
			return res;
	}
	public ArrayList<EmployeeEntity> searchEmp(String jobid, String empname, String cardnumber, String phone,
			String depid, String sex) throws Exception {
		// 1.2打开连接
		openConnection();
		// 3、写sql
		String sql1="",sql2="",sql3="",sql4="",sql5="",sql6="";
		//选择职位时会有一个为0的默认值，所以，此处只要判断不是0即可。
		if(!"0".equals(jobid)){
			sql1=" and j.jobid="+jobid;
		}
		//非空empname like '%?%'
		if(empname!=null&&!"".equals(empname)){
			sql2=" and e.empname like '%"+empname+"%'";
		}
		//身份证号码非空判断
		if(cardnumber!=null&&!"".equals(cardnumber)){
			sql3=" and e.cardnumber='"+cardnumber+"'";
		}
		//性别,选择性别时也会有一个默认值为0
		if(!"0".equals(sex)){
			sql4=" and e.sex="+sex;
		}
		//手机号非空判断
		if(phone!=null&&!"".equals(phone)){
			sql5=" and e.phone='"+phone+"'";
		}
		//部门，选择部门时也会有一个默认值为0
		if (!"0".equals(depid)) {
			sql6 = " and e.depid=" + depid;
		}
		String sql = "select * from t_employee e,t_dep d , t_job j where e.depid=d.depid and e.jobid=j.jobid and status=1"+sql1+sql2+sql3+sql4+sql5+sql6;
		// 4、创建预编译对象
		PreparedStatement pst = conn.prepareStatement(sql);
		// 5、执行sql executequery
		ResultSet rs = pst.executeQuery();
		// 6、处理结果集 1.选择用什么来接受参数 next getXX
		ArrayList<EmployeeEntity> emps = new ArrayList<EmployeeEntity>();
		while (rs.next()) {
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmpId(rs.getInt("empid"));// getXX（索引--表里字段的顺序或者数据表里的字段名获得在数据库里的值：1.）
			emp.setEmpname(rs.getString("empname"));
			emp.setRole(rs.getString("role"));
			emp.setJobname(rs.getString("jobname"));
			emp.setDepname(rs.getString("depname"));
			emp.setPassword(rs.getString("password"));
			emp.setAddress(rs.getString("address"));
			emp.setBirthday(rs.getTimestamp("birthday"));
			emp.setEducation(rs.getString("education"));
			emp.setCreateTime(rs.getTimestamp("createtime"));
			emp.setEmail(rs.getString("email"));
			emp.setFaceUrl(rs.getString("facepath"));
			emp.setFacePath(rs.getString("faceurl"));
			emp.setTel(rs.getString("tel"));
			emp.setPostcode(rs.getString("postcode"));
			emp.setCardunmber(rs.getString("cardnumber"));
			emp.setQq(rs.getString("qq"));
			emp.setParty(rs.getString("party"));
			emp.setPhone(rs.getString("phone"));
			emp.setSpeciality(rs.getString("speciality"));
			emp.setSex(rs.getString("sex"));
			emp.setStatus(rs.getString("status"));
			emp.setRace(rs.getString("race"));
			emp.setRemark(rs.getString("remark"));
			emp.setHobby(rs.getString("hobby"));
			emps.add(emp);
		}
		return emps;
	}
	public void delEmp(String[] empids) throws Exception {
		openConnection();
		String wherein=" where empid in (";
		for(int i=0;i<empids.length;i++){
//			if(loginEmpid!=Integer.parseInt(empids[i])){
				wherein+=empids[i];
			//}
			if(i!=empids.length-1){
				wherein+=",";
			}
		}
		wherein+=")";
		String sql="update t_employee set status=2 "+wherein;
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}
	public void UpdateEmp(EmployeeEntity emp) throws Exception {
		openConnection();
		//System.out.println(emp);
		String sql="update t_employee set depid=?,jobid=?,empname=?,cardnumber=?,sex=?,education=?,email=?,phone=?,tel=?,party=?,qq=?,address=?,postcode=?,birthday=?,race=?,speciality=?,hobby=?,remark=? where  empid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, emp.getDepId());
		pst.setInt(2, emp.getJobId());
		pst.setString(3, emp.getEmpname());
		pst.setString(4, emp.getCardunmber());
		pst.setString(5, emp.getSex());
		pst.setString(6, emp.getEducation());
		pst.setString(7, emp.getEmail());
		pst.setString(8, emp.getPhone());
		pst.setString(9, emp.getTel());
		pst.setString(10, emp.getParty());
		pst.setString(11, emp.getQq());
		pst.setString(12, emp.getAddress());
		pst.setString(13, emp.getPostcode());
		pst.setTimestamp(14, emp.getBirthday());
		pst.setString(15, emp.getRace());
		pst.setString(16, emp.getSpeciality());
		pst.setString(17, emp.getHobby());
		pst.setString(18, emp.getRemark());
		pst.setInt(19, emp.getEmpId());
		//pst.setTimestamp(19,new Timestamp(new Date().getTime()));
		//pst.setString(20, emp.getPassword());
		//pst.setString(21,emp.getStatus());
		//pst.setString(22, emp.getRole());
		pst.executeUpdate();
	}
	public boolean editPassword(int empid, String newpassword, String oldpassword) throws Exception {
		//boolean result;
		openConnection();
		String sql="select * from t_employee where empid=?";
		//System.out.println(newpassword+" "+oldpassword+" "+empid);
		PreparedStatement pst = conn.prepareStatement(sql);
		/*EmployeeEntity emp=new EmployeeEntity();
		emp.setEmpId(empid);*/
		pst.setInt(1, empid);
		//System.out.println(pst);
		ResultSet rs = pst.executeQuery();
		String password=null;
		while(rs.next()){
			password=rs.getString("password");
			//System.out.println(password);
		}
		/*System.out.println(password);
		System.out.println(password);*/
		if(password.equals(oldpassword)){
			String sql1="update t_employee set password=? where empid=?";
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setString(1, newpassword);
			pst1.setInt(2, empid);
			pst1.executeUpdate();
			return true;
		}
		else{
			return false;
		}
	}
	public void upDataFaceUrlByName(Integer empid, String urlPath, String path) throws Exception {
		// TODO Auto-generated method stub
		openConnection();
		String sql="update t_employee set faceurl=?,facepath=? where empid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, urlPath);
		pst.setString(2, path);
		pst.setInt(3, empid);
		pst.executeUpdate();
	}
	
}
	

