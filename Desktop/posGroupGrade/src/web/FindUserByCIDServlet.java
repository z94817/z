package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.GroupPosUser;
import service.GroupPosService;

import com.alibaba.fastjson.JSONObject;

public class FindUserByCIDServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		rep.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		try {
			GroupPosService gps = new GroupPosService();
			String cId = req.getParameter("cId");
			List<GroupPosUser> list = gps.findUserByCID(Integer.parseInt(cId));
			String string = JSONObject.toJSONString(list);
			if(list.size()==0){
				rep.getWriter().print("1");
			}else{
				rep.getWriter().print(string);
			}
			
		} catch (Exception e) {
			rep.getWriter().print("0");
		}
		
	}
}
