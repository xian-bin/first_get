package service;
import java.util.List;
import entity.StudentInfo;

public interface StudentInfoService {
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
	// ��֤�û����Ƿ���ڵķ���
	boolean isExists(String stuName);
	boolean addStu(StudentInfo stu);
	boolean deleteStu(int stuNo);
	StudentInfo findByNo(int stuNo);
	boolean updaStu(StudentInfo stu);
}
