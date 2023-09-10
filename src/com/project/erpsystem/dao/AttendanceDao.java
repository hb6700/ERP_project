package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.admin.view.AdminView;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;
import com.project.erpsystem.vo.AttendanceVo;

public class AttendanceDao {
	
	public static ArrayList<AttendanceVo> list;
	
	static {
		
		AttendanceDao.list = new ArrayList<AttendanceVo>();
		
	}
	
	public static boolean load(String year, String month) {
		
		try {
			
			AttendanceDao.list.clear();

			String path = String.format("data\\attendance%s.txt", month);
			
	        File file = new File(path);

	        if (file.exists()) {
	        	
				BufferedReader reader = new BufferedReader(new FileReader(path));
				
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					
					String[] temp = line.split(",");
					
					AttendanceVo a = new AttendanceVo(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10]);
					AttendanceDao.list.add(a);
					
				}
				
				reader.close();
				
				return true;
				
	        }
	        
	        return false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
		
	public static void save(String year, String month) {
		
		try {
					
			String path = String.format("data\\attendance%s.txt", month);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (AttendanceVo a : AttendanceDao.list) {
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
										   a.getId(), a.getDate(), a.getMorningRushHours(), a.getEveningRushHours(),
										   a.getBusinessHours(), a.getLateness(), a.getLeavingEarly(), a.getDayOff(),
										   a.getOvertime(), a.getOvertimeAtHoliday(), a.getOvertimeAtNight()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}
	
	public static void checkAttendanceMember(String year, String month, String id, String name) {
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%s님의 %s년 %s월 근태 조회 결과\n\n", name, year, month);
		System.out.printf("%s%s%s%s%s%s%s%s%s%s\n", "[일자]", "[출근시간]", "[퇴근시간]", "[근무시간]", "[지각]", "[조퇴]", "[휴가]", "[OT시간(일반)]", "[OT시간(휴일)]", "[OT시간(심야)]");
		
		int countDate = 0, countHoliday = 0, countBusinessHour = 0, countBusinessMinute = 0, countOvertimeHour = 0,
			countOvertimeMinute = 0, countLateness = 0, countLeavingEarly = 0, countDayOff = 0;
		
		for (AttendanceVo  a : AttendanceDao.list) {
			
			if (a.getId().equals(id)) {
				
				String lateness = a.getLateness().equals("0") ? "X" : "O";
				String leavingEarly = a.getLeavingEarly().equals("0") ?  "X" : "O";
				String dayOff = a.getDayOff().equals("0") ?  "X" : "O";
				
				System.out.printf("%3s일%8s%10s%10s%7s%6s%6s%10s%14s%14s\n",
								  a.getDate(), a.getMorningRushHours(), a.getEveningRushHours(), a.getBusinessHours(),
								  lateness, leavingEarly, dayOff, a.getOvertime(),  a.getOvertimeAtHoliday(), a.getOvertimeAtNight());
			    
			    countDate += !a.getBusinessHours().equals("0:00") ? 1 : 0;
			    countHoliday += a.getBusinessHours().equals("0:00") ? 1 : 0;
				
				countBusinessHour += Integer.parseInt(a.getBusinessHours().split(":")[0]);
				countBusinessMinute += Integer.parseInt(a.getBusinessHours().split(":")[1]);
				
				while (countBusinessMinute >= 60) {
					
					countBusinessHour++;
				    countBusinessMinute -= 60;
				    
				}

				if (!a.getOvertime().equals("0")) {
					
					countOvertimeHour += Integer.parseInt(a.getOvertime().split(":")[0]);
					countOvertimeMinute += Integer.parseInt(a.getOvertime().split(":")[1]);
					
				}
				
				if (!a.getOvertimeAtNight().equals("0")) {
					
					countOvertimeHour += Integer.parseInt(a.getOvertimeAtNight().split(":")[0]);
					countOvertimeMinute += Integer.parseInt(a.getOvertimeAtNight().split(":")[1]);
					
				}
				
				if (!a.getOvertimeAtHoliday().equals("0")) {
					
					countOvertimeHour += Integer.parseInt(a.getOvertimeAtHoliday().split(":")[0]);
					countOvertimeMinute += Integer.parseInt(a.getOvertimeAtHoliday().split(":")[1]);
					
				}
					
				while (countOvertimeMinute >= 60) {
						
					countOvertimeHour++;
					countOvertimeMinute -= 60;
					    
				}
				
				countLateness += lateness.equals("O") ? 1 : 0;
				countLeavingEarly += leavingEarly.equals("O") ? 1 : 0;
				countDayOff += dayOff.equals("O") ? 1 : 0;

			}
			
		}
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("월 소계\n");
		System.out.println("[근무일수]    [휴무일수]    [근무시간]     [OT시간]     [지각건수]     [조퇴건수]     [휴가건수]");
		System.out.printf("%5s일%12s일%9s시간%3s분%5s시간%3s분%8s건%13s건%13s건\n",
						   countDate, countHoliday, countBusinessHour, countBusinessMinute, countOvertimeHour,
						   countOvertimeMinute, countLateness, countLeavingEarly, countDayOff);
		
	}
	
	public static boolean checkAttendanceAdmin(String year, String month, String id) {
		
		boolean exist = false;
		
		for (AttendanceVo  a : AttendanceDao.list) {
			
			if (a.getId().equals(id)) {
				
				exist = true;
				
	            break;
				
			}
			
		}
		
		if (!exist) {
			
			return false;
			
		}
			
		String name = "";

		for (PersonVo p : PersonDao.list) {
				
			if (id.equals(p.getId())) {
					
				name = p.getName();
					
				break;
					
			}
				
		}
			
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%s님의 %s년 %s월 근태 조회 결과\n\n", name, year, month);
		System.out.println("[일자][출근시간][퇴근시간][근무시간][지각][조퇴][휴가][OT시간(일반)][OT시간(휴일)][OT시간(심야)]");
			
		for (AttendanceVo  a : AttendanceDao.list) {
				
			if (a.getId().equals(id)) {
					
				String lateness = a.getLateness().equals("0") ? "X" : "O";
				String leavingEarly = a.getLeavingEarly().equals("0") ?  "X" : "O";
				String dayOff = a.getDayOff().equals("0") ?  "X" : "O";
					
				System.out.printf("%3s일%8s%10s%10s%7s%6s%6s%10s%14s%14s\n",
								   a.getDate(), a.getMorningRushHours(), a.getEveningRushHours(), a.getBusinessHours(),
								   lateness, leavingEarly, dayOff, a.getOvertime(),  a.getOvertimeAtHoliday(), a.getOvertimeAtNight());
					
			}
				
		}
			
		System.out.println("================================================================================================");
			
		return true;

	}
	
	public static boolean attendanceModify(String year, String month, String date, String id, int input, String time) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean success = false;
		
		int modifiedHour = Integer.parseInt(time.split(":")[0]);
		int modifiedMinute = Integer.parseInt(time.split(":")[1]);
		
        Calendar modifiedTime = Calendar.getInstance();
        modifiedTime.set(Calendar.getInstance().get(Calendar.YEAR), Integer.parseInt(month) - 1, Integer.parseInt(date), modifiedHour, modifiedMinute);
        
        long modifiedTick = modifiedTime.getTimeInMillis();
			
		switch (input) {
			
			case 1:
					
				for (AttendanceVo  a : AttendanceDao.list) {
				
					if (id.equals(a.getId()) && date.equals(a.getDate())) {
						
						int eveningHour = Integer.parseInt(a.getEveningRushHours().split(":")[0]);
						int eveningMinute = Integer.parseInt(a.getEveningRushHours().split(":")[1]);
						
						Calendar eveningRushHours = Calendar.getInstance();
						eveningRushHours.set(Calendar.getInstance().get(Calendar.YEAR), Integer.parseInt(month) - 1, Integer.parseInt(date), eveningHour, eveningMinute);

						long eveningTick = eveningRushHours.getTimeInMillis();
				        
				        if (modifiedTick < eveningTick) {
				        	
				        	a.setMorningRushHours(String.format("%d:%02d", modifiedHour, modifiedMinute));
				        	
				        	long calculatedMinute = (eveningTick - modifiedTick) / 1000 / 60;
				        	long calculatedHour = calculatedMinute / 60;
				        	calculatedMinute %= 60;
				        	
				        	a.setBusinessHours(String.format("%d:%02d", calculatedHour, calculatedMinute));

				        	if ((modifiedHour < 9) || (modifiedHour == 9 && modifiedMinute == 0)) {
				        		
				        		a.setLateness("0");
				        		
				        	} else if ((modifiedHour == 9 && modifiedMinute > 0) || (modifiedHour > 9)) {
				        		
				        		a.setLateness("1");
				        		
				        	}
				        	
				        	while (true) {
				        		
				        		System.out.print("출근 시간을 조정하시겠습니까?(Y/N): ");
					        	String answer = scan.nextLine();
					        	
					        	if (answer.equals("Y") || answer.equals("y")) {
					        		
					        		System.out.println("출근 시간 조정이 완료되었습니다.");
					        		//System.out.println("==============================================");
					        		AttendanceDao.save(year, month);
					        		
					        		break;
					        		
					        	} else if (answer.equals("N") || answer.equals("n")) {
					        		
					        		System.out.println("출근 시간 조정이 취소되었습니다.");
					        		//System.out.println("==============================================");
					        		AdminView.adminMain();

					        		break;
					        		
					        	} else {
					        		
					        		System.out.println("Y 또는 N을 입력하세요.");
					        		//System.out.println("----------------------------------------------");
					        		
					        	}
				        		
				        	}
				        	
				        	success =  true;
				        	
				        }
						
					}
					
				}
				
				break;
				
			case 2:
					
				for (AttendanceVo  a : AttendanceDao.list) {
				
					if (id.equals(a.getId()) && date.equals(a.getDate())) {

						int morningHour = Integer.parseInt(a.getMorningRushHours().split(":")[0]);
						int morningMinute = Integer.parseInt(a.getMorningRushHours().split(":")[1]);

						Calendar morningRushHours = Calendar.getInstance();
						morningRushHours.set(Calendar.getInstance().get(Calendar.YEAR), Integer.parseInt(month) - 1, Integer.parseInt(date), morningHour, morningMinute);

				        long morningTick = morningRushHours.getTimeInMillis();
				        
				        if (modifiedTick > morningTick) {

				        	a.setEveningRushHours(String.format("%d:%02d", modifiedHour, modifiedMinute));

				        	long calculatedMinute = (modifiedTick - morningTick) / 1000 / 60;
				        	long calculatedHour = calculatedMinute / 60;
				        	calculatedMinute %= 60;

				        	a.setBusinessHours(String.format("%d:%02d", calculatedHour, calculatedMinute));
				        	
					        if (modifiedHour < 18) {
					        		
					        	a.setLeavingEarly("1");
					        	a.setOvertime("0");
					        	a.setOvertimeAtNight("0");
					        		
					        } else if (modifiedHour < 22) {
					        		
					        	a.setOvertime(String.format("%d:%02d", modifiedHour - 18, modifiedMinute));
					        	a.setOvertimeAtNight("0");
					        		
					        } else if (modifiedHour < 24) {
					        		
					        	a.setOvertime(String.format("%d:%02d", modifiedHour - 18, 0));
					        	a.setOvertimeAtNight(String.format("%d:%02d", modifiedHour - 22, modifiedMinute));
					        		
					        }
				        	
				        	if (modifiedTime.get(Calendar.DAY_OF_WEEK) == 1 || modifiedTime.get(Calendar.DAY_OF_WEEK) == 7) {
				        		
				        		a.setOvertime("0");
				        		a.setOvertimeAtHoliday(String.format("%d:%02d", modifiedHour - 18, modifiedMinute));
				        		a.setOvertimeAtNight("0");
				        		
				        	} else {
				        		
				        		a.setOvertimeAtHoliday("0");
				        		
				        	}
				        	
				        	while (true) {
				        		
				        		System.out.print("퇴근 시간을 조정하시겠습니까?(Y/N): ");
					        	String answer = scan.nextLine();
					        	
					        	if (answer.equals("Y") || answer.equals("y")) {
					        		
					        		System.out.println("퇴근 시간 조정이 완료되었습니다.");
					        		System.out.println("================================================================================");
					        		AttendanceDao.save(year, month);
					        		
					        		break;
					        		
					        	} else if (answer.equals("N") || answer.equals("n")) {
					        		
					        		System.out.println("퇴근 시간 조정이 취소되었습니다.");
					        		System.out.println("================================================================================");
					        		AdminView.adminMain();

					        		break;
					        		
					        	} else {
					        		
					        		System.out.println("Y 또는 N을 입력하세요.");
					        		System.out.println("================================================================================");
					        		
					        	}
				        		
				        	}
				        	
				        	success =  true;
				        	
				        }
			
					}

				}
				
				break;
		
		}
		
		return success;
		
	}

}
