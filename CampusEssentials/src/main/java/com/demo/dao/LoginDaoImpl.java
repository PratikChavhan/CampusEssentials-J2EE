package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.beans.User;

public class LoginDaoImpl implements LoginDao{
	
	static Connection conn;
	static PreparedStatement selUser;
	
	static {
		try {
			conn = DBUtil.getMyConnection();
			selUser = conn.prepareStatement("select uname, pass, role from user where uname = ? and pass = ?"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUser(String uname, String pass) {
		// TODO Auto-generated method stub
		try {
			selUser.setString(1, uname);
			selUser.setString(2, pass);
			ResultSet rs = selUser.executeQuery();
			if(rs.next()) {
				return new User(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
