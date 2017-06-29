package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Product;
import edu.xupt.entity.User;
import edu.xupt.util.JdbcUtil;

public class productDao {
	private JdbcUtil jdbc = new JdbcUtil();
	inventoryDao ivt = new inventoryDao();
//	productDao pd = new productDao();
	Product p = new Product();
	public int insertProduct(String name,String Type,String Description){
		Connection conn = jdbc.getConn();
		String sql = "insert into product(name,Type,Description) values('"+name+"','"+Type+"','"+Description+"')";
		System.out.println(sql);
		int result = 0;
		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ivt.insert(name);
		return result;
	}
	
	public void deleteByName(String name){
		Connection conn = jdbc.getConn();
		inventoryDao ivt = new inventoryDao();
		int productID = Integer.parseInt(this.selectByNameToId(name));
		ivt.deleteById(productID);
		String sql = "delete from product where name='"+name+"'";
		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int updateProduct(Product p){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update product set name=?,Type=?,Description=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setString(3, p.getDescription());
			System.out.println(p.getId());
			int id = Integer.parseInt(p.getId());
			ps.setInt(4, id);
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
	
	public void updateByIdToName(int id ,String name,String type,String description){
		Connection conn = jdbc.getConn();
		String sql = "update product set name='"+name+"', type='"+type+"', description='"+description+"' where id='"+id+"'";
		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateByNameToInformation(String name,String type,String description){
		Connection conn = jdbc.getConn();
		String sql = "update product set type='"+type+"', description='"+description+"' where id='"+name+"'";
		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String  selectByNameToInformation(String name){
		Connection conn = jdbc.getConn();
		String sql = "select * from product where name='"+name+"'";
		
		String id = null, description= null,type = null;
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
//				System.out.print(rs.getString(1)+", ");
//				System.out.print(rs.getString(2)+", ");
				id = rs.getString(1);
				type = rs.getString(3);
				description = rs.getString(4);
//				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id+","+name+","+type+","+description;
	}
	public String selectByNameToId(String name){
		Connection conn = jdbc.getConn();
		String sql = "select * from product where name='"+name+"'";
		
		String id = null, description= null,type = null;
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
//				System.out.print(rs.getString(1)+", ");
//				System.out.print(rs.getString(2)+", ");
				id = rs.getString(1);
				type = rs.getString(3);
				description = rs.getString(4);
//				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	public static void main(String[] args) {
		productDao pt = new productDao();
//		pt.insert("apple", "fruit", "fresh and nourishment");
//		pt.deleteById(2);
//		pt.updateByIdToName(1, "iphone","electricity","apple product");
//		String wt=pt.selectByNameToDescription("iphone");
//		System.out.print(wt);
//		pt.deleteByName("aaaa");
	}

	public int deleteById(String id){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from product where id='"+id+"'";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ivt.deleteById(Integer.parseInt(id));
		return result;
	}
	
	public List<Product> selectAllProduct() {
		// TODO Auto-generated method stub
		Connection conn = jdbc.getConn();
		String sql = "select * from product";
		List<Product> products = new ArrayList<Product>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				product.setType(rs.getString(3));
				product.setDescription(rs.getString(4));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
