package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.GroupPosUser;
import pojo.SubCompany;
import service.GroupPosService;

import com.alibaba.fastjson.JSONObject;

public class FindSubCompanyAndUserServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

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
			for (int i = 0; i < list.size(); i++) {
				SubCompany sc = list.get(i);
				List<GroupPosUser> usersByCid = gps.findUserByCID(sc.getcId());
				sc.setUsers(usersByCid);
			}
			String string = JSONObject.toJSONString(list);
			rep.getWriter().print(string);
		} catch (Exception e) {
			rep.getWriter().print("0");
		}
	}
}
