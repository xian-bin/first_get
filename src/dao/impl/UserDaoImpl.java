package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import entity.User;
/**
 * ʵ��dao�ӿ��з�����һ��������
 * 
 */
public class UserDaoImpl implements UserDao {

	@Override
	public void save(User user) {
		// ����һ�������������û���Ϣ
	List<User> list = new ArrayList<User>();
		list.add(user);
		for(User user2 : list) {
			System.out.println(user2.getName()+","
		+user2.getAge());
		}

	}
	
	@Override
	public void update(User user) {
		System.out.println("ִ���޸��û���Ϣ�Ĳ�����");
	}
	
}
