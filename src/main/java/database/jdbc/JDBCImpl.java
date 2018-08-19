package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;

public class JDBCImpl {
	public static String Driver_For_MySQL="com.mysql.cj.jdbc.Driver";//mysql的驱动
	
	public static String Driver_For_DB2="com.mysql.jdbc.Driver";//db2的驱动

	public static String URL="jdbc:mysql://localhost:3306/tables?useSSL=false&serverTimezone=GMT%2B8";
	
	public static String username="root";
	
	public static String password="123456";
	/*
	 * 静态Sql
	 * SQL语句不需要拼接，直接使用Statement
	 */
	
	@Test
	public void testStaticSql(){
	
		try {
			String sql="insert into user(name,password) values('qiantao2','qtweqwe')";
			Class.forName(Driver_For_MySQL) ;
			Connection conn=DriverManager.getConnection(URL,username,password);
			//得到Statement对象(静态SQL)
			Statement stat =conn.createStatement();
			//执行SQL
			int result=stat.executeUpdate(sql);
			// 处理结果
			System.out.println(result==0?"执行失败":"执行成功");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	
	/*
	 * 执行动态Sql
	 * 当执行动态sql时，使用PreparedStatement来进行拼接
	 */
	@Test
	public void testDynamicSql(){
	
		try {
			String sql="delete from user where name =?";
			Class.forName(Driver_For_MySQL) ;
			Connection conn=DriverManager.getConnection(URL,username,password);
			PreparedStatement stat=conn.prepareStatement(sql);
			stat.setString(1, "qiantao2");//下标从1开始
			//执行sql
			int result=stat.executeUpdate();
			//  处理结果
			System.out.println(result==0?"执行失败":"执行成功");
			//关闭连接 
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	/*
	 * 执行批量静态Sql
	 * 只能进行DML语句  无法执行DQL语句  (select 语句)
	 * 当执行动态sql时，使用PreparedStatement来进行拼接
	 * 当需要批量处理时，通过addBatch()方法来处理
	 */
	@Test
	public void testStaticSqlWithBatch(){
		try {
			String sql="insert into user(name,password) values('qiantao3','qtweqwe3') ";
			String sql2="insert into user(name,password) values('qiantao4','qtweqwe4')";
			Class.forName(Driver_For_MySQL) ;
			Connection conn=DriverManager.getConnection(URL,username,password);
			//得到Statement对象(静态SQL)
			Statement stat =conn.createStatement();
			stat.addBatch(sql);
			stat.addBatch(sql2);
			
			//执行SQL
			int[] result =stat.executeBatch();
			// 处理结果
			for(int i=0;i< result.length;i++) {
				System.out.println("result:"+i+"   //"+result[i]);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	
	
	/*
	 * 执行批量动态Sql
	 * 只能进行DML语句  无法执行DQL语句  (select 语句)
	 * 当执行动态sql时，使用PreparedStatement来进行拼接
	 * 当需要批量处理时，通过addBatch()方法来处理
	 */
	@Test
	public void testDynamicSqlWithBatch(){
	
		try {
			String sql="delete from user where name=? and password =?";
			Class.forName(Driver_For_MySQL) ;
			Connection conn=DriverManager.getConnection(URL,username,password);
			//得到Statement 对象 PreparedStatement是Statement的子接口
			PreparedStatement stat=conn.prepareStatement(sql);
			stat.setString(1, "qiantao3");
			stat.setString(2, "qtweqwe3");
			stat.addBatch();
			stat.setString(1, "qiantao4");
			stat.setString(2, "qtweqwe4");
			stat.addBatch();
			//执行sql
			int[] result=stat.executeBatch();
			//  处理结果
			System.out.println(result[0]==0?"执行失败":"执行成功");
			//关闭连接 
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	
	/*
	 * 执行DQL语句（Select语句) 
	 * 使用 executeQuery(sql)方法  返回结果集 ResultSet
	 * 使用迭代将其返回成一个Object[]此处 用
	 * 
	 */
	
	@Test
	public void testSqlWithResultSet(){
		try {
			String sql="select * from user";
			Class.forName(Driver_For_MySQL) ;
			Connection conn=DriverManager.getConnection(URL,username,password);
			Statement stat =conn.createStatement();
			//得到结果集
			ResultSet result=stat.executeQuery(sql);
			// 处理结果  
			List list=new ArrayList();
			//ResultSetMetaData   rsmd1 = result.getMetaData();//此处有当前字段的信息
			while(result.next()){
				Map map=new HashMap();//模拟 返回对象 其中有两个属性值(name ,birthdate)
				map.put("name", result.getString("Name"));//根据表中的列名得到数据，并传递到属性上
				map.put("password", result.getString("password"));
				list.add(map);
			}
			System.out.println("-----------------------------");
			for(Object o:list){
				System.out.println(o);
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
}
