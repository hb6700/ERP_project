package com.project.erpsystem.admin.view;

import java.util.Scanner;

import com.project.erpsystem.dao.DeducationDao;
import com.project.erpsystem.dao.SalaryStepModifyDao;

public class BasicView {

	public static void PersonPaySetting() {
		//1. 인사급여
		
	}
	
	public static void payStepTableSetting() {
		//2. 호봉테이블 등록
		Scanner scan = new Scanner(System.in);
		
		/*비즈니스 로직*/
		while (true) {
			
			System.out.println("[호봉테이블 등록]");
			System.out.println("================================================================================");
			System.out.println("(단위:원)");
			SalaryStepModifyDao.hobongList();
			System.out.println("================================================================================");

			System.out.println("1. 기본급 수정");
			System.out.println("2. 직책수당 수정");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			switch (input) {
			
				case "1" : HobongModifyView.hobongBasicPayModify(); break;
				case "2" : HobongModifyView.hobongPositionPayModify(); break;
				case "3" : return;
				default  : System.out.println("올바른 번호를 입력해주세요.");
						   System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
						   scan.nextLine();
			
			}//switch
			
		}//while
	}
	
	public static void deducationSetting() {
		//3. 지급공제항목 등록

		boolean loop = true;
		while(loop) {
			
			System.out.println();
			System.out.println("[지급공제항목 등록]");
			System.out.println("1. 지급내역 항목 조회 및 수정");
			System.out.println("2. 공제내역 항목 조회 및 수정");
			System.out.println("3. 이전 화면");
			
			Scanner scan = new Scanner(System.in);
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input){
			case "1" : DeducationView.payItem(); break;
			case "2" : DeducationView.deducation(); break;
			case "3" : return;
			default  : System.out.println("올바른 번호를 입력해주세요.");
			   System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
			   scan.nextLine();
				
			}
		}
	}
	
	public static void payDateSetting() {
		//4. 급/상여 지급일자 등록
	}

	public static void insureanceSetting() {
		//5.사회보험 등록

		boolean loop = true;
		
		while(loop) {
			
			System.out.println();
			System.out.println("[사회보험 환경등록]");
			System.out.println("1. 건강보험");
			System.out.println("2. 고용보험");
			System.out.println("3. 산재보험");
			System.out.println("4. 국민연금");
			System.out.println("5. 이전화면");
			
			Scanner scan = new Scanner(System.in);
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			if (input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")) {
				InsureanceView.insureanceChoice(input); break;
			} else if (input.equals("5")) {
				return;
			} else {
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}
			/*
			switch(input){
			case "1","2","3","4" : InsureanceView.insureanceChoice(input); break;
			case "5" : return;
			default  : System.out.println("올바른 번호를 입력해주세요.");
			   System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
			   scan.nextLine();
				
			}
			*/
		}
	}

	public static void incomeTaxSetting() {
		//6. 소득세액 공제 등록
	}

}
