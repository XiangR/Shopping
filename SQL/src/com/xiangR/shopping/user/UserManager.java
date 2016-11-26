package com.xiangR.shopping.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xiangR.shopping.exception.PasswordNotCorrectException;
import com.xiangR.shopping.exception.UserNotFoundException;
import com.xiangR.shopping.util.DB;

public class UserManager {
	
	/**
	 *  static 得到一个 User 集合
	 * @return
	 */
	public static List<User> getUser() {
		
		List<User> list = new ArrayList<User>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from user;";
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setAddr(rs.getString("addr"));
				u.setRdate(rs.getTimestamp("rdate"));
				u.setId(rs.getInt("id"));
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	/**
	 * 由id 删除指定的User
	 * @param id
	 */
	public static void deleteUser(int id) {
		
		Connection conn = null;
		Statement st = null;
		
		String sql = "delete from user where id = " + id;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加指定的对象到数据库中
	 * @param u
	 */
	public static void save(User u) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();	
			String sql = "insert into user values (null, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getPhone());
			ps.setString(4, u.getAddr());
			ps.setTimestamp(5, new Timestamp(u.getRdate().getTime()));
			int i = ps.executeUpdate();
			System.out.print( "i + " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新一个指定的对象
	 * @param u
	 */
	public static void update(User u) {
		// 使用数据库 update的形式 
		Connection conn = null;
		PreparedStatement ps = null;
	 
		try {
			conn = DB.getConnection();
			String sql = "update user set username = ?, phone = ?, addr = ? where id = " + u.getId();
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPhone());
			ps.setString(3, u.getAddr());
			int i = ps.executeUpdate();
			System.out.print( "i + " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 由 用户名和密码 来确认是否为数据库中的 User 且我们在这里自定义了一个异常类 来抛出 账户密码异常的错误
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 * @throws PasswordNotCorrectException
	 */
	public static User validate(String username, String password) throws UserNotFoundException, PasswordNotCorrectException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		
		try {
			conn = DB.getConnection();
			String sql = "select * from user where username = '" + username + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();				
			// 若是存在则rs.next 为 true
			if(!rs.next()) {
				throw new UserNotFoundException();
			} else if(!rs.getString("password").equals(password)){
				throw new PasswordNotCorrectException();
			} else {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setAddr(rs.getString("addr"));
				u.setRdate(rs.getTimestamp("rdate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	// 封装的检测用户输入的函数但是我们使用了一种更好的方式 定义异常
	public static boolean userExists(String username) {
		
		List<User> list = UserManager.getUser();
		Iterator<User> users = list.iterator();
		
		while(users.hasNext()) {
			if(users.next().getUsername() == username ) {
				return true;
			}
		}
		
		return false;
	}
	public static boolean isPasswordCorrect(String username, String password) {

		List<User> list = UserManager.getUser();
		Iterator<User> users = list.iterator();
		
		while(users.hasNext()) {
			User u = users.next();
			if( u.getUsername() == username ) { 
				if(u.getPassword() == password) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
