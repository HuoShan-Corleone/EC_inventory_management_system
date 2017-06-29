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

import edu.xupt.dao.inventoryDao;
import edu.xupt.entity.Inventory;

@Controller
@RequestMapping("/inventory")

public class inventoryController {
	inventoryDao inv = new inventoryDao();
	@RequestMapping("/getInventoy.do")
	public void getProductAll(HttpServletResponse response){
		Inventory inventory = new Inventory();
		List<Inventory> inventorys = inv.selectAllInventory();
		System.out.println("get user info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(inventorys);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(inventorys));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
