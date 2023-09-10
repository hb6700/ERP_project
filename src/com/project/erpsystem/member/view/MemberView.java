package com.project.erpsystem.member.view;

import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.dao.AnnualDao;
import com.project.erpsystem.dao.AnnualSubDao;
import com.project.erpsystem.dao.CertificateDao;
import com.project.erpsystem.dao.DeducationDao;
import com.project.erpsystem.dao.MypageDao;
import com.project.erpsystem.dao.PayItemDao;
import com.project.erpsystem.dao.PersonDepartmentCh;
import com.project.erpsystem.dao.SalaryStepModifyDao;
import com.project.erpsystem.main.InitialView;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.openapi.Test;
import com.project.erpsystem.vo.AnnualVo;

public class MemberView {

	public static void memberMain(){
		//일반 회원 메인화면
		Scanner scan = new Scanner(System.in);
		
		boolean loop=true;
		
		while(loop) {
			
			System.out.printf("안녕하세요. %s님. 환영합니다. ",UserDao.auth.getName());
			
			if (!InitialView.result.equals("")) {
				System.out.printf("오늘은 %d월 %d일(%s)입니다.\n", InitialView.month, InitialView.date, InitialView.result);
			} else {
				System.out.printf("오늘은 %d월 %d일입니다.\n", InitialView.month, InitialView.date);
			}
			
			System.out.println("1. 근태확인");
			System.out.println("2. 연차 조회 및 신청");
			System.out.println("3. 급여명세서 확인");
			System.out.println("4. 조직도, 부서별 현황 조회");
			System.out.println("5. 마이페이지");
			System.out.println("6. 증명서 발급 신청");
			System.out.println("7. 로그아웃");
			System.out.println();
			System.out.print("번호를 입력해주세요: ");
			String input=scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			
			switch (input) {
			case "1":
				attendanceRecordMenu();
				break;
			case "2":
				annualMenu();
				break;
			case "3":
				payMenu();
				break;
			case "4":
				organizationMenu();
				break;
			case "5":
				if (MemberView.myMenu() == 1) {
					break;
				} else {
					return;
				}

			case "6":
				certificateMenu();
				break;
			case "7":
				save();
				return;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
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

	public static void attendanceRecordMenu() {
		//1. 근태 확인 화면 
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[근태 확인]");
		
		System.out.print("연도를 입력하세요: ");
		String year = scan.nextLine();
		
		System.out.print("월을 입력하세요: ");
		String month = scan.nextLine();
		AttendanceRecordView.checkExistence(year, month);
		
	}
	
	public static void annualMenu() {
		//2. 연차 조회 및 신청
		
		Scanner scan=new Scanner(System.in);
		

		boolean loop=true;
		
		while(loop) {
			
			System.out.println("[연차 조회 및 신청]");
			AnnualVo temp=AnnualDao.checkAnnualApply();
			System.out.println("[할당 연차]\t[잔여 연차]\t[사용 연차]");
			System.out.printf("%5s\t\t%5s\t\t%5s\n", temp.getAllotmentAnnual(), temp.getLeftoverAnnual(), temp.getUsedAnnal());
			//남은 연차 출력
			System.out.println("================================================================================");
			
			
			System.out.println("1. 연차 신청");
			System.out.println("2. 연차 신청 내역 조회");
			System.out.println("3. 메인 화면");
			System.out.println();
			System.out.print("번호를 입력해주세요: ");
			String input=scan.nextLine();
			
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input) {
			case "1" : 
				if(temp.getLeftoverAnnual() == 0) {
					System.out.println("잔여 연차가 없습니다");
					break;
				}else {
					annualMenu1();
				}
				break;
			case "2" : 
				if(temp.getLeftoverAnnual() == temp.getAllotmentAnnual()) {
					System.out.println("연차 내역이 없습니다");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
					scan.nextLine();
					break;
				}else {
					System.out.println("  [시작일]\t  [종료일]\t[사용 일수]\t[신청사유]");
					System.out.println(AnnualSubDao.checkAnnualApply());
					System.out.println("================================================================================");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
					scan.nextLine();
					break;
				}
				
			case "3" : return;
			
			default : 
				System.out.println("잘못 입력 하셨습니다");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
				break;
			
			}
		
		
		}
		
		
		
	}
	
	public static void annualMenu1() { // 2-1 연차 신청 1번 출력 메소드
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("입력 양식 202x-xx-xx");
		System.out.print("시작일: ");
		String startAnnual=scan.nextLine();
		System.out.print("종료일: ");
		String endAnnual=scan.nextLine();
		System.out.print("신청 사유: ");
		String reason=scan.nextLine();
		System.out.println("--------------------------------------------------------------------------------");
		
		boolean loop=true;
		
		while(loop){
			System.out.print("신청하시겠습니까? (Y/N): ");
			String input=scan.nextLine();
			if(input.toLowerCase().equals("y")) {
				
//				System.out.println(AnnualSubDao.annualCheck(startAnnual,endAnnual));
				if(AnnualSubDao.annualCheck(startAnnual,endAnnual)) {
					int result=AnnualSubDao.annualSubApply(startAnnual,endAnnual,reason);
					if(result == -1) {
						System.out.println("잔여연차가 없습니다");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
						scan.nextLine();
						break;
					}else if(result == 1) {
						System.out.println("신청 완료되었습니다");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
						scan.nextLine();
						break;
					}
					else { //0 
						System.out.println("주말 휴가 신청을 하셨습니다");
						System.out.println("확인 부탁드립니다");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
						scan.nextLine();
						break;
					}
					
				}else {
					System.out.println("잘못 입력 하셨습니다");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
					scan.nextLine();
					break;
				}
			}else if(input.toLowerCase().equals("n")) {
				System.out.println("신청을 취소하였습니다");
				System.out.println("[연차조회 및 신청]화면으로 돌아갑니다");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
				break;
			}else {
				System.out.println("Y 또는 N 입력 바랍니다");
			}
			
		}
		
	}
	
	
	public static void payMenu() {
		//3. 급여명세서 확인
		
		while(true) {
			
			Scanner scan=new Scanner(System.in);
			
			MemberPayView.subTitle();
			System.out.println("1. 급여명세서 조회");
			System.out.println("2. 메인 화면");
			System.out.print("번호를 입력해주세요: ");
			String input=scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input) {
			case "1" : MemberPayView.paycheck(); break;
			case "2" : return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
			}
		}
		
	}
	
	public static void organizationMenu() {
		//4. 조직도, 부서별 현황 조회
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("\t\t쌍용 주식회사");
			System.out.println("\t\t     |");
			System.out.println("    ┌──────┬──────┬──────┬──────┬─────┐");
			System.out.println("  인사팀   구매팀  기획팀   영업팀  개발팀  재무팀");
			System.out.println("================================================================================");
			System.out.println("1. 부서별 현황 조회");
			System.out.println("2. 메인화면");
			System.out.println();
			System.out.print("번호를 입력해주세요: ");
			String input=scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			if(input.equals("1")) {
				System.out.print("부서 입력: ");
				String dp=scan.nextLine();
				if(dp.equals("인사팀") || dp.equals("구매팀") || dp.equals("기획팀")
						|| dp.equals("영업팀") || dp.equals("개발팀") || dp.equals("재무팀")) {
					System.out.println("[부서]\t[직급]\t[이름]\t[전화번호]");
					System.out.println(PersonDepartmentCh.checkDepartmentMember(dp));
					System.out.println("================================================================================");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
					scan.nextLine();
				}else {
					System.out.println("잘못 입력 하셨습니다");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
					scan.nextLine();
				}
				
				
				
			}else if(input.equals("2")) {	//메인화면
				return;
			}else {
				System.out.println("잘못 입력 하셨습니다");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
		}
		
		
		
	}
	
	public static int myMenu() {
		//5. 마이 페이지

		/*선언 및 초기화부*/
		Scanner scan = new Scanner(System.in);

		/* 비즈니스 로직 */
		while (true) {
			
			System.out.println("[마이페이지]");
			System.out.println("1. 인사 현황 조회");
			System.out.println("2. 비밀번호 변경");
			System.out.println("3. 메인화면");
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch (input) {
			case "1":
				// 인사 현황 조회 메서드
				MypageDao.checkUserInfo();
				break;
				
			case "2":
				// 비밀번호 변경 메서드
				if (MypageDao.changePw() == 1) {
					break;
				} else {
//					InitialView.erpStart();
					return 0;
//					break;
				}
				
			case "3":
				// 일반회원 메인화면으로 돌아가기
				return 1;
				
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
				scan.nextLine();
				
			}// switch
		}


//		}//while
	}
	
	public static void certificateMenu() {
		//6 증명서 발급 신청

		/*선언 및 초기화부*/
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		/*비즈니스 로직*/
		while (loop) {
			
			
			System.out.println("[증명서 발급 신청]");
			System.out.println("[신청 내역]");
			System.out.println("================================================================================");
			
			//로그인 직원 본인의 증명서 발급 신청 목록 출력 메서드
			CertificateDao.memberCertiRqList();
			
			System.out.println("================================================================================");
			System.out.println("1. 재직증명서 발급 신청");
			System.out.println("2. 경력증명서 발급 신청");
			System.out.println("3. 메인화면");
			System.out.print("번호를 입력해주세요: "); //TODO 나중에 문구 수정
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			
			switch (input) {
				case "1" :
					//재직증명서 발급 신청 메서드
					CertificateDao.employmentCertiRq();
					break;
					
				case "2" :
					//경력증명서 발급 신청 메서드
					CertificateDao.careerCertiRq();
					break;
					
				case "3" :
					//일반회원 메인화면으로 돌아가기
					return;
					
				default : 
					System.out.println("올바른 번호를 입력해주세요.");
					System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
					scan.nextLine();
			
			}//switch
			
		}//while
		
		scan.close();
	}
	
	
	
	
}
