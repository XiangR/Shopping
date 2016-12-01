package com.xiangR.shopping.sale;
import java.util.List;

import com.xiangR.shopping.dao.OrderDAO;

public class OrderMgr {

	private static OrderMgr om= null;
	OrderDAO dao = null;
	
	private OrderMgr() {
		
	}
	
	static {
		om = new OrderMgr();
		om.setDao(new OrderDAO());
	}

	public static OrderMgr getInstance() {
		return om;
		
	}
	
	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}
	
	// method
	public void saveOrder(SalesOrder so) {
		dao.saveOrder(so);
	}
	
	public SalesOrder loadById(int id) {
		return dao.loadById(id);
	}
	
	public int getOrders(List<SalesOrder>orders, int pageNo, int pageSize) {
		return dao.getOrders(orders, pageNo, pageSize);
	}
	
	public List<SalesItem> getSalesItems(SalesOrder so) {
		return dao.getSalesItems(so);
	}

	public void updateStatus(SalesOrder order) {
		dao.updateStatus(order);
	}
}
