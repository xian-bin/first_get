package dao;
import entity.User;
public interface UserDao {
	//保存用户信息的方法
	void save(User user);
	
	void update(User user);
}
