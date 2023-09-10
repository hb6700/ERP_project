package com.project.erpsystem.admin.view;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.erpsystem.dao.AttendanceDao;

public class AttendanceSettingView {
	
	public static void checkMonthExistence() {
		
		while(true) {
			
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("월을 입력하세요: ");
			String month = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			Matcher matcher = Pattern.compile("^(1[0-2]|[1-9])$").matcher(month);
			
			if (matcher.matches()) {
				
				boolean result = AttendanceDao.load(year, month);
				
				if (result) {
					
					checkIdExistence(year, month);
					return;
					
				} else {
					
					System.out.println("근태 이력이 존재하지 않습니다.");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
					scan.nextLine();
				}
				
			} else {
				
				System.out.println("올바른 형식의 월을 입력하세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
				
			}
		}
		

	}
	
	public static void checkIdExistence(String year, String month) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("사번을 입력하세요: ");
			String id = scan.nextLine();
			
			boolean result = AttendanceDao.checkAttendanceAdmin(year, month, id);
			
			if (result) {
				
				chooseMenu(year, month, id);
				return;
			} else {
				
				System.out.println("근태 이력이 존재하지 않습니다.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
				
			}
			
		}
		
	}

	public static void chooseMenu(String year, String month, String id) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("1. 출/퇴근 시간 조정");
			System.out.println("2. 이전화면");
			System.out.print("번호를 입력하세요: ");
			int input = scan.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch (input) {
			
			case 1: 
				
				//System.out.println("----------------------------------------------");
				checkDateExistence(year, month, id);
				
				break;
				
			case 2: 
				return;
				
			default:
				System.out.println("올바른 번호를 입력하세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
		}
		
		
	}
	
	public static void checkDateExistence(String year, String month, String id) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("날짜(일)를 입력하세요: ");
			int date = scan.nextInt();
			
			System.out.println("--------------------------------------------------------------------------------");
			
			int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int today = Calendar.getInstance().get(Calendar.DATE);
			
			int modifiedMonth = Integer.parseInt(month);
			
			Calendar modifiedcCalendar = Calendar.getInstance();
			modifiedcCalendar.set(Calendar.YEAR, Integer.parseInt(year));
			modifiedcCalendar.set(Calendar.MONTH, modifiedMonth - 1);
			
			int lastDate = modifiedcCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			if ((modifiedMonth != thisMonth && (date > 0 && date <= lastDate)) ||
					(modifiedMonth == thisMonth && (date > 0 && date < today))) {
				
				chooseTime(year, month, String.valueOf(date), id);
				return;
			} else {
				
				System.out.println("올바른 형식의 날짜(일)를 입력하세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
				
			}
			
		}
		
		
	}
	
	public static void chooseTime(String year, String month, String date, String id) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			//System.out.println("================================================================================");

			System.out.println("1. 출근 시간");
			System.out.println("2. 퇴근 시간");
			System.out.print("번호를 입력하세요: ");
			int input = scan.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			switch (input) {
			
			case 1: 	
			case 2: 
				
				//System.out.println("----------------------------------------------");
				modifyTime(year, month, date, id, input);
				
				return;
				
			default:
				
				//System.out.println("----------------------------------------------");
				System.out.println("올바른 번호를 입력하세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
		}
		
		
	}
	
	public static void modifyTime(String year, String month, String date, String id, int input) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("시간을 입력하세요(X:XX): ");
			String time = scan.nextLine();
			
			System.out.println("--------------------------------------------------------------------------------");
			
			Matcher matcher = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$").matcher(time);
			
			if (matcher.matches()) {
				
				boolean result = AttendanceDao.attendanceModify(year, month, date, id, input, time);
				
				if (result) {
					
					//AdminView.adminMain();
					return;
				} else {
					
					if (input == 1) {
						
						System.out.println("퇴근 시간보다 이른 시간을 입력하세요.");
						
					} else {
						
						System.out.println("출근 시간보다 늦은 시간을 입력하세요.");
						
					}
					
					//System.out.println("----------------------------------------------");
					//modifyTime(year, month, date, id, input);
				}
				
			} else {
				
				System.out.println("올바른 형식의 시간을 입력하세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
		}
		
		
	}

}
