package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.GroupPosService;

public class SaveUsersServlet extends HttpServlet {

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
			String src = "E:\\MyEclipse 10\\Workspaces\\posGroupGrade\\WebRoot\\img\\user\\";
			int sizeThreshold = 1024 * 64;  //写满该大小的缓存后，存入硬盘中。
			File repositoryFile = new File(src);
			FileItemFactory factory = new DiskFileItemFactory(sizeThreshold, repositoryFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");	//设置字符编码
			upload.setSizeMax(2 * 1024 * 1024); // set every upload file'size less than 50M
			List items = upload.parseRequest(req);   //这里开始执行上传
			Iterator iter = items.iterator();
			Map<String, String> map = new HashMap<String, String>();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();   //FileItem就是表示一个表单域。
				if(item.isFormField()){ //isFormField方法用于判断FileItem是否代表一个普通表单域(即非file表单域)
					String key = new String(item.getFieldName().getBytes("ISO-8859-1"), "UTF-8");
					String value = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
					map.put(key, value);
				}else {
//					String fieldName = item.getFieldName();  //获取表单域name属性的值
//					String fileName = item.getName();     //返回该文件在客户机上的文件名。e.g: e:\dianying\\video\1.wmv
					String path = item.getName();
					String fileName = path.substring(path.lastIndexOf("."));
					fileName = System.currentTimeMillis()+fileName;
					map.put("fileName", "img/user/"+fileName);
					File uploadedFile = new File(src + fileName);
					item.write(uploadedFile);
				}
			}
			GroupPosService gps = new GroupPosService();
			String uId = map.get("uId");
			String fileName = map.get("fileName");
			if("undefined".equals(uId)|| uId == ""|| uId == null){
				gps.addUsers(fileName, map.get("uName"), map.get("uWork"));
			}else{
				if("undefined".equals(fileName)|| fileName == ""|| fileName == null){
					gps.updateUsers(Integer.parseInt(uId), map.get("uImg"), map.get("uName"), map.get("uWork"));
				}else{
					gps.updateUsers(Integer.parseInt(uId),fileName, map.get("uName"), map.get("uWork"));
				}
				
			}
			rep.getWriter().print("保存成功!");
			for (String key : map.keySet()) {
			}
		} catch (Exception e) {
			rep.getWriter().print("失败,请重试!");
			e.printStackTrace();
		}
		
		
			
		
	}
}
