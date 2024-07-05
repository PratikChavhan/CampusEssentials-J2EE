package com.demo.service;

import com.demo.beans.User;
import com.demo.dao.LoginDao;
import com.demo.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService{
	
	LoginDao ldao = new LoginDaoImpl();

	@Override
	public User validateUser(String uname, String pass) {
		User user = ldao.findUser(uname, pass);
		if (user != null) {
			return user;
		}
		return null;
	}

}
