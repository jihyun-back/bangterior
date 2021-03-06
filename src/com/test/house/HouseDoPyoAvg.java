package com.test.house;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/house/HouseDoPyoAvg.do")
public class HouseDoPyoAvg extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String graphadress = req.getParameter("graphadress");

		HouseDAO dao = new HouseDAO();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("graphadress", graphadress);
		
	
		ArrayList<String> list = dao.dopyoavg(map);
		
		dao.close();

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		JSONArray arr = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			JSONObject obj = new JSONObject();
			
			obj.put("score",list.get(i));
			arr.add(obj);
		}
		
		writer.print(arr);
		writer.close();
	}
}
