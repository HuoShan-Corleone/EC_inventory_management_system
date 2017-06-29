package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.User;
import edu.xupt.util.JdbcUtil;

public class usernameDao {
	private JdbcUtil jdbc = new JdbcUtil();
	User u = new User();
	public int insertNameAndPassword(String name,String password){
		Connection conn = jdbc.getConn();
		String sql = "insert into user(username,password) values('"+name+"','"+password+"')";
//		System.out.println(sql);
		int result = 0;
		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
//			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteById(String id){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from user where id='"+id+"'";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateByIdToName(int id , String name){
		Connection conn = jdbc.getConn();
		String sql = "update user set name='"+name+"' where id='"+id+"'";
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String updateByNameToPassword(String name,String password){
		Connection conn = jdbc.getConn();
		String sql = "update user set password='"+password+"' where name='"+name+"'";
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Change Password Success!";
	}
	
	public int updateUser(User u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update user set username=?,password=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			System.out.println(u.getId());
			int id = Integer.parseInt(u.getId());
			ps.setInt(3, id);
			System.out.println(ps.toString());
			result = ps.executeUpdate();
			System.out.println(sql);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public String  selectByNameToPassword(String name){
		Connection conn = jdbc.getConn();
		String sql = "select * from user where username='"+name+"'";
		String password = null;
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
//				System.out.print(rs.getString(1)+", ");
//				System.out.print(rs.getString(2)+", ");
				password = rs.getString(3);
//				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	public String  selectByNameToName(String name){
		Connection conn = jdbc.getConn();
		String sql = "select * from user where username='"+name+"'";
		String username = null;
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
//				System.out.print(rs.getString(1)+", ");
//				System.out.print(rs.getString(2)+", ");
				username = rs.getString(2);
//				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
	
	public List<User> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from user";
		List<User> users = new ArrayList<User>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static void main(String[] args) {
		usernameDao stu = new usernameDao();
//		stu.insertNameAndPassword("wuqian","123456"); // String name ,
//		stu.deleteById(5);  //int id 
//		stu.updateByIdToName(7,"hangyangyang");  //int id,String name
//		stu.selectByNameToPassword("liyunxiang"); //String name
	}
}
