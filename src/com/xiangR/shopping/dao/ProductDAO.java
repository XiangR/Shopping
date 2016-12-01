package com.xiangR.shopping.dao;

import java.util.Date;
import java.util.List;

import com.xiangR.shopping.product.Product;

public interface ProductDAO {

	/*
	 * DAO �������ݿ�Ӵ��Ĳ� �ǲ���view��Ӵ���
	 * ��������������� ProductMgr
	 */
	
	/**
	 * �õ����е�product
	 * @return
	 */
	public List<Product> getProducts();
	
	/**
	 * �� id �õ�ָ���� product
	 * @param id
	 * @return
	 */
	public Product loadById(int id); 
	
	/**
	 * �õ���join ��� list
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Product> getProducts(int pageNo, int pageSize);
	
	/**
	 * ��list ��Ϊ������ȥ�ڷ������ʼ�� ͬʱҲʹ�÷�ҳ
	 * @param products
	 * @param pageNo
	 * @param pageSize
	 * @return 	page couts of the specified pageSize
	 */
	public int getProducts(List<Product> products, int pageNo, int pageSize);
	
	/**
	 * �ڵ�������Ķ�����ķ�������ʱ �õ�δ��ҳʱ������ ��ҳ���ҳʹ��
	 * @return
	 */
	public int sizeProducts();
	
	/**
	 * �ɲ����õ��� ָ�������µ� product �����������������ҳ
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
	 * ��һ��productΪ���� �������ݿ��е���� product 
	 * @param p
	 * @return
	 */
	public boolean updateProduct(Product p);
	
	/**
	 * �����product���뵽���ݿ���
	 * @param p
	 * @return
	 */
	public boolean addProduct(Product p);
	
	/**
	 * ɾ��idָ����product
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id);
	
	/**
	 * �õ�ָ���������簴ʱ�������µ�ָ�� count ��product
	 * @param count
	 * @return
	 */
	public List<Product> getLatestProducts(int count);
	
	/**
	 * ʹ�� CategoryId �õ�ָ�������µ�product ����ʹ��������� join ���ʹ������sql ���һ������limit һ���õ�δ��ҳʱɸѡ��������
	 * @param products 
	 * @param pageNo 
	 * @param pageSize 
	 * @param categoryId
	 * @return
	 */
	public int find(List<Product> products, int pageNo, int pageSize, int categoryId);
}
 