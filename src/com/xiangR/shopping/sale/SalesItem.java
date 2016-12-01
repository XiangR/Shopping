package com.xiangR.shopping.sale;

import com.xiangR.shopping.product.Product;

public class SalesItem {
	/*
	 * ���۳���Ʒ����ϸ��Ϣ
	 * ��װ��һ�����۳���Ʒ������ Product �� �����ڵ�SalesOrder
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
