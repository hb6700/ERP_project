package com.project.erpsystem.vo;

public class PayItemVo {
	private int num;
	private String payItem;
	private boolean toUse;
	public PayItemVo(int num, String payItem, boolean toUse) {
		super();
		this.num = num;
		this.payItem = payItem;
		this.toUse = toUse;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPayItem() {
		return payItem;
	}
	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}
	public boolean isToUse() {
		return toUse;
	}
	public void setToUse(boolean toUse) {
		this.toUse = toUse;
	}
	
	
}
