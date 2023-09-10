package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.erpsystem.vo.SalaryStepModifyVo;

public class SalaryStepModifyDao {

	public static ArrayList<SalaryStepModifyVo> list;
	
	static {
		list = new ArrayList<SalaryStepModifyVo>();
	}
	
	static String path = "data\\hobong.txt";
	
	public static void load() {
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null)	{
				
				String[] temp = line.split(",");
				
				SalaryStepModifyVo s = new SalaryStepModifyVo(temp[0], temp[1], temp[2], temp[3]);
				
				SalaryStepModifyDao.list.add(s);
				
			}//while
			reader.close();
			
		} catch (Exception e) {
			System.out.println("at SalaryStepModifyDao.load");
			e.printStackTrace();
		}
		
	}
	
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (SalaryStepModifyVo s : SalaryStepModifyDao.list) {
				
				writer.write(String.format("%s,%s,%s,%s\r\n"
									, s.getPosition()
									, s.getHobong()
									, s.getBasicPay()
									, s.getPositionPay()));
				
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("at SalaryStepModifyDao.save");
			e.printStackTrace();
		}

	}
	
	
	//호봉테이블 출력하는 메서드
	public static void hobongList() {
		
		System.out.println("[직급]\t[호봉]\t [기본급]    [직책수당]");
		
		for (SalaryStepModifyVo s : list) {
			
			System.out.printf(" %s\t  %s\t%,7d   %,7d\n"
								, s.getPosition()
								, s.getHobong()
								, Integer.parseInt(s.getBasicPay())
								, Integer.parseInt(s.getPositionPay()));
		}//for
		
	}
	
	
	//기본급 조회 및 수정 메서드
	public static boolean hobongBasicPayModify(String position, String hobong) {
		
		Scanner scan = new Scanner(System.in);
		boolean frag = false;
		try {
			/*
			Scanner scan = new Scanner(System.in);
			
			System.out.println("[기본급 조회 및 수정]");
			System.out.println();
			
			System.out.print("수정할 직급을 입력해주세요: ");
			String position = scan.nextLine();
			
			System.out.print("수정할 호봉(숫자)을 입력해주세요: ");
			String hobong = scan.nextLine();
			*/
			
			for (SalaryStepModifyVo s : list) {
				
				if (s.getPosition().equals(position) && s.getHobong().equals(hobong)) {
					frag = true;
					System.out.println("-----------------------------");
					
					//해당 직급, 호봉의 현재 기본급 출력
					System.out.printf("[%s]직급 [%s호봉]의 기본급은 '%,d(원)'입니다."
							, s.getPosition()
							, s.getHobong()
							, Integer.parseInt(s.getBasicPay()));
					
					//해당 직급, 호봉의 변경할 기본급 입력받고 수정
					System.out.println();
					System.out.print("변경할 기본급 금액(단위: 원)을 입력해주세요: ");
					String newpay = scan.nextLine();
					
					System.out.println("-----------------------------");
					System.out.printf("입력하신 금액은 [%,d(원)]입니다.", Integer.parseInt(newpay));
					
					while(true) {
						
						System.out.print("수정하시겠습니까? (Y/N): ");
						String input = scan.nextLine();
						
						if (input.equalsIgnoreCase("Y")) {
							
							s.setBasicPay(newpay);
							System.out.println("수정이 완료되었습니다.");
							System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
							scan.nextLine();
							return true;
							
						} else if (input.equalsIgnoreCase("N")) {
							
							System.out.println("수정이 취소되었습니다.");
							System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
							scan.nextLine();
							return true;
							
						} else {
							
							System.out.println("Y 또는 N으로 입력해주세요.");
						}					
					}//while
				}//if
			}//for
			
//			scan.close(); //여기서 스캐너 닫으면 메서드 종료되고 java.util.NoSuchElementException 예외 발생 > AdminView.hobongTable(AdminView.java:105) 여기에서
			
		} catch (NumberFormatException e) {
			System.out.println("금액은 정수만 입력 가능합니다.");
		}
		return frag;
			
			
	}
	
	
	//직책수당 조회 및 수정 메서드
	public static boolean hobongPositionPayModify(String position) {
		boolean frag = false;
		try {
			Scanner scan = new Scanner(System.in);
			for (SalaryStepModifyVo s : list) {
				
				if (s.getPosition().equals(position)) {
					frag = true;
					System.out.println("----------------------");
				
					System.out.printf("[%s]직급직책수당은 '%,d(원)'입니다."
										, s.getPosition()
										, Integer.parseInt(s.getPositionPay()));

					System.out.println();
					
					System.out.print("변경할 직책수당 금액(단위: 원)을 입력해주세요: ");
					String newpay = scan.nextLine();
					
					System.out.println("----------------------");
					System.out.printf("입력하신 금액은 [%,d(원)]입니다.", Integer.parseInt(newpay));
						
					//여기아래부터 와일문 넣어보기
					while (true) {
						System.out.print("수정하시겠습니까? (Y/N): ");
						String input = scan.nextLine();

						if (input.equalsIgnoreCase("Y")) {
							for (SalaryStepModifyVo s2 : list) {
								if (s2.getPosition().equals(position)) {
									s2.setPositionPay(newpay);
								}
							}
							System.out.println("수정이 완료되었습니다.");
							System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
							scan.nextLine();
							return true;

						} else if (input.equalsIgnoreCase("N")) {

							System.out.println("수정이 취소되었습니다.");
							System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
							scan.nextLine();
							return true;

						} else {

							System.out.println("Y 또는 N으로 입력해주세요.");
						}

					} // while
				}//if
			}//for
			
			
		} catch (NumberFormatException e) {
			System.out.println("금액은 정수만 입력 가능합니다.");
		}
		return frag;
	}//직책수당 조회 및 수정
	
	
	
}
