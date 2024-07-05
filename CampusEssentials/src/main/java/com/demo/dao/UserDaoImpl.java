package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.demo.beans.User;

public class UserDaoImpl implements UserDao {
	
	static Connection conn;
	static PreparedStatement insertUser;
	
	static {
		try {
			conn = DBUtil.getMyConnection();
			insertUser = conn.prepareStatement("insert into user(uname, pass, role) values(?, ?, ?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean save(User user) {
		// TODO Auto-generated method stub
		try {
			insertUser.setString(1, user.getUname());
			insertUser.setString(2, user.getPass());
			insertUser.setString(3, user.getRole());
			int check = insertUser.executeUpdate();
			return check > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
