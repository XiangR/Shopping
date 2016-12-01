package com.xiangR.shopping.category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.xiangR.shopping.util.DB;

public class CategoryDAO {
	// dao ����һ��ʵ���������ݿ���н����ķֲ㣬���ǵ�Ŀ�����ø��߻��ԣ������Ǹ������ݿ������ʱ������ֻ��Ҫ��DAO�����һ������Ϳ�����
	
	/**
	 * ����id ���õ� Category
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
	 * �õ��ڵ�����ӽڵ�
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
	// ������ķ������� ����ֹ��������� conn ����
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
	 * �� id ɾ��ָ���� Category �����ӽڵ�
	 * @param id
	 */
	public static void deleteCategory(int id) {
		
		Category parent = CategoryDAO.loadById(id);
		System.out.println(parent.isLeaf() + ": " + parent.getPid());
		if(!parent.isLeaf()) {
			// ����ʹ�� �� ���ܽ�ȥ �Һ����� and why��
			// ɾ���ӽڵ�
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
	 * ɾ���ƶ����� �����ӽڵ�
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
	 * ɾ��ͬ����ָ��id �� �����ӽڵ�
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
	 * ����һ�� ����Ľ��
	 * @param name
	 * @param descr
	 */
	public static void addTopCategeory(String name, String descr) {
			
		addChildCategeory(0, name, descr);
		// ����������ѡ�����һ�������� category	
	/*	Category c = new Category();
		c.setId(-1);					// ��Ϊϵͳ���Զ������������ǿ���ѡ��һ�������ϳ���Ĳ���
		c.setName(name);
		c.setDescr(descr);
		c.setLeaf(true);
		c.setPid(0);					// û�нڵ�
		c.setGrade(1);					// ��������Լ���Ϊ��һ��
		CategoryDAO.save(c);
		*/
	}
	
	public static void add(Category c) {
		CategoryDAO.save(c);
	}
	
	/**
	 * ����һ���ڵ�
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
	 * ����һ������
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
			System.out.println(c.getName() +" :��"+ c.getDescr());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addChilde(Category c) {
		addChildCategeory(c.getId(), c.getName(), c.getDescr());
	}
	
	/**
	 * �� pid ��  name descr ��ӽڵ�
	 * ���� pid Ϊ 0 �����Ϊ  һ���ڵ�
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
			// �����µ� Category use grade
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, name);
			ps2.setString(2, descr);
			ps2.setInt(3, pid);
			ps2.setInt(4, 0);
			ps2.setInt(5, parentGrade + 1);
			ps2.executeUpdate();
			// ���¸��ڵ�
			ps3 = conn.prepareStatement(sql3);
			ps3.executeUpdate();
			conn.setAutoCommit(true);
			System.out.println("pid = " + pid + "; name = " + name + "; descr = " + descr + "; parentGrade = " + parentGrade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
