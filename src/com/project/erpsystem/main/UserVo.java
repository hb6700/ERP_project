package com.project.erpsystem.main;

public class UserVo {

	String id;//사번
	String pw;
	String level;// 일반, 관리자;
	
	public UserVo() {
		// TODO Auto-generated constructor stub
	}

	public UserVo(String id, String pw, String level) {
		super();
		this.id = id;
		this.pw = pw;
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + ", level=" + level + "]";
	}
	
	
	
}
