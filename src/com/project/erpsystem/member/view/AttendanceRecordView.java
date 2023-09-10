package com.project.erpsystem.member.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.erpsystem.dao.AttendanceDao;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.vo.AttendanceVo;

public class AttendanceRecordView {
	
	public static boolean checkExistence(String year, String month) {
		
		Scanner scan = new Scanner(System.in);
		
		Matcher yearMatcher = Pattern.compile("^(20\\d\\d)$").matcher(year);
		Matcher monthMatcher = Pattern.compile("^(1[0-2]|[1-9])$").matcher(month);
		
		if (!(yearMatcher.matches() && monthMatcher.matches())) {
			
			System.out.println("올바른 형식의 연도/월을 입력하세요.");
			scan.nextLine();
			return false;
			
		}
			
		boolean result = AttendanceDao.load(year, month);
			
	    if (!result) {
	    	
	    	System.out.println("근태 이력이 존재하지 않습니다.");
	    	System.out.println("계속 진행하시려면 Enter를 입력해주세요.");  
	    	scan.nextLine();
	    	//System.out.println("================================================================================================");
	    	return false;
	       
	    }
	    if(checkIdExistence(year, month)) {
	    	
	    	return true;
	    } else {
	    	return false;
	    }
		
	}

	public static boolean checkIdExistence(String year, String month) {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			boolean exist = false;
			
			for (AttendanceVo  a : AttendanceDao.list) {
				
				if (a.getId().equals(UserDao.auth.getId())) {
					
					exist = true;
					
					break;
					
				}
				
			}
			
			if (exist) {
				
				AttendanceDao.checkAttendanceMember(year, month, UserDao.auth.getId(), UserDao.auth.getName());
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");  
				scan.nextLine();
				//System.out.println("================================================================================================");
				return true;
			} else {
				
				System.out.println("근태 이력이 존재하지 않습니다.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");  
				scan.nextLine();
				return false;
			}
		}
		


	}

}
