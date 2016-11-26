package com.xiangR.shopping.category;

public class Category {
	public static final int MAX_GRADE = 3;
	
	private int id;
	private String name;
	private String descr;
	private int pid;
	private boolean leaf;
	private int grade;
	
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public static void addChilde(Category c) {
		CategoryDAO.addChilde(c);
	}
	public void update() {
		CategoryDAO.updateCategory(this);
	/*
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			String sql = "update category set name = ?, descr = ? where id = " + this.id;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.getName());
			ps.setString(2, this.descr);
			ps.executeUpdate();
			System.out.println(this.name +" :¡¡"+ this.descr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
