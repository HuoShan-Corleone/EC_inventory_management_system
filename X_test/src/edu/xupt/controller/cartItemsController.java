package edu.xupt.controller;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import edu.xupt.dao.cartItemsDao;
import edu.xupt.entity.CartItems;
@Controller
@RequestMapping("/cartItems")

public class cartItemsController {
	cartItemsDao cd = new cartItemsDao();
	@RequestMapping("/purchaseItems.do")
	public void purchaseItems(String productname,String record,HttpServletResponse response){
		int num = Integer.parseInt(record);
		int r = cd.insertAdd(num, productname);
		System.out.println(r);
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		System.out.println("111");
		try {
			writer = response.getWriter();
			if(r>0){
				writer.append("OK");
			}else{
				writer.append("NO");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(writer != null){
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	@RequestMapping("/sellItems.do")
	public void sellItems(String productname,String record,HttpServletResponse response){
		int num = Integer.parseInt(record);
		int r = cd.insertSub(num, productname);
		System.out.println(r);
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		System.out.println("111");
		try {
			writer = response.getWriter();
			if(r>0){
				writer.append("OK");
			}else{
				writer.append("NO");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(writer != null){
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping("/getCartItems.do")
	public void getCartItems(HttpServletResponse response){
		List<CartItems> cartItems = cd.selectAllRecord();
		System.out.println("get user info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(cartItems);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(cartItems));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
