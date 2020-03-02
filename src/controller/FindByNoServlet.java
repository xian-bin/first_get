package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.StudentInfo;
import service.StudentInfoService;
import service.impl.StudentInfoServiceImpl;
@WebServlet("/FindByNo")
public class FindByNoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int stuNo = Integer.parseInt(req.getParameter("stuNo"));
		StudentInfoService sis = new StudentInfoServiceImpl();
		StudentInfo stu = sis.findByNo(stuNo);
		if(stu != null) {
			req.setAttribute("stu", stu);
			req.getRequestDispatcher("client/stuDetials.jsp").forward(req, resp);
		}
	}
	
}
