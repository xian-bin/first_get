package dao;
/**
 * ����������ݿ��ķ���
 * @author lindy
 *
 */

import java.util.List;

import entity.StudentInfo;

public interface StudentInfoDao {
	// ������������ķ���
	int getCount();
	/**
	 * 	��÷�ҳ���ݵķ���
	 * @param currpage  ��ǰҳ��
	 * @param pageSize	ÿһҳ��ʾ����������
	 * @return ��ǰҳ����Ҫչʾ�����ݼ���
	 */
	List<StudentInfo> getStus(int currpage,int pageSize);
	
	// ������ݿ����������ݵķ���
	List<StudentInfo> getAll();
	int addStu(StudentInfo stu);
	int deleteStu(int stuNo);
	StudentInfo findByNo(int stuNo);
	int updateStu(StudentInfo stu);
}
