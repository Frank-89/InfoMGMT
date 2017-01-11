package com.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SqlHelper {
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost/StuInfoSystem";
	static final String USER = "root";
	static final String PASS = "root";

	public SqlHelper() {
		try {
			// 1. load jdbc driver
			Class.forName(JDBC_DRIVER);
			// 2. get connectivity
			System.out.println("Connecting to database...");
			ct = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// close database
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ResultSet queryExectue(String sql) {
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return rs;
	}

	// do query operation in database
	public ResultSet queryExectue(String sql, String[] paras) {
		try {
			// 3. create prepareStatement
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return rs;
	}

	public boolean updExecute(String sql, String[] paras) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			if (ps.executeUpdate() != 1) {
				b = false;
			}
		} catch (Exception e) {
			b = false;
			JOptionPane.showMessageDialog(null, "Data is wrong, uername or password of database is wrong", "Database connectivity error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}
}
