package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import pojo.GroupPosGrade;
import pojo.GroupPosUser;

import service.GroupPosService;

public class FindUserAndGradeServlet extends HttpServlet {

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
		String datetime = req.getParameter("datetime");
		int month = 0;
		if(datetime==null||datetime==""||"undefined".equals(datetime)){
			month = new Date().getMonth() +1;
		}else{
			if(!"总表".equals(datetime)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				try {
					Date parse = sdf.parse(datetime);
					month = parse.getMonth()+1;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			GroupPosService gps = new GroupPosService();
			List<GroupPosUser> users = gps.findUser();
			if(users.size()==0){
				rep.getWriter().print("1");
				return;
			}
			if("总表".equals(datetime)){
				for (int i = 0; i < users.size(); i++) {
					GroupPosUser gpu = users.get(i);
					List<GroupPosGrade> list = gps.findAllGrade(gpu.getuId());
					gpu.setGrades(list);
				}		
			}else{
				for (int i = 0; i < users.size(); i++) {
					GroupPosUser gpu = users.get(i);
					List<GroupPosGrade> list = gps.findGradeByUID(gpu.getuId(),month);
					gpu.setGrades(list);
				}
			}
			
			String string = JSONObject.toJSONString(users);
			rep.getWriter().print(string);
		} catch (Exception e) {
			rep.getWriter().print("0");
		}
	}
}
