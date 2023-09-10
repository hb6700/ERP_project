package com.project.erpsystem.vo;

public class HobongVo {
	private String position;
	private String hobong;
	private int basicPay;
	private int positionPay;
	
	public HobongVo(String position, String hobong, int basicPay, int positionPay) {
		super();
		this.position = position;
		this.hobong = hobong;
		this.basicPay = basicPay;
		this.positionPay = positionPay;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHobong() {
		return hobong;
	}
	public void setHobong(String hobong) {
		this.hobong = hobong;
	}
	public int getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(int basicPay) {
		this.basicPay = basicPay;
	}
	public int getPositionPay() {
		return positionPay;
	}
	public void setPositionPay(int positionPay) {
		this.positionPay = positionPay;
	}
	
	
}
