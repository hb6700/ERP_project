package com.project.erpsystem.vo;

public class AnnualSubVo {

	String id;
	String startAnnual;
	String endAnnal;
	int dateAnnual;
	String reason;
	
	public AnnualSubVo() {
	}

	public AnnualSubVo(String id, String startAnnual, String endAnnal, int dateAnnual, String reason) {
		super();
		this.id = id;
		this.startAnnual = startAnnual;
		this.endAnnal = endAnnal;
		this.dateAnnual = dateAnnual;
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartAnnual() {
		return startAnnual;
	}

	public void setStartAnnual(String startAnnual) {
		this.startAnnual = startAnnual;
	}

	public String getEndAnnal() {
		return endAnnal;
	}

	public void setEndAnnal(String endAnnal) {
		this.endAnnal = endAnnal;
	}

	public int getDateAnnual() {
		return dateAnnual;
	}

	public void setDateAnnual(int dateAnnual) {
		this.dateAnnual = dateAnnual;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "AnnualSubVo [id=" + id + ", startAnnual=" + startAnnual + ", endAnnal=" + endAnnal + ", dateAnnual="
				+ dateAnnual + ", reason=" + reason + "]";
	}

	
	
	
	
	
}
