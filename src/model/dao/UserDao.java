package model.dao;

import model.vo.User;

public interface UserDao {
	void addUser (User user);
	
	void updateUser (User user);
	
	User findUserById(int getMemberId);
	
	User findUserByName(String memberAccount);
	
	User findUserByNameOrEmail(String nameOrEmail);
	
}
