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
		// 创建一个service的接口对象
		StudentInfoService sis = new StudentInfoServiceImpl();
		// 设置当前页码和每一页显示的数据条数
		int currpage = 1;
		int pageSize = 6;
		// 分页类的对象
		Pager pg = new Pager();
		pg.setPageSize(pageSize);
		// 获得pageIndex的值
		String pageIndex = req.getParameter("pageIndex");
		// 获得数据库中数据的总数
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
		// 通过当前页面和每一页显示的数据条数获得分页数据
		List<StudentInfo> slist = sis.getStus(currpage, pageSize);
		// 将这些数据设置给分页类的实体
		pg.setCurrpage(currpage);
		pg.setStusList(slist);
		// 将这个分页类保存到作用域中
		req.setAttribute("pg", pg);
		req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
	}
	
}
