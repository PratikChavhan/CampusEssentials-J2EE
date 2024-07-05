package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn = null;
	
	public static Connection getMyConnection() {
		System.out.println("Connecting");
		if (conn == null) {
			try {
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/j2ee?useSSL=false";
				conn = DriverManager.getConnection(url, "root", "root");
				System.out.println("Database connection established: " + (conn != null));
				return conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void closeMyConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
