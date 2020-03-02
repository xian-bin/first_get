package service.impl;

import java.util.List;

import dao.StudentInfoDao;
import dao.impl.StudentInfoDaoImpl;
import entity.StudentInfo;
import service.StudentInfoService;

public class StudentInfoServiceImpl implements StudentInfoService {
	// ����һ���ӿڵ�ʵ�������
	private StudentInfoDao sdao = new StudentInfoDaoImpl();
	@Override
	public int getCount() {
		return sdao.getCount();
	}

	@Override
	public List<StudentInfo> getStus(int currpage, int pageSize) {
		return sdao.getStus(currpage, pageSize);
	}

	@Override
	public List<StudentInfo> getAll() {
		return sdao.getAll();
	}

	@Override
	public boolean isExists(String stuName) {
		// ʹ�ô��ݵ��û��������ݿ��б�����û������бȽ�
		List<StudentInfo> list = getAll();
		// ʹ��ѭ��������������
		for (StudentInfo studentInfo : list) {
			if(stuName.equals(studentInfo.getStuName())) {
				// ��ʾ����
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addStu(StudentInfo stu) {
		int result = sdao.addStu(stu);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteStu(int stuNo) {
		int result = sdao.deleteStu(stuNo);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public StudentInfo findByNo(int stuNo) {
		// TODO Auto-generated method stub
		return sdao.findByNo(stuNo);
	}

	@Override
	public boolean updaStu(StudentInfo stu) {
		int result = sdao.updateStu(stu);
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

}
