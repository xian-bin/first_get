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
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
		// 获得参数值
		int stuNo = Integer.parseInt(req.getParameter("stuNo"));
		StudentInfoService sis = new StudentInfoServiceImpl();
		boolean isOk = sis.deleteStu(stuNo);
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
