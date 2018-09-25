package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import pojo.GroupPosUser;
import pojo.SubCompany;
import service.GroupPosService;

public class SubCompanyServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException {
		rep.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		try {
			GroupPosService gps = new GroupPosService();
			List<SubCompany> list = gps.findSubCompany();
			if(list.size()==0){
				rep.getWriter().print("1");
				return;
			}
			String string = JSONObject.toJSONString(list);
			rep.getWriter().print(string);
		} catch (Exception e) {
			// TODO: handle exception
			rep.getWriter().print("0");
		}
		
	}
	
}
