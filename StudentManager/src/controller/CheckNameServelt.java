package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentInfoService;
import service.impl.StudentInfoServiceImpl;
@WebServlet("/CheckName")
public class CheckNameServelt extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求的编码
		req.setCharacterEncoding("UTF-8");
		// 设置响应的数据格式
		resp.setContentType("application/json;charset=UTF-8");
		// 获得用户名
		String stuName = req.getParameter("stuName");
		// 创建业务层对象
		StudentInfoService sis = new StudentInfoServiceImpl();
		// 调用验证用户名是否存在的方法
		boolean isOk = sis.isExists(stuName);
		// 根据调用方法后的结果，响应客户端的信息
		PrintWriter pw = resp.getWriter();
		if(isOk) {
			pw.write("{\"result\":\"true\"}");
		}else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
		pw.close();
	}
	
}
