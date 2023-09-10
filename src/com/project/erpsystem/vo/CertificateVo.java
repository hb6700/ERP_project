package com.project.erpsystem.vo;

public class CertificateVo {

	//일련번호, 신청일자, 사번, 증명서 종류, 승인상태
	private int no;
	private String rqDate;
	private String id;
//	private String name;
//	private String department;
	private int type; //1 > 재직, 2 > 경력
	private int certStatus; //0 > 처리 전, 1 > 승인
	
	public CertificateVo(int no
							, String rqDate
							, String id
//							, String name
//							, String department
							, int type
							, int certStatus) {
		this.no = no;
		this.rqDate = rqDate;
		this.id = id;
//		this.name = name;
//		this.department = department;
		this.type = type;
		this.certStatus = certStatus;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRqDate() {
		return rqDate;
	}

	public void setRqDate(String rqDate) {
		this.rqDate = rqDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(int certStatus) {
		this.certStatus = certStatus;
	}

	@Override
	public String toString() {
		return "CertificateVo [no=" + no 
				+ ", rqDate=" + rqDate 
				+ ", id=" + id 
//				+ ", name=" + name 
//				+ ", department="+ department 
				+ ", type=" + type 
				+ ", certStatus=" + certStatus + "]";
	}
	
	
}
