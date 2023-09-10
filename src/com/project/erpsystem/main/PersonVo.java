package com.project.erpsystem.main;

public class PersonVo {

	String id;
	String name;
	String birthDay;
	String addr;
	String tel;
	String department;
	String position;
	String hobong;
	String beginDate;
	String licence;
	String school;
	String rating;
	String workStatus;
	
	public PersonVo() {
		// TODO Auto-generated constructor stub
	}

	public PersonVo(String id, String name, String birthDay, String addr, String tel, String department,
			String position, String hobong, String beginDate, String licence, String school, String rating,
			String workStatus) {
		super();
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
		this.addr = addr;
		this.tel = tel;
		this.department = department;
		this.position = position;
		this.hobong = hobong;
		this.beginDate = beginDate;
		this.licence = licence;
		this.school = school;
		this.rating = rating;
		this.workStatus = workStatus;
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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	@Override
	public String toString() {
		return "PersonVo [id=" + id + ", name=" + name + ", birthDay=" + birthDay + ", addr=" + addr + ", tel=" + tel
				+ ", department=" + department + ", position=" + position + ", hobong=" + hobong + ", beginDate="
				+ beginDate + ", licence=" + licence + ", school=" + school + ", rating=" + rating + ", workStatus="
				+ workStatus + "]";
	}
	
	
	
}
