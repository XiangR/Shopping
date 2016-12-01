package com.xiangR.shopping.dao;

import java.sql.*;
import java.util.*;

import com.xiangR.shopping.client.*;
import com.xiangR.shopping.product.Product;
import com.xiangR.shopping.sale.SalesItem;
import com.xiangR.shopping.sale.SalesOrder;
import com.xiangR.shopping.user.User;
import com.xiangR.shopping.util.DB;

public class OrderDAO {
	
	/**
	 * �����е�SalesOrder ���ŵ����ݿ��� 
	 * �� SalesOrder ��Ϊ���� �ڸ�ֵ�����ݿ��е�salesorder���ʱ�� ͬʱҪʹ�ô�salesorder��id ��Ϊsalesitem �� orderid 
	 * @param so
	 */
	public void saveOrder(SalesOrder so) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDetail = null;
		ResultSet rsKey = null;
		
		try {
			conn = DB.getConnection();
			// ������
			conn.setAutoCommit(false);
			
			String sql = "insert into salesorder values (null, ?, ?, ?, ?)";
			pstmt = DB.prepare(conn, sql, true);
			pstmt.setInt(1, so.getUser().getId());
			pstmt.setString(2, so.getAddr());
			pstmt.setTimestamp(3, so.getoDate());
			pstmt.setInt(4, so.getStatus());
			pstmt.executeUpdate();

			// �����һ���� order��id ����������ȡ�����id Ҫ�õ������ɵ����key�洢����
			rsKey = pstmt.getGeneratedKeys();
			rsKey.next();
			int key = rsKey.getInt(1);
			
			String sqlDetail = "insert into salesitem values (null, ?, ?, ?, ?)";
			pstmtDetail = conn.prepareStatement(sqlDetail);
			Cart cart = so.getCart();
			List<CartItem> items = cart.getItems(); 
			for(int i = 0; i < items.size(); i ++) {
				CartItem ci = items.get(i);
				pstmtDetail.setInt(1, ci.getProduct().getId());
				pstmtDetail.setDouble(2, ci.getProduct().getMemberPrice());
				pstmtDetail.setInt(3, ci.getCount());
				pstmtDetail.setInt(4, key);
				pstmtDetail.addBatch();			// ��Ӷ�����
			}
			pstmtDetail.executeBatch();
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	
	}
	
	/**
	 * ʹ�ñ����ӵ���ʽ���õ�һ��user ���е� <List>salesorder 
	 * @param orders
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public int getOrders(List<SalesOrder>orders, int pageNo, int pageSize) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rsCount = null;
		int pageCount = 0;
		try {
			conn = DB.getConnection();
			String sql2 = "select count(*) from salesorder";
			ps = conn.prepareStatement(sql2);
			rsCount = ps.executeQuery();
			rsCount.next();
			pageCount = rsCount.getInt(1);
			
			// ���ű������
			String sql = "select salesorder.id, salesorder.userid, salesorder.odate, salesorder.addr, salesorder.status , "
					+ " user.id uid, user.username, user.password, user.addr uaddr, user.phone, user.rdate from salesorder "
					+ " left join user on (salesorder.userid = user.id) limit " + (pageNo - 1) * pageSize + "," + pageSize;
			conn = DB.getConnection();
			ps2 = conn.prepareStatement(sql);
			rs = ps2.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setAddr(rs.getString("uaddr"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setRdate(rs.getTimestamp("rdate"));
				
				SalesOrder so = new SalesOrder();
				so.setId(rs.getInt("id"));
				so.setAddr(rs.getString("addr"));
				so.setoDate(rs.getTimestamp("odate"));
				so.setStatus(rs.getInt("status"));
				so.setUser(u);
				
				orders.add(so);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}

	/**
	 * �ҵ�ָ����id�� salesorder �����ش˶���
	 * @param id 
	 * @return SalesOrder
	 */
	public SalesOrder loadById(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SalesOrder so = null;
		String sql = "select salesorder.id, salesorder.userid, salesorder.odate, salesorder.addr, salesorder.status , " +
	 			 	 " user.id uid, user.username, user.password, user.addr uaddr, user.phone, user.rdate from salesorder " +
	 			     " join user on (salesorder.userid = user.id) where salesorder.id = " + id;  
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setAddr(rs.getString("uaddr"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setRdate(rs.getTimestamp("rdate"));
				
				so = new SalesOrder();
				so.setId(rs.getInt("id"));
				so.setAddr(rs.getString("addr"));
				so.setoDate(rs.getTimestamp("odate"));
				so.setStatus(rs.getInt("status"));
				so.setUser(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return so;
	}

	/**
	 * �ҵ�ָ�� SalesOrder�����е�SalesItem 
	 * @param so
	 * @return List<SalesItem>
	 */
	public List<SalesItem> getSalesItems(SalesOrder so) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SalesItem> items = new ArrayList<SalesItem>();
		// ������������ȡ������ ʹ�õ��Ǹ���ģʽ
		String sql = " select salesorder.id, salesorder.userid, salesorder.addr, salesorder.odate, salesorder.status," + 
					 " salesitem.id itemid, salesitem.productid, salesitem.unitprice, salesitem.pcount, salesitem.orderid, " +
					 " product.id pid, product.name, product.descr, product.normalprice, product.memberprice, product.pdate, product.categoryid" +
					 " from salesorder join salesitem on (salesorder.id = salesitem.orderid)" +
					 " join product on (salesitem.productid = product.id) where salesorder.id = " + so.getId();
		
		try {
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
				
				SalesItem si = new SalesItem();
				si.setOrder(so);
				si.setId(rs.getInt("itemid"));
				si.setUnitPrice(rs.getDouble("unitprice"));
				si.setCount(rs.getInt("pcount"));
				si.setProduct(p);
				
				items.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	/**
	 * ��һ��order ��Ϊ����ȥ�������ݿ��е�order
	 * @param order
	 */
	public void updateStatus(SalesOrder order) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			String sql = "update salesorder set status = " + order.getStatus() + " where id = " + order.getId();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//List<SalesOrder> orders = new ArrayList<SalesOrder>();
		//int count = new OrderDAO().getOrders(orders, 1, 2);
		
		List<SalesItem> items = new ArrayList<SalesItem>();
		SalesOrder so = new OrderDAO().loadById(4);
		System.out.println(so.getId());
		items = new OrderDAO().getSalesItems(so);
		System.out.println(items);
	}
}


