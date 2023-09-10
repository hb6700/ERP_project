package com.project.erpsystem.dao;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.main.UserVo;

public class MypageDao {
	
	//로그인한 사용자 인사 현황 조회 메서드
	public static void checkUserInfo() {
		//이름, 생년월일, 전화번호, 주소, 부서, 직급, 입사일, 면허/자격, 학력/경력, 고과/상벌을 출력한다.
		//‘메인 화면으로 돌아가시겠습니까? (Y/N)’ 메시지를 출력한다.
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("[마이페이지 - 인사 현황 조회]");
			System.out.println("[이름]\t[생년월일] \t[전화번호]\t[주소]\t[부서]\t[직급]\t[호봉]\t[입사일]\t[면허/자격]\t[학력/경력]\t[고과/상벌]");
			System.out.println("=================================================================================================================================");
			
			//로그인하면서 auth에 저장된 사용자의 인사기록정보를 출력하기
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
					, UserDao.auth.getName()
					, UserDao.auth.getBirthDay()
					, UserDao.auth.getTel()
					, UserDao.auth.getAddr()
					, UserDao.auth.getDepartment()
					, UserDao.auth.getPosition()
					, UserDao.auth.getHobong()
					, UserDao.auth.getBeginDate()
					, UserDao.auth.getLicence().equals("") ? "*" : UserDao.auth.getLicence()
							, UserDao.auth.getSchool().equals("") ? "*" : UserDao.auth.getSchool()
									, UserDao.auth.getRating().equals("0") ? "*" : UserDao.auth.getRating());
			
			System.out.println("=================================================================================================================================");
			System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
			scan.nextLine();
			return;
			//return 없어도 다 끝났으니 호출한곳으로 돌아가지 않나??
		}
		
	
	}
	
	
	
	//login 파일이 저장된 vo를 사용 할 예정 > uservo
	//비밀번호 변경하는 메서드 > 변경완료해서 return: 0, 그외 나머지break: 1
	public static int changePw() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[마이페이지 - 비밀번호 변경]");
		
		//0. 현재 비밀번호를 저장할 변수 선언
		String currentPw = null; //null vs ""?
		String newPw = null;
		
		//1. 현재 비밀번호 확인
		while (true) {
			
			System.out.print("현재 비밀번호를 입력해주세요: ");
			String input = scan.nextLine();
			
			//for문을 돌아서 list에서 로그인한 사용자의 아이디와 입력받은 input(현재 비밀번호)이 일치하는것을 찾아.
			for (UserVo u : UserDao.list) {
				if (u.getId().equals(UserDao.auth.getId()) && u.getPw().equals(input)) {
					
					currentPw = u.getPw();
					break;
				}
			}//for
			
			//현재 비밀번호 일치여부 검증
			if (currentPw == null) { //일치 비밀번호 없음
				
				System.out.println("현재 비밀번호가 잘못 입력되었습니다.");
				System.out.println("다시 입력해주세요.");
			
			} else { //비밀번호 일치
				
				break;
			}
			
		}//while
		
		//2. 변경할 비밀번호 확인
		while (true) {
			
			System.out.println("\n비밀번호는 8~12자리 영문 또는 숫자로 입력해주세요.");
			System.out.print("변경할 비밀번호를 입력해주세요: ");
			newPw = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			//비밀번호 유효성 검사
			if (isValidPw(newPw) == false) {
				
				System.out.println("비밀번호는 8~12자리 영문 또는 숫자로 입력해주세요.");
				continue;
				
			} else {
				
				//변경하시겠습니까?Y/N 입력받는거 판단
				while (true) {
					
					System.out.print("비밀번호를 변경하시겠습니까? (Y/N): ");
					String input = scan.nextLine();
					System.out.println("--------------------------------------------------------------------------------");
					
					if (input.equalsIgnoreCase("Y")) {
						
						for (UserVo u : UserDao.list) {
							
							if (u.getId().equals(UserDao.auth.getId())) {
								u.setPw(newPw);
							}
							
						}
						System.out.println("비밀번호 변경이 완료되었습니다.");
						System.out.println("초기화면으로 이동하시려면 Enter를 입력해주세요."); 
						UserDao.save();
						UserDao.auth = null;
						scan.nextLine();
						return 0; 
						
					} else if (input.equalsIgnoreCase("N")) {
					
						System.out.println("비밀번호 변경이 취소되었습니다.");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
						scan.nextLine();
						return 1;
						
					} else {
						
						System.out.println("Y 또는 N으로 입력해주세요.");
						continue;
					}
					
				}//Y/N입력 while	
			}//if
			
		}//while
		
	}


	//비밀번호 유효성 검사 메서드
	private static boolean isValidPw(String newPw) {

		/* 비밀번호 > 8~12자리 영문과 숫자 조합 입력만 허용한다. */
		//조건에 해당하지 않는 비밀번호 입력 시, ‘비밀번호는 8~12자리 영문과 숫자 조합으로 입력해주세요.’ 변경할 비밀번호 재입력 받는다.

		String regex = "^[A-Za-z0-9]{8,12}$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(newPw);
		
		if (!m1.find()) {
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
}
