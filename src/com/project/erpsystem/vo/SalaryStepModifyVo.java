package com.project.erpsystem.vo;

public class SalaryStepModifyVo {

	private String position;
	private String hobong;
	private String basicPay;
	private String positionPay;
	
	public SalaryStepModifyVo(String position, String hobong, String basicPay, String positionPay) {
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

	public String getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(String basicPay) {
		this.basicPay = basicPay;
	}

	public String getPositionPay() {
		return positionPay;
	}

	public void setPositionPay(String positionPay) {
		this.positionPay = positionPay;
	}

	@Override
	public String toString() {
		return "SalaryStepModifyVo [position=" + position + ", hobong=" + hobong + ", basicPay=" + basicPay
				+ ", positionPay=" + positionPay + "]";
	}
	
	
}
