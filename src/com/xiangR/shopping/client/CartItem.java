package com.xiangR.shopping.client;

import com.xiangR.shopping.product.Product;

public class CartItem {

	/**
	 * 购物车类
	 * 封装了 一个 product的对象和count数量
	 */
	
	private Product product;
	private int count;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
