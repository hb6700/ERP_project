package com.project.erpsystem.admin.view;

import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.dao.AnnualDao;
import com.project.erpsystem.dao.AnnualSubDao;
import com.project.erpsystem.dao.CertificateDao;
import com.project.erpsystem.dao.DeducationDao;
import com.project.erpsystem.dao.PayItemDao;
import com.project.erpsystem.dao.RetireDao;
import com.project.erpsystem.dao.SalaryStepModifyDao;
import com.project.erpsystem.main.InitialView;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.openapi.Test;

public class AdminView {

	public static void adminMain() {
		// 관리자 메인 화면
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.print("안녕하세요, 관리자님 ");
			if (!InitialView.result.equals("")) {
				System.out.printf("오늘은 %d월 %d일(%s)입니다.\n", InitialView.month, InitialView.date, InitialView.result);
			} else {
				System.out.printf("오늘은 %d월 %d일입니다.\n", InitialView.month, InitialView.date);
			}
			
			
			System.out.println("1. 기초환경설정");
			System.out.println("2. 인사관리정보");
			System.out.println("3. 근태관리정보");
			System.out.println("4. 급여관리정보");
			System.out.println("5. 퇴직관리");
			System.out.println("6. 증명서 발급 승인 및 조회");
			System.out.println("7. 로그아웃");
			System.out.println();
			System.out.print("번호를 입력해주세요: ");
			String input=scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			
			switch(input) {
			case "1" : basicMenu(); break;
			case "2" : userinfoMenu(); break;
			case "3" : attendanceMenu(); break;
			case "4" : payMenu(); break;
			case "5" : retireMenu(); break;
			case "6" : certificateMenu(); break;
			case "7" : save(); return;
			default  : System.out.println("올바른 번호를 입력해주세요.");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
						scan.nextLine();
			
			}
		}
		
		
		
	}
	
	private static void save() {

		PersonDao.save();
		AnnualDao.save();
		AnnualSubDao.save();
		UserDao.save();
		CertificateDao.save();
		SalaryStepModifyDao.save();
		DeducationDao.save();
		PayItemDao.save();
		
		
	}

	public static void basicMenu() {
		// 1. 기초 환경 설정
		
		/*선언 및 초기화부*/
		Scanner scan = new Scanner(System.in);
		
		/*비즈니스 로직*/
		while (true) {
			
			System.out.println("[기초환경설정]");
			System.out.println("1. 인사급여 환경설정");
			System.out.println("2. 호봉테이블 등록");
			System.out.println("3. 지급공제항목 등록");
			System.out.println("4. 급/상여 지급일자 등록");
			System.out.println("5. 사회보험 환경등록");
			System.out.println("6. 소득세액 공제 환경등록");
			System.out.println("7. 메인 화면");
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch (input) {
			case "1":
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
				break;
			case "2":
				BasicView.payStepTableSetting();
				break;
			case "3":
				BasicView.deducationSetting();
				break;
			case "4":
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
				break;
			case "5":
				BasicView.insureanceSetting();
				break;
			case "6":
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
				break;
			case "7":
				return;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}
			
		}//while
		
		//scan.close();	
	}
	
	public static void userinfoMenu() {
		// 2. 인사 관리 정보
		PersonDepartmentView.PersonDepartmentChView();
		
	}
	
	
	public static void attendanceMenu() {
		// 3. 근태 관리 정보
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("[근태관리정보]");
			System.out.println("1. 조회 및 출/퇴근 시간 조정");
			System.out.println("2. 메인 화면");
			System.out.print("번호를 입력하세요: ");
			int input = scan.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			
			
			switch (input) {

			case 1:

				AttendanceSettingView.checkMonthExistence();
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
	
	public static void payMenu() {
		//4. 급여 관리 정보
		
		while(true) {
			
			AdminPayView.subTitle();
			System.out.println("1. 근로소득 일괄 입력");
			System.out.println("2. 급여명세서 조회");
			System.out.println("3. 메인 화면");
			System.out.print("번호를 입력해주세요: ");
			
			Scanner scan = new Scanner(System.in);
			int input = scan.nextInt();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch (input) {
			case 1: AdminPayView.listAdd(); break;
			case 2: AdminPayView.listCheck(); break;
			case 3: return;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}
		}
	}
	
	public static void retireMenu() {

		// 5. 퇴직 관리
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("[퇴직관리]");
			System.out.println("1. 퇴직자 처리");
			System.out.println("2. 퇴직금 계산");
			System.out.println("3. 이전화면");

			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");

			if (input.equals("1")) { // 1. 퇴사자 처리
				RetireDao.retireeProcess();
			} else if (input.equals("2")) { // 2. 퇴직금 계산
				RetireDao.calcSeverancePay();
			} else if (input.equals("3")) { // 3. 메인화면
				return;
			} else {
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}
		}
	}

	public static void certificateMenu() {
		// 6. 증명서 발급 승인 및 조회

		/* 선언 및 초기화부 */
		Scanner scan = new Scanner(System.in);

		/* 비즈니스 로직 */
		while (true) {

			System.out.println("[증명서 발급 승인 및 조회]");
			System.out.println("[신청 내역]");
			System.out.println("================================================================================");

			// 전직원 증명서 발급 신청 목록 출력 메서드
			CertificateDao.adminCertiRqList();

			System.out.println("================================================================================");

			System.out.println("1. 승인 처리");
			System.out.println("2. 메인화면");
			System.out.print("번호를 입력해주세요: "); 
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");

			switch (input) {
			case "1":
				// 승인 처리 메서드 호출
				CertificateDao.adminApprovalRq();
				break;

			case "2":
				return;

			default:
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}

		} // while

	}// certificateMenu()_6. 증명서 발급 및 승인 조회 메뉴

}
