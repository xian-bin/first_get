package service;
import java.util.List;
import entity.StudentInfo;

public interface StudentInfoService {
	// 获得数据总数的方法
	int getCount();
	/**
	 * 	获得分页数据的方法
	 * @param currpage  当前页码
	 * @param pageSize	每一页显示的数据条数
	 * @return 当前页所需要展示的数据集合
	 */
	List<StudentInfo> getStus(int currpage,int pageSize);
	
	// 获得数据库中所有数据的方法
	List<StudentInfo> getAll();
	// 验证用户名是否存在的方法
	boolean isExists(String stuName);
	boolean addStu(StudentInfo stu);
	boolean deleteStu(int stuNo);
	StudentInfo findByNo(int stuNo);
	boolean updaStu(StudentInfo stu);
}
