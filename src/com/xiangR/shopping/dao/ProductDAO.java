package com.xiangR.shopping.dao;

import java.util.Date;
import java.util.List;

import com.xiangR.shopping.product.Product;

public interface ProductDAO {

	/*
	 * DAO 是与数据库接触的层 是不和view层接触的
	 * 比如在这里服务于 ProductMgr
	 */
	public List<Product> getProducts();
	
	public List<Product> getProducts(int pageNo, int pageSize);
	
	/**
	 * 
	 * @param products
	 * @param pageNo
	 * @param pageSize
	 * @return 	page couts of the specified pageSize
	 */
	public int getProducts(List<Product> products, int pageNo, int pageSize);
	
	public int sizeProducts();
	
	public List<Product> findProducts(int[] categoryId,
									  String keyWord,
									  double lowNormalPrice,
									  double highNormalPrice,
									  double lowMemberPrice,
									  double highMemberPrice,
									  Date startDate,
									  Date endDate,
									  int pageNo,
									  int pageSize);
	
	public List<Product> findProducts(String name);
			
	public boolean deleteProductByCategoryById(int categoryId);
	
	public boolean deleteProductsById(int[] idArray);
	
	public boolean updateProduct(Product p);
	
	public boolean addProduct(Product p);
}
 