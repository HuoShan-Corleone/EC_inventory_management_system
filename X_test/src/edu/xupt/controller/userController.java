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

import edu.xupt.dao.usernameDao;
import edu.xupt.entity.User;

@Controller
@RequestMapping("/user")
public class userController {
	usernameDao ud = new usernameDao();
	
	@RequestMapping("/login.do")
	public String userLogin(String username,String password){
		String dbpass = ud.selectByNameToPassword(username);
		String dbname = ud.selectByNameToName(username);
		System.out.println(username);
		System.out.println(password);
		System.out.println(dbpass);
		System.out.println(dbname);
		if(username.equals(dbname)){
			if(password.equals(dbpass)){
				return "main";
			}else{
				return "PasswordFalse";
			}
		}else{
			return "UsernameFalse";
		}
	}
	
	@RequestMapping("register.do")
	public void userRegister(String username,String password,HttpServletResponse response){
		usernameDao ud = new usernameDao();
		int r = ud.insertNameAndPassword(username, password);
//		System.out.println(r);
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
//		System.out.println("111");
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
	@RequestMapping("/delete2.do")
	public void userDelete2(String id,HttpServletResponse response){
		usernameDao user = new usernameDao();
		int r = user.deleteById(id);
		
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
	
	@RequestMapping("updateUser.do")
	public void updateUser(String id, String username,String password,HttpServletResponse response){
		System.out.println("*****************update user!************");
		System.out.println(username);
		System.out.println("id="+id+" name="+username+" password="+password);
		usernameDao userDao = new usernameDao();
		User u = new User();
		u.setId(id);
		u.setUsername(username);
		u.setPassword(password);
		int r = userDao.updateUser(u);
		
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
	@RequestMapping("/getAll2.do")
	public void getUserAll(HttpServletResponse response){
		usernameDao user = new usernameDao();
		List<User> users = user.selectAll2();
		System.out.println("get user info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(users);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(users));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
