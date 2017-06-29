package edu.xupt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.CartItems;
import edu.xupt.util.JdbcUtil;

public class cartItemsDao {
	private JdbcUtil jdbc = new JdbcUtil();
	productDao pd = new productDao();
	inventoryDao inv = new inventoryDao();
//���
	public int insertAdd(int record,String productName){
		Connection conn = jdbc.getConn();
		int productID = Integer.parseInt(pd.selectByNameToId(productName));
		String sql = "insert into cartItems(record,productID) values('"+record+"','"+productID+"')";
//		System.out.println(sql);
		int result = 0;
		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSum(productName);
		return result;
	}
//����
	public int insertSub(int record,String productName){
		Connection conn = jdbc.getConn();
		int productID = Integer.parseInt(pd.selectByNameToId(productName));
		String sql = "insert into cartItems(record,productID) values('"+-record+"','"+productID+"')";
//		System.out.println(sql);
		int result = 0;
		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSum(productName);
		return result;
	}
	
	public int getSum(String productName){
		Connection conn = jdbc.getConn();
		int productID = Integer.parseInt(pd.selectByNameToId(productName));
		String sql = "SELECT SUM(record) FROM cartitems where productID='"+productID+"'";
//		System.out.println(sql);
		int sum = 0;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
//				System.out.print(rs.getString(1));
				sum = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inv.updateByProductIDToSum(productID, sum);
		return sum;
	}
	
	public List<CartItems> selectAllRecord() {
		// TODO Auto-generated method stub
		Connection conn = jdbc.getConn();
		String sql = "select * from cartitems";
		List<CartItems> cartItems = new ArrayList<CartItems>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				CartItems c = new CartItems();
				c.setId(rs.getString(1));
				c.setProductID(rs.getString(2));
				c.setRecord(rs.getString(3));
				cartItems.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItems;
	}
	public static void main(String[] args) {
		cartItemsDao cart = new cartItemsDao();
//		cart.insertSub(30, "apple");
		
		System.out.print(cart.getSum("apple"));
	}
}
