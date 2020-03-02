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
		// ��������ı���
		req.setCharacterEncoding("UTF-8");
		// ������Ӧ�����ݸ�ʽ
		resp.setContentType("application/json;charset=UTF-8");
		// ����û���
		String stuName = req.getParameter("stuName");
		// ����ҵ������
		StudentInfoService sis = new StudentInfoServiceImpl();
		// ������֤�û����Ƿ���ڵķ���
		boolean isOk = sis.isExists(stuName);
		// ���ݵ��÷�����Ľ������Ӧ�ͻ��˵���Ϣ
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
