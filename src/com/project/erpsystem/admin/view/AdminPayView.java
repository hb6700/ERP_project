package com.project.erpsystem.admin.view;

import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.dao.PayDao;

public class AdminPayView {

	public static void subTitle() {
		System.out.println();
		System.out.println("[급여관리정보]");
	}

	public static void listAdd() {
		
		while(true) {
			
			Calendar now = Calendar.getInstance();
			String month = now.get(Calendar.MONTH)+1 + "";
			
			System.out.printf("%s월 근로소득 일괄 등록하시겠습니까?(Y/N): ", month);
			
			Scanner scan = new Scanner(System.in);
			
			String input = scan.nextLine().toLowerCase();
			System.out.println("--------------------------------------------------------------------------------");
			if (input.equals("y")) {
				PayDao.listAdd(month);
				return;
			} else if (input.equals("n")) {
				AdminView.payMenu();
				return;
			} else {
				System.out.println("Y/N 으로 입력해주세요.");
			}
		}
		
		
	}

	public static void listCheck() {
		
//		while(true) {
			
			System.out.println();
			System.out.println("[직원별 급여명세서 조회]");
			Scanner scan = new Scanner(System.in);
			
			System.out.print("조회할 사번을 입력해주세요.: ");
			String id = scan.nextLine();
			System.out.print("조회할 날짜(월)을 입력해주세요.: ");
			String month = scan.nextLine();
			
			if (PayDao.isExistence(id, month)) {
				//System.out.println("================================================================================");
				PayDao.listShow(month,id);
				//System.out.println("================================================================================");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
				return;
			} else {
				System.out.println("유효하지 않는 사번 또는 날짜입니다.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
//		}
		
	}
	
}
