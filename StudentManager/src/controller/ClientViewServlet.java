package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Pager;
import entity.StudentInfo;
import service.StudentInfoService;
import service.impl.StudentInfoServiceImpl;
@WebServlet("/ClientView")
public class ClientViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ����һ��service�Ľӿڶ���
		StudentInfoService sis = new StudentInfoServiceImpl();
		// ���õ�ǰҳ���ÿһҳ��ʾ����������
		int currpage = 1;
		int pageSize = 6;
		// ��ҳ��Ķ���
		Pager pg = new Pager();
		pg.setPageSize(pageSize);
		// ���pageIndex��ֵ
		String pageIndex = req.getParameter("pageIndex");
		// ������ݿ������ݵ�����
		int count = sis.getCount();
		pg.setTotalCount(count);
		if(pageIndex == null || "".equals(pageIndex)) {
			currpage = 1;
		}else {
			currpage = Integer.parseInt(pageIndex);
			if(currpage <= 0) {
				currpage = 1;
			}else if(currpage >= pg.getTotalPage()) {
				currpage = pg.getTotalPage();
			}
		}
		// ͨ����ǰҳ���ÿһҳ��ʾ������������÷�ҳ����
		List<StudentInfo> slist = sis.getStus(currpage, pageSize);
		// ����Щ�������ø���ҳ���ʵ��
		pg.setCurrpage(currpage);
		pg.setStusList(slist);
		// �������ҳ�ౣ�浽��������
		req.setAttribute("pg", pg);
		req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
	}
	
}
