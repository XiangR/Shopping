package com.xiangR.shopping.client;

import java.util.*;


public class Cart{
	
	List<CartItem> items = new ArrayList<CartItem>();
	
	/**
	 * add to cart
	 * @param ci
	 */
	public void addItem(CartItem ci) {
		for(Iterator<CartItem> it = items.iterator(); it.hasNext(); ) {
			CartItem ct = it.next();
			if(ct.getProduct().getId() == ci.getProduct().getId()) {
				ct.setCount(ct.getCount() + 1);
				return;
			} 
		}
		items.add(ci);
	}
	
	public List<CartItem> getItems() { 
		return items;
	}
	
	public void setItems(List<CartItem> list) {
		items = list;
	}
	
	/**
	 * delete the Item use the categoryid of product
	 * @param id
	 */
	public void deletItem(int id) {
		for(Iterator<CartItem> list = items.iterator(); list.hasNext(); ) {
			CartItem ct = list.next();
			if(ct.getProduct().getId() == id ) {
				//items.remove(ct);
				list.remove();
			}
		}
	}
	
	/**
	 * 得到所有商品的 Normal 价格
	 * @return
	 */
	public double getTotalNormalPrice() {
		double price = 0;
		
		for(Iterator<CartItem> list = items.iterator(); list.hasNext(); ) {
			CartItem ct = list.next();
			price += (ct.getProduct().getNormalPrice() * ct.getCount());
		}
		return price;
	}
	
	/**
	 * 得到所有商品的 Member 价格
	 * @return
	 */
	public double getTotalMemberPrice() {
		
		double price = 0;
		
		for(Iterator<CartItem> list = items.iterator(); list.hasNext(); ) {
			CartItem ct = list.next();
			price += (ct.getProduct().getMemberPrice() * ct.getCount());
		}
		return price;
	}
}