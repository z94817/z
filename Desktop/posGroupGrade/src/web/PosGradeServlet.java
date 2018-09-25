package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.GroupPosGrade;
import service.GroupPosService;

import com.alibaba.fastjson.JSON;

public class PosGradeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
//		ServletContext context = req.getServletContext();
//		Integer posGroupGradeCount = (Integer) context.getAttribute("PosGroupGradeCount");
//		System.out.println(posGroupGradeCount);
//		if(posGroupGradeCount==null){
//			posGroupGradeCount=0;
//		}else{
//			if(posGroupGradeCount>=5){
//				rep.getWriter().print("本月投票超限");
//				return;
//			}
//		}
		GroupPosService pgs = null;
		String ipAddr = null;
		try {
			ipAddr = req.getRemoteAddr();
			pgs = new GroupPosService();
			String date = pgs.findeIp(ipAddr);
			if(date!=""&&date!=null){
				rep.getWriter().print("您本月已投票,请勿重复投票");
				return;
			}
			Cookie[] cookies = req.getCookies();
			if(cookies!=null){
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if("PosGroupGradeUserIp".equals(cookie.getName())){
						String value = cookie.getValue();
						@SuppressWarnings("deprecation")
						String localeString = new Date().toLocaleString();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(sdf.parse(value).getYear()==sdf.parse(localeString).getYear()&&sdf.parse(value).getMonth()>=sdf.parse(localeString).getMonth()){
							rep.getWriter().print("您本月已投票,请勿重复投票");
							return;
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        reader.close();
        List<GroupPosGrade> list = JSON.parseArray(json, GroupPosGrade.class);
        try {
        	pgs.addGrade(list);
        	int companyId = list.get(0).getSubCompanyId();
	        pgs.saveIp(ipAddr, companyId);
	        Cookie cookie = new Cookie("PosGroupGradeUserIp", new Date().toLocaleString());
	        cookie.setMaxAge(60*60*24*30);
	        rep.addCookie(cookie);
//	        context.setAttribute("PosGroupGradeCount", ++posGroupGradeCount);
			rep.getWriter().print("感谢您的评价");
		} catch (Exception e) {
			rep.getWriter().print("评价失败,请刷新页面重试");
		}
//		Map<String, String[]> map = req.getParameterMap();
//		GroupPosGrade pgg = new GroupPosGrade();
//		try {
//			BeanUtils.populate(pgg, map);
//			GroupPosService pgs = new GroupPosService();
//			boolean flag = pgs.addGrade(pgg);
//			if(flag){
//				rep.getWriter().print("<script language='javascript'>alert('感谢您的评价')</script>");
//			}else{
//				rep.getWriter().print("<script language='javascript'>alert('不明原因,评价失败')</script>");
//			}
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
}
