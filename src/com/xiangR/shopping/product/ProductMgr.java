package com.xiangR.shopping.product;

import java.util.*;

import com.xiangR.shopping.dao.ProductDAO;
import com.xiangR.shopping.dao.ProductMysqlDAO;

public class ProductMgr {
	
	ProductDAO dao = null;
	// singlton
	private static ProductMgr pm = null;
	
	static {
		if(pm == null) {
			pm = new ProductMgr();
			pm.setDao(new ProductMysqlDAO());
		}
	}
	
	private ProductMgr() {
	}
	public static ProductMgr getInstance() {
		return pm;
	}
	
	
	public ProductDAO getDao() {
		return new ProductMysqlDAO();
	}

	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}

	public List<Product> getProducts() { 
		
		return null;
	}
	
	public List<Product> getProducts(int pageNo, int pageSize) {
		return dao.getProducts(pageNo, pageSize);
	}
	
	public int getProducts(List<Product> products, int pageNo, int pageSize) {
		return dao.getProducts(products, pageNo, pageSize);
	}
	public int sizeProducts() {
		return dao.sizeProducts();
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
		return dao.findProducts(categoryId, keyWord, lowNormalPrice, highNormalPrice, lowMemberPrice, highMemberPrice, startDate, endDate, pageNo, pageSize);
	}
	
	public List<Product> findProducts(String name) {
		return null;
	}
			
	public boolean addProduct(Product p) {
		return dao.addProduct(p);
	}
	public boolean deleteProductByCategoryById(int categoryId) {
		return false;
	}
	
	public boolean deleteProductsById(int[] idArray) {
		// 支持选中删除
		return false;
	}
	
	public boolean updateProduct(Product p) {
		return false;
	}

}
