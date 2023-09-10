package com.project.erpsystem.main;

import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.admin.view.AdminView;
import com.project.erpsystem.dao.AnnualDao;
import com.project.erpsystem.dao.AnnualSubDao;
import com.project.erpsystem.dao.CertificateDao;
import com.project.erpsystem.dao.DeducationDao;
import com.project.erpsystem.dao.HobongDao;
import com.project.erpsystem.dao.PayItemDao;
import com.project.erpsystem.dao.SalaryStepModifyDao;
import com.project.erpsystem.member.view.MemberView;
import com.project.erpsystem.openapi.Test;

public class InitialView {
	
	public static int month;
	public static int date;
	public static String result;
	
	static {
		month=0;
		date=0;
		result="";
	}

	public static void erpStart() {
		
		Scanner scan = new Scanner(System.in);
		
		PersonDao.load(); //기본 인사정보 파일 로드 메소드
		AnnualDao.load();
		AnnualSubDao.load();
		UserDao.load();
		CertificateDao.load(); //[나래 추가-나중에 삭제] 증명서 신청 파일 로드 메소드
		SalaryStepModifyDao.load(); //[나래 추가-나중에 삭제] 호봉테이블 파일 로드 메소드
		DeducationDao.load();
		PayItemDao.load();
		//HobongDao.load();
		
		// openAPI추가부분
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		date = Calendar.getInstance().get(Calendar.DATE);

		if (result.equals("")) {
			result = Test.checkNationalHoliday(month, date);
		}
		
		boolean loop=true;
		
		while(loop){
			
			logo();
			
			System.out.println("안녕하세요, ㈜쌍용아이티 인사관리 ERP 입니다.");
			System.out.println("1. 로그인");
			System.out.println("2. 프로그램 종료");
			
			System.out.print("입력: ");
			String input=scan.nextLine();
			
			if(input.equals("1")) {
				login();
			}else if(input.equals("2")) {
				
				loop=false;
				//System.exit(0);
				
			}else {
				System.out.println("다시 입력해주세요");
				System.out.println("================================================================================");
				System.out.println();
			}
		}
	}

	public static void login() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("아이디: ");
		String id=scan.nextLine();
		System.out.print("비밀번호: ");
		String pw=scan.nextLine();
		
		
		String level=UserDao.checkLogin(id,pw); //로그인 체크 메소드후 level 값 반환
		
		
		
		if(UserDao.auth != null) {
			if(level.equals("1")) { 		//1일반
				loginSuccess();
				MemberView.memberMain();
			}else if(level.equals("2")) { 	//2관리자
				loginSuccess();
				AdminView.adminMain();
			}else {
				loginFailed();
				System.out.println("계속진행하시려면 엔터를 눌러주세요");
				scan.nextLine();
				return;
			}
		}else {
			loginFailed();
			System.out.println("계속진행하시려면 엔터를 눌러주세요");
			scan.nextLine();
			return;
		}
		
	}
	private static void loginSuccess() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("로그인 성공");
	}
	
	private static void loginFailed() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("로그인 실패");
		
	}
	
	private static void logo() {
		System.out.println("                      ._______________________.");
        System.out.println("                      |□ □ □ □ □ □ □ □ □ □ □ □|");
        System.out.println("                      |_______________________|");
        System.out.println("                  *   |□ □ □ □ □ □ □ □ □ □ □ □|");
        System.out.println("                      |_______________________|");
        System.out.println("           *          |□ □ □ □ □ □ □ □ □ □ □ □|          *");
        System.out.println("              .       |_______________________|");
        System.out.println("                      |□ □ □ □ □ □ □ □ □ □ □ □|                         *");
        System.out.println("      ...       `     |_______________________|   +             ...");
        System.out.println("     /   \\  +         |□ □ □ □ □ □ □ □ □ □ □ □|            +   /   \\");
        System.out.println("     \\   /     ...    |_______________________|     ...     .  \\   /");
        System.out.println("   * /   \\.   /   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\ *    ./   \\.");
        System.out.println("     \\   /    \\   /   |_______________________|    \\   /   +   \\   /   '");
        System.out.println("  .  /   \\   */   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\  * .  /   \\");
        System.out.println("     \\   /  . \\   /   |_______________________|    \\   /.      \\   /");
        System.out.println(" +   /   \\    /   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\       /   \\   +");
        System.out.println("     \\   /    \\   /   |_______________________|    \\   /       \\   /");
        System.out.println("      | |      | |    |□ □ □ □ □ □ □ □ □ □ □ □|     | |         | |");
        System.out.println("      |_|      |_|    |_______________________|     |_|         |_|");
        
        
        System.out.println("  ______   _____   ______   _________   ________  _______     _______   ");
        System.out.println(".' ____ \\ |_   _|.' ____ \\ |  _   _  | |_   __  ||_   __ \\   |_   __ \\  ");
        System.out.println("| (___ \\_|  | |  | (___ \\_||_/ | | \\_|   | |_ \\_|  | |__) |    | |__) | ");
        System.out.println(" _.____`.   | |   _.____`.     | |       |  _| _   |  __ /     |  ___/  ");
        System.out.println("| \\____) | _| |_ | \\____) |   _| |_     _| |__/ | _| |  \\ \\_  _| |_     ");
        System.out.println(" \\______.'|_____| \\______.'  |_____|   |________||____| |___||_____|    ");
        System.out.println();
	}
	
	
	
}
