package com.xiangR.shopping.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

import com.xiangR.shopping.util.DB;

public class User {

	private int id;
	private String username;
	private String password;
	private String phone;
	private String addr;
	// 为其在初始化即设定时间
	private Date rdate = new Date();
	
	public User() {
		
	}
	
	public User(int id, String username, String password, String phone, String addr) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.addr = addr;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	@Override
	public String toString() {
		return (id + " : " + username + " : "+ password + " : " + addr + " : "+ rdate);
	}

	public void save() {
		/*
		 * 调用 工具类的方法
		 * UserManager.save(this);
		 */
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();	
			String sql = "insert into user values (null, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setString(4, addr);
			ps.setTimestamp(5, new Timestamp(rdate.getTime()));
			int i = ps.executeUpdate();
			System.out.print( "i + " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int id) {
		UserManager.deleteUser(id);
		/*
		 * 
		 */
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
	
	public void update() {
		UserManager.update(this);
		Connection conn = null;
		PreparedStatement ps = null;
	 
		try {
			conn = DB.getConnection();
			String sql = "update user set username = ?, phone = ?, addr = ? where id = " + this.id;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.username);
			ps.setString(2, this.phone);
			ps.setString(3, this.addr);
			int i = ps.executeUpdate();
			System.out.print( "i + " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		User u = new User(5, "xiangrui", "123456", "1575505", "China");
		u.update();
	}
}
