package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.StringUtils;

import entity.StudentInfo;
import service.StudentInfoService;
import service.impl.StudentInfoServiceImpl;
@WebServlet("/Add")
public class AddServelt extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		req.setCharacterEncoding("UTF-8");
		// 获得隐藏域中的值
		String op = req.getParameter("op");
		if("add".equals(op)) {
			// 调用增加的方法
			addStu(req,resp);
		}else if("update".equals(op)){
			// 调用修改的方法
			updateStu(req,resp);
		}
	}

	private void updateStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String stuName=null;
		String gender = null;
		String age = null;
		String email = null;
		String headImg = null;
		String code = null;
		String stuNo=null;
		// 获得文件上传的路径
		String filePath = this.getServletContext().getRealPath("/statics/img");
		// 查看是否是
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart) {
			// 创建一个工厂对象
			FileItemFactory fac = new DiskFileItemFactory();
			// 创建一个文件上传的对象
			ServletFileUpload upload = new ServletFileUpload(fac);
			// 获得表单中的所有请求 限制10M
			upload.setFileSizeMax(10*1024*1024);
			try {
				List<FileItem> items = upload.parseRequest(req);
				// 遍历所有的请求内容
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					// 取出元素对象
					FileItem item = it.next();
					// 判断是否是普通的表单元素
					if(item.isFormField()) {
						String name = item.getFieldName();// 得到表单的name值
						// 根据name值，为上面的变量赋值
						switch (name) {
						case "stuNo":
							// 取出pname的值
							stuNo = item.getString("UTF-8");
							break;
						case "stuName":
							// 取出pname的值
							stuName = item.getString("UTF-8");
							break;
						case "gender":
							gender = item.getString("UTF-8");
							break;
						case "age":
							age = item.getString("UTF-8");
							break;
						case "email":
							email = item.getString("UTF-8");
							break;
						case "code":
							code = item.getString("UTF-8");
							break;
						}
					}else {// 上传的文本
						headImg = item.getName();
						// 设置一个上传文件的对象
						File saveFile = new File(filePath, headImg);
						item.write(saveFile);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String msg = "";
		// 后台做非空验证
		
		if(StringUtils.isNullOrEmpty(stuName)) {
			msg = "用户名为空";
			// 将这个结果保存到session中
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		// 获得保存在session中的验证码
		String saveCode  = (String)req.getSession().getAttribute("yzm");
		if(!code.equals(saveCode)) {
			msg = "验证码不一致";
			// 将这个结果保存到session中
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		
		// 创建一个瑶保存到数据库中的实体对象
		StudentInfo stu = new StudentInfo(Integer.parseInt(stuNo),stuName, Integer.parseInt(gender), Integer.parseInt(age), email, headImg);
		// 调用业务层中保存数据的方法
		StudentInfoService sis = new StudentInfoServiceImpl();
		boolean isOk = sis.addStu(stu);
		if(isOk) {
			resp.sendRedirect("ServerView");
		}else {
			msg = "更新数据失败";
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
		}
	}

	private void addStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String stuName=null;
		String gender = null;
		String age = null;
		String email = null;
		String headImg = null;
		String code = null;
		// 获得文件上传的路径
		String filePath = this.getServletContext().getRealPath("/statics/img");
		// 查看是否是
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart) {
			// 创建一个工厂对象
			FileItemFactory fac = new DiskFileItemFactory();
			// 创建一个文件上传的对象
			ServletFileUpload upload = new ServletFileUpload(fac);
			// 获得表单中的所有请求 限制10M
			upload.setFileSizeMax(10*1024*1024);
			try {
				List<FileItem> items = upload.parseRequest(req);
				// 遍历所有的请求内容
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					// 取出元素对象
					FileItem item = it.next();
					// 判断是否是普通的表单元素
					if(item.isFormField()) {
						String name = item.getFieldName();// 得到表单的name值
						// 根据name值，为上面的变量赋值
						switch (name) {
						case "stuName":
							// 取出pname的值
							stuName = item.getString("UTF-8");
							break;
						case "gender":
							gender = item.getString("UTF-8");
							break;
						case "age":
							age = item.getString("UTF-8");
							break;
						case "email":
							email = item.getString("UTF-8");
							break;
						case "code":
							code = item.getString("UTF-8");
							break;
						}
					}else {// 上传的文本
						headImg = item.getName();
						// 设置一个上传文件的对象
						File saveFile = new File(filePath, headImg);
						item.write(saveFile);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String msg = "";
		// 后台做非空验证
		if(StringUtils.isNullOrEmpty(stuName)) {
			msg = "用户名为空";
			// 将这个结果保存到session中
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		// 获得保存在session中的验证码
		String saveCode  = (String)req.getSession().getAttribute("yzm");
		if(!code.equals(saveCode)) {
			msg = "验证码不一致";
			// 将这个结果保存到session中
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		
		// 创建一个瑶保存到数据库中的实体对象
		StudentInfo stu = new StudentInfo(stuName, Integer.parseInt(gender), Integer.parseInt(age), email, headImg);
		// 调用业务层中保存数据的方法
		StudentInfoService sis = new StudentInfoServiceImpl();
		boolean isOk = sis.updaStu(stu);
		if(isOk) {
			resp.sendRedirect("ServerView");
		}else {
			msg = "增加数据失败";
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
		}
	}
	
}
