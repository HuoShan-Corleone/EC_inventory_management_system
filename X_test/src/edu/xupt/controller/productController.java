package edu.xupt.controller;


import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import edu.xupt.dao.productDao;
import edu.xupt.dao.usernameDao;
import edu.xupt.entity.Product;
import edu.xupt.entity.User;

@Controller
@RequestMapping("/product")

public class productController {
	productDao pd = new productDao();
	@RequestMapping("/getProduct.do")
	public void getProductAll(HttpServletResponse response){
		List<Product> products = pd.selectAllProduct();
		System.out.println("get user info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(products);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(products));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/addProduct.do")
	public void addProduct(String name,String type,String description,HttpServletResponse response){
		productDao pd = new productDao();
		int r = pd.insertProduct(name, type, description);
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
	
	@RequestMapping("deleteProduct.do")
	public void deleteProduct(String id,HttpServletResponse response){
		productDao pd = new productDao();
		int r = pd.deleteById(id);
		
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			writer = response.getWriter();
			if(r>0){
				System.out.println("delete success!");
				map.put("success", true);
			}else{
				map.put("success", false);
				System.out.println("delete fail!");
			}
			writer.append(JSON.toJSONString(map));
			writer.flush();
		} catch (IOException e) {
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
	
	@RequestMapping("updateProduct.do")
	public void updateProduct(String id, String name,String type,String description,HttpServletResponse response){
		System.out.println("*****************update user!************");
		System.out.println(name);
		System.out.println("id="+id+" name="+name+" password="+type+" description"+description);
		productDao pd = new productDao();
		Product p = new Product();
		p.setId(id);
		p.setName(name);
		p.setType(type);
		p.setDescription(description);
		int r = pd.updateProduct(p);
		
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
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
	
}
