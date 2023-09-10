package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.erpsystem.admin.view.AdminView;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.member.view.MemberView;
import com.project.erpsystem.vo.CertificateVo;

public class CertificateDao {

	public static ArrayList<CertificateVo> list;
	
	static {
		
		list = new ArrayList<CertificateVo>();
		
	}
	
	static String path = "data\\certiRqList.txt";
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				CertificateVo c = new CertificateVo(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				
				CertificateDao.list.add(c);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("at CertificateDao.load");
			e.printStackTrace();
		}
		
	}//load
	
	public static void save() {
		
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (CertificateVo c : list) {
				
				writer.write(String.format("%d,%s,%s,%d,%d\r\n"
										, c.getNo()
										, c.getRqDate()
										, c.getId()
										, c.getType()
										, c.getCertStatus()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("at CertificateDao.save");
			e.printStackTrace();
		}
		
	}//save
	
	
	//일반회원용 본인 신청 내역 조회 메서드
	public static void memberCertiRqList() {
		
		//처음 load() 실행으로 ArrayList에 저장한 list를 불러와서 로그인한 본인의 사번과 일치하는 해당 내역만 출력.
		
		int n = 1;
		int listFlag = 0;
		

		for (CertificateVo c : list) {
			
			if (c.getId().equals(UserDao.auth.getId())) {
				
				//기신청내역 있을 시 항목 타이틀 보여주기
				if (listFlag == 0) {
					
					System.out.println("[일련번호]\t[날짜]\t[사번]\t[이름]\t[부서]\t[신청 증명서 종류]\t[승인 상태]");
					listFlag = 1;
					
				}
				
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n"
									, n++
									, c.getRqDate()
									, c.getId()
									, UserDao.auth.getName()
									, UserDao.auth.getDepartment()
									, c.getType() == 1 ? "재직증명서" : "경력증명서"
									, c.getCertStatus() == 1 ? "승인 완료" : "승인 전");
			}//if
			
		}//for
		
		//신청내역 없으면 없다고 출력
		if (listFlag == 0) {
			System.out.println("신청 내역이 없습니다.");
		}
		
	}//memberCertiRqList
	
	public static void employmentCertiRq() {
		//일반회원용 재직증명서 발급 신청 메서드
		
		Scanner scan = new Scanner(System.in);
		
		
		while (true) {
			
			System.out.print("재직증명서 발급을 신청하시겠습니까? (Y/N): ");
			String input = scan.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				
				Calendar cal = Calendar.getInstance();
				
				CertificateVo c = new CertificateVo(list.size() + 1, String.format("%tF", cal), UserDao.auth.getId(), 1, 0);
				
				list.add(c);
				
				
				System.out.println("재직증명서 발급신청이 완료되었습니다.");
				System.out.println("이전 페이지로 돌아가시려면 Enter를 입력해주세요.");
				scan.nextLine();
				return;
				
				
			} else if (input.equalsIgnoreCase("N")) {
				
				System.out.println("재직증명서 발급신청이 취소되었습니다.");
				System.out.println("이전 페이지로 돌아가시려면 Enter를 입력해주세요.");
				scan.nextLine();
				return;
				
			} else {
				
				System.out.println("Y 또는 N으로 입력해주세요.");
			}			
		}//while
		
	}//employmentCertiRq()
	
	
	
	
	//일반회원용 경력증명서 발급 신청 메서드
	public static void careerCertiRq() {
		
		Scanner scan = new Scanner(System.in);
		
		
		while (true) {
			
			System.out.print("경력증명서 발급을 신청하시겠습니까? (Y/N): ");
			String input = scan.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				
				Calendar cal = Calendar.getInstance();
				
				CertificateVo c = new CertificateVo(list.size() + 1, String.format("%tF",  cal), UserDao.auth.getId(), 2, 0);
				
				list.add(c);
				
				System.out.println("경력증명서 발급신청이 완료되었습니다.");
				System.out.println("이전 페이지로 돌아가시려면 Enter를 입력해주세요.");
				scan.nextLine();
				return;
				
				
			} else if (input.equalsIgnoreCase("N")) {
				
				System.out.println("경력증명서 발급신청이 취소되었습니다.");
				System.out.println("이전 페이지로 돌아가시려면 Enter를 입력해주세요.");
				scan.nextLine();
				return;
				
			} else {
				
				System.out.println("Y 또는 N으로 입력해주세요.");
			}
		}//while
		
	}
	
	//관리자용 전직원 증명서 발급 신청내역 조회 메서드
	public static void adminCertiRqList() {
		
		System.out.println("[일련번호]    [신청일]\t[사번]\t[신청자명]\t [부서]\t[증명서 종류]  [처리 상태]");
		
		for (CertificateVo c : list)
			
			System.out.printf("%5d \t  %s\t%s\t %s\t %s\t %s    %s\n"
							, c.getNo()
							, c.getRqDate()
							, c.getId()
							, getname(c)
							, getdepartment(c)
							, c.getType() == 1 ? "재직증명서" : "경력증명서"
							, c.getCertStatus() == 0? "승인 전" : "승인 완료");
		
	}//adminCertiRqList
	
	//PersonVo에서 사번으로 부서 가져오기
	private static String getdepartment(CertificateVo c) {
		
		for (PersonVo p : PersonDao.list) {
			
			if (c.getId().equals(p.getId())) {

				return p.getDepartment();
			}
		}
		
		return "";
		
	}

	//PersonVo에서 사번으로 이름 가져오기
	private static String getname(CertificateVo c) {
		
		for (PersonVo p : PersonDao.list) {
			
			if (c.getId().equals(p.getId())) {
				
				return p.getName();
			}
		}
		
		return "";
		
	}

	//관리자용 증명서 발급 승인 메서드
	public static void adminApprovalRq() {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			while (true) {
				System.out.print("증명서 발급 신청을 승인할 일련번호를 입력해주세요: ");
				String no = scan.nextLine();

				System.out.println("--------------------------------------------------------------------------------");
				
				//for문 돌려서 일련번호 일치하는거 찾기
				for (CertificateVo c : list) {
					
					if (c.getNo() == Integer.parseInt(no)) {
						
						if (c.getCertStatus() == 0) {
							
							c.setCertStatus(1);
							System.out.println("증명서 발급 신청이 승인되었습니다.");
							System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
							scan.nextLine();
							return;
							
						} 
						System.out.println("기승인 완료된 건입니다.");
								System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
						scan.nextLine();
						return;
						
					}
					
				}//for
				
				System.out.println("올바른 번호를 입력해주세요.");
			}//while
			
		} catch (NumberFormatException e) {
			System.out.println("일련번호는 정수만 입력 가능합니다.");
		}
		
	}//adminApprovalRq()
	
	
	
	
	
	
}
