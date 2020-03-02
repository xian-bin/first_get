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
		// ����������е�ֵ
		String op = req.getParameter("op");
		if("add".equals(op)) {
			// �������ӵķ���
			addStu(req,resp);
		}else if("update".equals(op)){
			// �����޸ĵķ���
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
		// ����ļ��ϴ���·��
		String filePath = this.getServletContext().getRealPath("/statics/img");
		// �鿴�Ƿ���
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart) {
			// ����һ����������
			FileItemFactory fac = new DiskFileItemFactory();
			// ����һ���ļ��ϴ��Ķ���
			ServletFileUpload upload = new ServletFileUpload(fac);
			// ��ñ��е��������� ����10M
			upload.setFileSizeMax(10*1024*1024);
			try {
				List<FileItem> items = upload.parseRequest(req);
				// �������е���������
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					// ȡ��Ԫ�ض���
					FileItem item = it.next();
					// �ж��Ƿ�����ͨ�ı�Ԫ��
					if(item.isFormField()) {
						String name = item.getFieldName();// �õ�����nameֵ
						// ����nameֵ��Ϊ����ı�����ֵ
						switch (name) {
						case "stuNo":
							// ȡ��pname��ֵ
							stuNo = item.getString("UTF-8");
							break;
						case "stuName":
							// ȡ��pname��ֵ
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
					}else {// �ϴ����ı�
						headImg = item.getName();
						// ����һ���ϴ��ļ��Ķ���
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
		// ��̨���ǿ���֤
		
		if(StringUtils.isNullOrEmpty(stuName)) {
			msg = "�û���Ϊ��";
			// �����������浽session��
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		// ��ñ�����session�е���֤��
		String saveCode  = (String)req.getSession().getAttribute("yzm");
		if(!code.equals(saveCode)) {
			msg = "��֤�벻һ��";
			// �����������浽session��
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		
		// ����һ�������浽���ݿ��е�ʵ�����
		StudentInfo stu = new StudentInfo(Integer.parseInt(stuNo),stuName, Integer.parseInt(gender), Integer.parseInt(age), email, headImg);
		// ����ҵ����б������ݵķ���
		StudentInfoService sis = new StudentInfoServiceImpl();
		boolean isOk = sis.addStu(stu);
		if(isOk) {
			resp.sendRedirect("ServerView");
		}else {
			msg = "��������ʧ��";
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
		// ����ļ��ϴ���·��
		String filePath = this.getServletContext().getRealPath("/statics/img");
		// �鿴�Ƿ���
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart) {
			// ����һ����������
			FileItemFactory fac = new DiskFileItemFactory();
			// ����һ���ļ��ϴ��Ķ���
			ServletFileUpload upload = new ServletFileUpload(fac);
			// ��ñ��е��������� ����10M
			upload.setFileSizeMax(10*1024*1024);
			try {
				List<FileItem> items = upload.parseRequest(req);
				// �������е���������
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					// ȡ��Ԫ�ض���
					FileItem item = it.next();
					// �ж��Ƿ�����ͨ�ı�Ԫ��
					if(item.isFormField()) {
						String name = item.getFieldName();// �õ�����nameֵ
						// ����nameֵ��Ϊ����ı�����ֵ
						switch (name) {
						case "stuName":
							// ȡ��pname��ֵ
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
					}else {// �ϴ����ı�
						headImg = item.getName();
						// ����һ���ϴ��ļ��Ķ���
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
		// ��̨���ǿ���֤
		if(StringUtils.isNullOrEmpty(stuName)) {
			msg = "�û���Ϊ��";
			// �����������浽session��
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		// ��ñ�����session�е���֤��
		String saveCode  = (String)req.getSession().getAttribute("yzm");
		if(!code.equals(saveCode)) {
			msg = "��֤�벻һ��";
			// �����������浽session��
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
			return;
		}
		
		// ����һ�������浽���ݿ��е�ʵ�����
		StudentInfo stu = new StudentInfo(stuName, Integer.parseInt(gender), Integer.parseInt(age), email, headImg);
		// ����ҵ����б������ݵķ���
		StudentInfoService sis = new StudentInfoServiceImpl();
		boolean isOk = sis.updaStu(stu);
		if(isOk) {
			resp.sendRedirect("ServerView");
		}else {
			msg = "��������ʧ��";
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("server/addStu.jsp");
		}
	}
	
}
