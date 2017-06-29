package edu.xupt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Inventory;
import edu.xupt.util.JdbcUtil;

public class inventoryDao {
	private JdbcUtil jdbc = new JdbcUtil();
	
	public void updateByProductIDToSum(int productID,int sum){
		Connection conn = jdbc.getConn();
		String sql = "update inventory set sum='"+sum+"' where productID='"+productID+"'";
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
	public List<Inventory> selectAllInventory() {
		// TODO Auto-generated method stub
		Connection conn = jdbc.getConn();
		String sql = "select * from inventory";
		List<Inventory> inventorys = new ArrayList<Inventory>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Inventory inventory = new Inventory();
				inventory.setId(rs.getString(1));
				inventory.setProductID(rs.getString(3));
				inventory.setSum(rs.getString(2));
				inventorys.add(inventory);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventorys;
	}
	public void insert(String name){
		Connection conn = jdbc.getConn();
		productDao pd = new productDao();
		int productID = Integer.parseInt(pd.selectByNameToId(name));
//		System.out.println(productID);
		String sql = "insert into inventory(productID,sum) values('"+productID+"','0')";
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
	
	public void deleteById(int productID) {
		// TODO Auto-generated method stub
		Connection conn = jdbc.getConn();
		String sql = "delete from inventory where productID='"+productID+"'";
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
	public static void main(String[] args) {
		inventoryDao inv = new inventoryDao();
//		inv.updateByProductIDToSum(9, 30);
//		inv.insert("iphone");
//		inv.deleteById(1);
	}


}
