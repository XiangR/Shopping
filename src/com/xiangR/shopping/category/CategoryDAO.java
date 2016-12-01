package com.xiangR.shopping.category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.xiangR.shopping.util.DB;

public class CategoryDAO {
	// dao 层是一个实体类与数据库进行交互的分层，我们的目的是让更具活性，在我们更换数据库操作的时候我们只需要对DAO层进行一个处理就可以了
	
	/**
	 * 根据id 来得到 Category
	 * @param id
	 * @return Object Category
	 */
	public static Category loadById(int id) {
	
		Connection conn = null;
		Statement stmt = null;
		Category c = null;
		try {
			conn = DB.getConnection();
			String sql = "select * from category where id = " + id;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new Category();
				c.setId(rs.getInt("id"));
				c.setPid(rs.getInt("pid"));
				c.setName(rs.getString("name"));
				c.setDescr(rs.getString("descr"));
				c.setLeaf(rs.getInt("isleaf") == 0 ? true : false);
				c.setGrade(rs.getInt("grade"));
			//	System.out.println(c.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	public static List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		CategoryDAO.getCategories(categories, 0);
		return categories;
	}
	
	/**
	 * 得到节点和其子节点
	 * @param list
	 * @param id
	 */
	private static void getCategories(List<Category> list, int id) {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			getCategories(conn, list, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 由上面的方法调用 来防止建立过多的 conn 连接
	private static void getCategories(Connection conn, List<Category> list, int id) {
		Statement stmt = null;
		try {
			conn = DB.getConnection();
			String sql = "select * from category where pid = " + id;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setPid(rs.getInt("pid"));
				c.setName(rs.getString("name"));
				c.setDescr(rs.getString("descr"));
				c.setLeaf(rs.getInt("isleaf") == 0 ? true : false);
				c.setGrade(rs.getInt("grade"));
				list.add(c);
				if(!c.isLeaf()) {
					getCategories(list, c.getId());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 由 id 删除指定的 Category 及其子节点
	 * @param id
	 */
	public static void deleteCategory(int id) {
		
		Category parent = CategoryDAO.loadById(id);
		System.out.println(parent.isLeaf() + ": " + parent.getPid());
		if(!parent.isLeaf()) {
			// 这里使用 非 才能进去 我很无语 and why？
			// 删除子节点
			deleteChildCategory(id);
		}
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DB.getConnection();
			String sql = "delete from category where id = " + id;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除制定对象 及其子节点
	 * @param c
	 */
	public static void deleteCategory(Category c) {
		if(c.isLeaf()) {
			int id = c.getId();
			deleteChildCategory(id);			
		}
		deleteCategory(c.getId());
	}
	
	/**
	 * 删除同属于指定id 的 所有子节点
	 * @param id
	 */
	private static void deleteChildCategory(int id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DB.getConnection();
			String sql = "delete from category where pid = " + id;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生产一个 顶层的结点
	 * @param name
	 * @param descr
	 */
	public static void addTopCategeory(String name, String descr) {
			
		addChildCategeory(0, name, descr);
		// 这里是我们选择添加一个顶部的 category	
	/*	Category c = new Category();
		c.setId(-1);					// 因为系统的自动生成所以我们可以选择一个不符合常规的参数
		c.setName(name);
		c.setDescr(descr);
		c.setLeaf(true);
		c.setPid(0);					// 没有节点
		c.setGrade(1);					// 最根上所以级别为第一级
		CategoryDAO.save(c);
		*/
	}
	
	public static void add(Category c) {
		CategoryDAO.save(c);
	}
	
	/**
	 * 加入一个节点
	 * @param c
	 */
	public static void save(Category c) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			String sql = "";
			if(c.getId() == -1) {
				sql = "insert into category values (null, ?, ?, ?, ?, ?)";
			} else {
				sql = "insert into category values (" + c.getId() + ", ?, ?, ?, ?, ?)";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescr());
			ps.setInt(3, c.getPid());
			ps.setInt(4, c.isLeaf() ? 0 : 1);
			ps.setInt(5, c.getGrade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新一个对象
	 * @param c
	 */
	public static void updateCategory(Category c) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			String sql = "update category set name = ?, descr = ? where id = " + c.getId();
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescr());
			ps.executeUpdate();
			System.out.println(c.getName() +" :　"+ c.getDescr());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addChilde(Category c) {
		addChildCategeory(c.getId(), c.getName(), c.getDescr());
	}
	
	/**
	 * 由 pid 和  name descr 添加节点
	 * 若是 pid 为 0 则添加为  一级节点
	 * @param pid
	 * @param name
	 * @param descr
	 */
	public static void addChildCategeory(int pid, String name, String descr) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		
		try {
			String sql1 = "select * from category where id = " + pid;
			String sql2 = "insert into category values (null, ?, ?, ?, ?, ?)";
			String sql3 = "update category set isleaf = 1 where id = " + pid; 
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			int parentGrade = 0;
			if(rs.next()) {
				parentGrade = rs.getInt("grade");
			}
			// 储存新的 Category use grade
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, name);
			ps2.setString(2, descr);
			ps2.setInt(3, pid);
			ps2.setInt(4, 0);
			ps2.setInt(5, parentGrade + 1);
			ps2.executeUpdate();
			// 更新父节点
			ps3 = conn.prepareStatement(sql3);
			ps3.executeUpdate();
			conn.setAutoCommit(true);
			System.out.println("pid = " + pid + "; name = " + name + "; descr = " + descr + "; parentGrade = " + parentGrade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
