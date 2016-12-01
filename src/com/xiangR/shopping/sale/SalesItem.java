package com.xiangR.shopping.sale;

import com.xiangR.shopping.product.Product;

public class SalesItem {
	/*
	 * 所售出物品的详细信息
	 * 封装了一个所售出物品所属的 Product 和 其所在的SalesOrder
	 */
	private int id;
	private Product product;
	private double unitPrice;
	private int count;
	private SalesOrder order;
	
	public SalesOrder getOrder() {
		return order;
	}
	public void setOrder(SalesOrder order) {
		this.order = order;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
