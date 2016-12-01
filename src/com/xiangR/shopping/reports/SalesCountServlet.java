package com.xiangR.shopping.reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.xiangR.shopping.sale.SalesOrder;
import com.xiangR.shopping.user.User;
import com.xiangR.shopping.util.DB;

public class SalesCountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalesCountServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// dataset.addValue(100, "beijing", "apple");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select p.name, sum(pcount) from product p join salesitem si on (p.id = si.productid) group by p.id";
System.out.println(sql);
			
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql);
			// 两张表的连接
			rs = ps.executeQuery();
			while(rs.next()) {
				dataset.addValue(rs.getInt(2), "", rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
							"产品销量图", // 图表标题
							"产品", // 目录轴的显示标签
							"销量", // 数值轴的显示标签
							dataset, // 数据集
							PlotOrientation.VERTICAL, // 图表方向：水平、垂直
							true, 	// 是否显示图例(对于简单的柱状图必须是false)
							false, 	// 是否生成工具
							false 	// 是否生成URL链接
							);
							
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("C:\\Users\\xiangrui\\Workspaces\\MyEclipse 10\\Shopping\\WebRoot\\images\\reports\\productsales.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,400,300,null);
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
