package com.xiangR.shopping.util;

import java.sql.*;

public class DB {
	
	// 数据库驱动
	private static final String driver = "com.mysql.jdbc.Driver";
	// 连接数据库  shopping 是一个库名 要在mysql 中先建立
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&amp;characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "111111";
	private static Connection conn;
	
	// 静态代码块负责加载驱动
	static {
		try{
			Class.forName(driver);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 典型的单例模式 返回连接对象
	 * 我们创建一个 conn 若是其存在则直接返回它，若是不存在 则新建一个
	 */
	public static Connection getConnection() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} else {
			return conn;
		}
	}

	public static void closeConn(ResultSet rs, Statement st, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static PreparedStatement prepare(Connection conn, String sql, boolean generateKey) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pstmt;
	}

	public static Statement getStatement(Connection conn) {
		Statement st = null;

		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

	public static ResultSet getResultSet(Statement st, String sql) {
		ResultSet rs = null;
		
		try {
			st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void close(Connection conn) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstmt) {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement st) {

		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
