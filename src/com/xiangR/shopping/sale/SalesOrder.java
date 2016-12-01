package com.xiangR.shopping.sale;

import java.sql.*;
import com.xiangR.shopping.client.Cart;
import com.xiangR.shopping.user.User;

public class SalesOrder {
	
	/*
	 * 封装了一个 user 在购物时的 事件 地点 订单状态 以及购物车
	 */
	
	int id;
	User user;
	String addr;
	Timestamp oDate;
	int status;
	
	Cart cart;
	
	public void save() {
		OrderMgr.getInstance().saveOrder(this);
	}
	
	/*
	 *  set get
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Timestamp getoDate() {
		return oDate;
	}
	public void setoDate(Timestamp oDate) {
		this.oDate = oDate;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart c) {
		this.cart = c;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "user ＝　" + this.user.getUsername() + " addr = " + this.addr + " Date = " + this.oDate.getTime() + " status = " + this.status;
	}
	
}
