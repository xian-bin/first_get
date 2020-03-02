package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {
	//业务层调用数据访问层的数据
	UserDao dao = new UserDaoImpl();
	@Override
	public void save(User user) {
		// 使用数据访问层中的类，调用它的方法来诗选数据的添加
		dao.save(user);
	}

}
