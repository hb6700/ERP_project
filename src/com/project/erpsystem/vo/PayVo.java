package com.project.erpsystem.vo;

public class PayVo {
	//사번, 이름, 부서, 지급일, 기본급, 직책수당, 연장근로수당 휴일근로수당 국민연금 건강보험 고용보험 장기요양보험 소득세 지방소득세 지급총액 공제총액 실수령액
	private String id;
	private String name;
	private String department;
	private String paymentDate;
	private int basicPay;
	private int positionPay;
	private int overtimePay;
	private int holidayPay;
	private int nationalPension;
	private int healthlnsurance;
	private int employmentInsurance;
	private int longTermCareInsuracne;
	private int incomeTax;
	private int localIncomeTax;
	private int totalPay;
	private int totalDeducations;
	private int netPay;
	public PayVo(String id, String name, String department, String paymentDate, int basicPay, int positionPay,
			int overtimePay, int holidayPay, int nationalPension, int healthlnsurance, int employmentInsurance,
			int longTermCareInsuracne, int incomeTax, int localIncomeTax, int totalPay, int totalDeducations,
			int netPay) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.paymentDate = paymentDate;
		this.basicPay = basicPay;
		this.positionPay = positionPay;
		this.overtimePay = overtimePay;
		this.holidayPay = holidayPay;
		this.nationalPension = nationalPension;
		this.healthlnsurance = healthlnsurance;
		this.employmentInsurance = employmentInsurance;
		this.longTermCareInsuracne = longTermCareInsuracne;
		this.incomeTax = incomeTax;
		this.localIncomeTax = localIncomeTax;
		this.totalPay = totalPay;
		this.totalDeducations = totalDeducations;
		this.netPay = netPay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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
	public int getOvertimePay() {
		return overtimePay;
	}
	public void setOvertimePay(int overtimePay) {
		this.overtimePay = overtimePay;
	}
	public int getHolidayPay() {
		return holidayPay;
	}
	public void setHolidayPay(int holidayPay) {
		this.holidayPay = holidayPay;
	}
	public int getNationalPension() {
		return nationalPension;
	}
	public void setNationalPension(int nationalPension) {
		this.nationalPension = nationalPension;
	}
	public int getHealthlnsurance() {
		return healthlnsurance;
	}
	public void setHealthlnsurance(int healthlnsurance) {
		this.healthlnsurance = healthlnsurance;
	}
	public int getEmploymentInsurance() {
		return employmentInsurance;
	}
	public void setEmploymentInsurance(int employmentInsurance) {
		this.employmentInsurance = employmentInsurance;
	}
	public int getLongTermCareInsuracne() {
		return longTermCareInsuracne;
	}
	public void setLongTermCareInsuracne(int longTermCareInsuracne) {
		this.longTermCareInsuracne = longTermCareInsuracne;
	}
	public int getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	public int getLocalIncomeTax() {
		return localIncomeTax;
	}
	public void setLocalIncomeTax(int localIncomeTax) {
		this.localIncomeTax = localIncomeTax;
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	public int getTotalDeducations() {
		return totalDeducations;
	}
	public void setTotalDeducations(int totalDeducations) {
		this.totalDeducations = totalDeducations;
	}
	public int getNetPay() {
		return netPay;
	}
	public void setNetPay(int netPay) {
		this.netPay = netPay;
	}
	@Override
	public String toString() {
		return "PayVo [id=" + id + ", name=" + name + ", department=" + department + ", paymentDate=" + paymentDate
				+ ", basicPay=" + basicPay + ", positionPay=" + positionPay + ", overtimePay=" + overtimePay
				+ ", holidayPay=" + holidayPay + ", nationalPension=" + nationalPension + ", healthlnsurance="
				+ healthlnsurance + ", employmentInsurance=" + employmentInsurance + ", longTermCareInsuracne="
				+ longTermCareInsuracne + ", incomeTax=" + incomeTax + ", localIncomeTax=" + localIncomeTax
				+ ", totalPay=" + totalPay + ", totalDeducations=" + totalDeducations + ", netPay=" + netPay + "]";
	}
	
	
	
}
