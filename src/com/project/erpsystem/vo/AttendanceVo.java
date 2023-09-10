package com.project.erpsystem.vo;

public class AttendanceVo {
	
	private String id, date, morningRushHours, eveningRushHours, businessHours, lateness, leavingEarly, dayOff, overtime, overtimeAtHoliday, overtimeAtNight;

	public AttendanceVo(String id, String date, String morningRushHours, String eveningRushHours, String businessHours, String lateness, String leavingEarly, String dayOff, String overtime, String overtimeAtHoliday, String overtimeAtNight) {
		
		this.id = id;
		this.date = date;
		this.morningRushHours = morningRushHours;
		this.eveningRushHours = eveningRushHours;
		this.businessHours = businessHours;
		this.lateness = lateness;
		this.leavingEarly = leavingEarly;
		this.dayOff = dayOff;
		this.overtime = overtime;
		this.overtimeAtHoliday = overtimeAtHoliday;
		this.overtimeAtNight = overtimeAtNight;
		
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getMorningRushHours() {
		return morningRushHours;
	}

	public void setMorningRushHours(String morningRushHours) {
		this.morningRushHours = morningRushHours;
	}

	public String getEveningRushHours() {
		return eveningRushHours;
	}

	public void setEveningRushHours(String eveningRushHours) {
		this.eveningRushHours = eveningRushHours;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getLateness() {
		return lateness;
	}

	public void setLateness(String lateness) {
		this.lateness = lateness;
	}

	public String getLeavingEarly() {
		return leavingEarly;
	}

	public void setLeavingEarly(String leavingEarly) {
		this.leavingEarly = leavingEarly;
	}

	public String getDayOff() {
		return dayOff;
	}

	public void setDayOff(String dayOff) {
		this.dayOff = dayOff;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	
	public String getOvertimeAtHoliday() {
		return overtimeAtHoliday;
	}

	public void setOvertimeAtHoliday(String overtimeAtHoliday) {
		this.overtimeAtHoliday = overtimeAtHoliday;
	}

	public String getOvertimeAtNight() {
		return overtimeAtNight;
	}

	public void setOvertimeAtNight(String overtimeAtNight) {
		this.overtimeAtNight = overtimeAtNight;
	}

	@Override
	public String toString() {
		
		return "AttendanceVo [id=" + id + ", date=" + date + ", morningRushHours=" + morningRushHours
				+ ", eveningRushHours=" + eveningRushHours + ", businessHours=" + businessHours + ", lateness="
				+ lateness + ", leavingEarly=" + leavingEarly + ", dayOff=" + dayOff + ", overtime=" + overtime
				+ ", overtimeAtHoliday=" + overtimeAtHoliday + ", overtimeAtNight=" + overtimeAtNight + "]";
		
	}

}
