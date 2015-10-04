package model.dao.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import model.dao.UserDao;
import model.vo.User;

public class UserDAOjdbc implements UserDao {
	
	private static UserDAOjdbc instance = new UserDAOjdbc();
	
	private UserDAOjdbc(){}
	
	public static UserDAOjdbc getInstance(){
		return instance;
	}

	  Map<Integer,User> users = new HashMap<Integer, User>();  
	
	int nextId = 1;
	
	@Override
	public void addUser(User user) {
		user.setMemberId(nextId++);
		user.setRandomCode(UUID.randomUUID().toString());
		users.put(user.getMemberId(),user);
	}

	@Override
	public void updateUser(User user) {
		users.put(user.getMemberId(),user);

	}

	@Override
	public User findUserById(int getMemberId) {		
		return users.get(getMemberId);
	}

	@Override
	public User findUserByName(String memberAccount) {
		 Collection<User> userValuse =users.values();
		 for(Iterator<User>iterator = userValuse.iterator();iterator.hasNext();){
			 User user = iterator.next();
			if(user.getMemberAccount().equals(memberAccount)){
				return user;
			}
		 }
		return null;
	}

	@Override
	public User findUserByNameOrEmail(String nameOrEmail) {
		 Collection<User> userValuse =users.values();
		 for(Iterator<User>iterator = userValuse.iterator();iterator.hasNext();){
			 User user = iterator.next();	 
			if(user.getMemberEmail().equals(nameOrEmail)|| user.getMemberAccount().equals(nameOrEmail)){
				 return user;
			 }
		 }	
		return null;
	}

}
