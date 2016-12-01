package com.xiangR.shopping.dao;

import java.util.Date;
import java.util.List;

import com.xiangR.shopping.product.Product;

public interface ProductDAO {

	/*
	 * DAO 是与数据库接触的层 是不和view层接触的
	 * 比如在这里服务于 ProductMgr
	 */
	
	/**
	 * 得到所有的product
	 * @return
	 */
	public List<Product> getProducts();
	
	/**
	 * 由 id 得到指定的 product
	 * @param id
	 * @return
	 */
	public Product loadById(int id); 
	
	/**
	 * 得到表join 后的 list
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> getProducts(int pageNo, int pageSize);
	
	/**
	 * 以list 作为参数进去在方法里初始化 同时也使用分页
	 * @param products
	 * @param pageNo
	 * @param pageSize
	 * @return 	page couts of the specified pageSize
	 */
	public int getProducts(List<Product> products, int pageNo, int pageSize);
	
	/**
	 * 在调用下面的多参数的方法运行时 得到未分页时的数据 由页面分页使用
	 * @return
	 */
	public int sizeProducts();
	
	/**
	 * 由参数得到的 指定条件下的 product 最后两个参数用作分页
	 * @param categoryId
	 * @param keyWord
	 * @param lowNormalPrice
	 * @param highNormalPrice
	 * @param lowMemberPrice
	 * @param highMemberPrice
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
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
	/**
	 * 以一个product为参数 更新数据库中的这个 product 
	 * @param p
	 * @return
	 */
	public boolean updateProduct(Product p);
	
	/**
	 * 将这个product加入到数据库中
	 * @param p
	 * @return
	 */
	public boolean addProduct(Product p);
	
	/**
	 * 删除id指定的product
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id);
	
	/**
	 * 得到指定条件（如按时间排序）下的指定 count 个product
	 * @param count
	 * @return
	 */
	public List<Product> getLatestProducts(int count);
	
	/**
	 * 使用 CategoryId 得到指定条件下的product 这里使用两条语句 join 随后使用两个sql 语句一个处理limit 一个得到未分页时筛选出的数量
	 * @param products 
	 * @param pageNo 
	 * @param pageSize 
	 * @param categoryId
	 * @return
	 */
	public int find(List<Product> products, int pageNo, int pageSize, int categoryId);
}
 