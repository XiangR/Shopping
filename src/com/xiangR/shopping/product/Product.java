package com.xiangR.shopping.product;

import java.sql.Timestamp;

import com.xiangR.shopping.category.Category;


public class Product {
	
	private int id;
	private String name;
	private String descr;
	private double normalPrice;
	private double memberPrice;
	private Timestamp pdate;
	private int categoryId;
	
	// 给每一个的product 加上一个其所属的 Category 对象
	private Category c;
	
	public Category getC() {
		return c;
	}
	public void setC(Category c) {
		this.c = c;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public double getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(double normalPrice) {
		this.normalPrice = normalPrice;
	}
	public double getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return this.getId() + "--" +  this.getName() + "--" + this.getPdate() + "--" + this.getDescr() + "--" + this.getMemberPrice();
	}
	
	
	
}
