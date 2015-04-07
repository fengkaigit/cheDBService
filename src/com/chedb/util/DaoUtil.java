package com.chedb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtil {
	// ��ȡ���ݿ�����
	static String driver = "com.mysql.jdbc.Driver";
	static String dbUser = "root";
	// �������Ի���
//	static String dbUrl = "jdbc:mysql://localhost:3306/we_world";
//	static String dbPassword = "123456";
	
	// ��ʽ���л���
//	static String dbUrl = "jdbc:mysql://localhost:3306/chedb";
	static String dbUrl = "jdbc:mysql://182.254.156.62:3306/chedb";
	static String dbPassword = "";

	public static Connection getConnection(){
		Connection conn = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// ��ȡ���ݿ����
	public static Statement getStatement(Connection conn){
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

