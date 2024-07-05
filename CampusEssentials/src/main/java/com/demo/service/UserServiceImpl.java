package com.demo.service;

import com.demo.beans.User;
import com.demo.dao.UserDao;
import com.demo.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{
	
	UserDao udao = new UserDaoImpl();

	@Override
	public boolean addUser(User user) {
		
		return udao.save(user);
	}

}
