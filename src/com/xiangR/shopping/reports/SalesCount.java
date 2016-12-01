package com.xiangR.shopping.reports;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.xiangR.shopping.util.DB;

/**
 * 封装的一个后台查看前台的购物情况的图标 
 * 有饼图和柱状图
 * @author xiangrui
 */
public class SalesCount {

	static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	static DefaultPieDataset pieDataset = new DefaultPieDataset();
	
	public static void main(String[] args) {
		
		getDataSet();
		JFreeChart chartCategory = ChartFactory.createBarChart3D(
							"产品销量图", // 图表标题
							"产品", // 目录轴的显示标签
							"销量", // 数值轴的显示标签
							dataset, // 数据集
							PlotOrientation.VERTICAL, // 图表方向：水平、垂直
							true, 	// 是否显示图例(对于简单的柱状图必须是false)
							false, 	// 是否生成工具
							false 	// 是否生成URL链接
							);
							
		JFreeChart pieChart = ChartFactory.createPieChart(
							"产品销量图",
							pieDataset, 
							true, 
							false, 
							false);
		
		FileOutputStream fos_jpg = null;
		FileOutputStream pie_jpg = null;
		try {
			fos_jpg = new FileOutputStream("D:\\reports.jpg");
				ChartUtilities.writeChartAsJPEG(fos_jpg,1,chartCategory,400,300,null);
			pie_jpg = new FileOutputStream("D:\\reports2.jpg");
				ChartUtilities.writeChartAsJPEG(pie_jpg,1,pieChart,400,300,null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos_jpg.close();
				pie_jpg.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static void getDataSet() {
		
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
				pieDataset.setValue(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
