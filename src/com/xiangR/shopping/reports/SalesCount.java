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
 * ��װ��һ����̨�鿴ǰ̨�Ĺ��������ͼ�� 
 * �б�ͼ����״ͼ
 * @author xiangrui
 */
public class SalesCount {

	static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	static DefaultPieDataset pieDataset = new DefaultPieDataset();
	
	public static void main(String[] args) {
		
		getDataSet();
		JFreeChart chartCategory = ChartFactory.createBarChart3D(
							"��Ʒ����ͼ", // ͼ�����
							"��Ʒ", // Ŀ¼�����ʾ��ǩ
							"����", // ��ֵ�����ʾ��ǩ
							dataset, // ���ݼ�
							PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
							true, 	// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
							false, 	// �Ƿ����ɹ���
							false 	// �Ƿ�����URL����
							);
							
		JFreeChart pieChart = ChartFactory.createPieChart(
							"��Ʒ����ͼ",
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
			// ���ű������
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
