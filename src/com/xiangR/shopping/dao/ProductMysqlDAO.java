package com.xiangR.shopping.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.xiangR.shopping.category.Category;
import com.xiangR.shopping.product.Product;
import com.xiangR.shopping.util.DB;

public class ProductMysqlDAO implements ProductDAO{
	// 声明为一个全局变量 作为一个取数据的缓存
	//List<Product> products = null;
	
	// 设定一个全局的 sql 字符串用作查询的分页 其在 复杂查询的时候被赋值
	static String searchSql = "";
	
	public List<Product> getProducts() {
		List<Product> products = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from product";
			products = new ArrayList<Product>();
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setNormalPrice(rs.getDouble("normalPrice"));
				p.setMemberPrice(rs.getDouble("memberPrice"));
				p.setPdate(rs.getTimestamp("pdate"));
				p.setCategoryId(rs.getInt("categoryId"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Product> getProducts(int pageNo, int pageSize) {
		List<Product> products = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			//String sql = "select * from product limit " + (pageNo - 1)*pageSize + ',' + pageSize;
			String sql = "select p.id productid, p.name pname, p.descr pdescr, p.normalPrice, " +
					" p.memberPrice, p.pdate, p.categoryId , " +
					" c.id categoryid, c.name cname, c.descr cdescr, c.pid, c.isleaf, c.grade " +
					" from product p join category c on (p.categoryId = c.id) limit " + (pageNo - 1)*pageSize + ',' + pageSize;
			products = new ArrayList<Product>();
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setNormalPrice(rs.getDouble("normalPrice"));
				p.setMemberPrice(rs.getDouble("memberPrice"));
				p.setPdate(rs.getTimestamp("pdate"));
				p.setCategoryId(rs.getInt("categoryId"));
				products.add(p);
				
				Category c = new Category();
				c.setId(rs.getInt("categoryid"));
				c.setName(rs.getString("cname"));
				c.setDescr(rs.getString("cdescr"));
				c.setPid(rs.getInt("pid"));
				c.setGrade(rs.getInt("grade"));
				c.setLeaf(rs.getInt("leaf") == 0 ? true : false);
				p.setC(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public int sizeProducts() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rsCount = null;
		int sizeCount = 0;
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(searchSql);
			rsCount = ps.executeQuery();
			rsCount.next();
			sizeCount = rsCount.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sizeCount;
	}
	public List<Product> findProducts(int[] categoryId,
									  String keyWord,
									  double lowNormalPrice, 
									  double highNormalPrice,
									  double lowMemberPrice, 
									  double highMemberPrice, 
									  Date startDate,
									  Date endDate, 
									  int pageNo, 
									  int pageSize) {
		
		List<Product> products = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from product where 1=1 ";
			searchSql = "select count(*) from product where 1=1 ";
			// categoryid 具有较强的筛选力度
			String strId = "";
			if(categoryId != null && categoryId.length > 0) {
				strId += "(";
				for(int i = 0; i < categoryId.length; i ++) {
					if( i < categoryId.length - 1) {
						strId += categoryId[i] + ",";
					} else {
						strId += categoryId[i];
					}
				}
				strId += ")";
				sql += " and categoryid in " + strId;
				searchSql += " and categoryid in " + strId;
			}
			
			if(keyWord != null && !keyWord.trim().equals("")) {
				sql += " and name like '%" + keyWord +"%' or descr like '%" + keyWord + "%'";
				searchSql += " and name like '%" + keyWord +"%' or descr like '%" + keyWord + "%'";
			}
			
			if(lowNormalPrice >= 0 ) {
				sql += " and lowNormalPrice > " + lowNormalPrice;
				searchSql += " and lowNormalPrice > " + lowNormalPrice;
			}
			
			if(highNormalPrice > 0 ) {
				sql += " and highNormalPrice < " + highNormalPrice;
				searchSql += " and highNormalPrice < " + highNormalPrice;
			}
			
			if(lowMemberPrice >= 0 ) {
				sql += " and lowMemberPrice > " + lowMemberPrice;
				searchSql += " and lowMemberPrice > " + lowMemberPrice;
			}
			
			if(highMemberPrice > 0 ) {
				sql += " and highMemberPrice < " + highMemberPrice;
				searchSql += " and highMemberPrice < " + highMemberPrice;
			}
			
			if( startDate != null) {
				sql += " and pdate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(startDate) + "'";
				searchSql += " and pdate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(startDate) + "'";
			}
			
			if( endDate != null) {
				sql += " and pdate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(endDate) + "'";
				searchSql += " and pdate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(endDate) + "'";
			}
			
			sql += " limit " + (pageNo - 1)*pageSize + "," + pageSize;
		
	// 		" * " 是一个 特殊字符 加上反斜杠		
	//		searchSql = sql.replaceFirst("select \\* ", "select count (*)"); 使用字符串的替换功能
System.out.println("sql = " + sql);
System.out.println("searchSql = " + searchSql);

			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setNormalPrice(rs.getDouble("normalPrice"));
				p.setMemberPrice(rs.getDouble("memberPrice"));
				p.setPdate(rs.getTimestamp("pdate"));
				p.setCategoryId(rs.getInt("categoryId"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Product> findProducts(String name) {
		return null;
	}

	public boolean deleteProductByCategoryById(int categoryId) {
		return false;
	}

	public boolean deleteProductsById(int[] idArray) {
		return false;
	}

	public boolean updateProduct(Product p) {
		return false;
	}

	public boolean addProduct(Product p) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			//String sql = "insert into product values (1, 'xiangrui', 'test', '18', '15', '2016-11-24 22:35:16.948', '17')";
			
			String sql = "insert into product values (null, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getDescr());
			ps.setDouble(3, p.getNormalPrice());
			ps.setDouble(4, p.getMemberPrice());
			ps.setTimestamp(5, p.getPdate());
			ps.setInt(6, p.getCategoryId());
			System.out.println(p.getId() + "--" +  p.getName() + "--" + p.getPdate() + "--" + p.getDescr() + "--" + p.getMemberPrice());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		//List<Product> products = new ArrayList<Product>();
//		new ProductMysqlDAO().addProduct(new Product());
	
	/*	List<Product> products = new ProductMysqlDAO().getProducts();
		for(Iterator<Product> it = products.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
		
		for(Iterator<Product> it = products.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		String sql = "select count(*) from product where 1=1  and categoryid in (17)";
		System.out.println(new ProductMysqlDAO().sizeProducts(sql));
		
		*/
		
	}

	public int getProducts(List<Product> products, int pageNo, int pageSize) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rsCount = null;
		int pageCount = 0;
		try {
			conn = DB.getConnection();
			String sql2 = "select count(*) from product";
			ps = conn.prepareStatement(sql2);
			rsCount = ps.executeQuery();
			rsCount.next();
			pageCount = rsCount.getInt(1);
			
			
			//String sql = "select * from product limit " + (pageNo - 1)*pageSize + ',' + pageSize;
			String sql = "select p.id productid, p.name pname, p.descr pdescr, p.normalPrice, p.memberPrice, p.pdate, p.categoryId , c.id categoryid, c.name cname, c.descr cdescr, c.pid, c.isleaf, c.grade from product p join category c on (p.categoryId = c.id) limit " + (pageNo - 1)*pageSize + ',' + pageSize;
			conn = DB.getConnection();
			ps2 = conn.prepareStatement(sql);
			rs = ps2.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("productid"));
				p.setName(rs.getString("pname"));
				p.setDescr(rs.getString("pdescr"));
				p.setNormalPrice(rs.getDouble("normalPrice"));
				p.setMemberPrice(rs.getDouble("memberPrice"));
				p.setPdate(rs.getTimestamp("pdate"));
				p.setCategoryId(rs.getInt("categoryId"));
				products.add(p);
				
				Category c = new Category();
				c.setId(rs.getInt("categoryid"));
				c.setName(rs.getString("cname"));
				c.setDescr(rs.getString("cdescr"));
				c.setPid(rs.getInt("pid"));
				c.setGrade(rs.getInt("grade"));
				c.setLeaf(rs.getInt("isleaf") == 0 ? true : false);
				p.setC(c);
			}
		/*	
			String sql1 = "select * from product limit " + (pageNo - 1)*pageSize + ',' + pageSize;
			conn = DB.getConnection();
			ps2 = conn.prepareStatement(sql1);
			rs = ps2.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setNormalPrice(rs.getDouble("normalPrice"));
				p.setMemberPrice(rs.getDouble("memberPrice"));
				p.setPdate(rs.getTimestamp("pdate"));
				p.setCategoryId(rs.getInt("categoryId"));
				products.add(p);
			}
		*/	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}
}
