package com.project.erpsystem.admin.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.erpsystem.dao.DeducationDao;
import com.project.erpsystem.dao.PayItemDao;

public class DeducationView {
	
	public static void deducation() {
	
		while(true) {
			
			System.out.println("[공제내역 항목 조회 및 수정]");
			System.out.println("================================================================================");
			DeducationDao.listShow();
			
			System.out.println("================================================================================");

			System.out.println("1. 사용여부 수정");
			System.out.println("2. 항목 등록");
			System.out.println("3. 항목 삭제");
			System.out.println("4. 이전 화면");
			System.out.print("번호를 입력해주세요: ");
			
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input) {
			
			case "1" : 
				System.out.print("수정할 항목의 번호를 입력해주세요: ");
				int num = scan.nextInt();
				System.out.println("--------------------------------------------------------------------------------");
				if(!DeducationDao.toUseChange(num)) {
					System.out.println(""); 
					scan.nextLine();
				}
				break;
			case "2":
				System.out.print("등록할 항목을 입력해주세요(String): ");
				String item = scan.nextLine();
				try {
					System.out.print("사용자부담율을 입력해주세요(단위 : %): ");
					double subscriberRate = scan.nextDouble();
					
					System.out.print("사업자부담율을 입력해주세요(단위 : %): ");
					double companyRate = scan.nextDouble();
					
					System.out.println("--------------------------------------------------------------------------------");
					if(!DeducationDao.itemAdd(item, subscriberRate, companyRate)) {
						System.out.println(""); 
						scan.nextLine();
					}
					
					
				} catch (InputMismatchException e) {
					System.out.println("숫자형으로 입력해주세요.\n");
				}
				break;
				
			case "3":
				System.out.print("삭제할 항목의 번호를 입력해주세요: ");
				num = scan.nextInt();
				System.out.println("--------------------------------------------------------------------------------");
				if(!DeducationDao.delete(num)) {
					System.out.println(""); 
					scan.nextLine();
				}
				
				break;
			case "4":
				return;
			default :
				System.out.println("유효한 번호를 입력해주세요");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
		}
		
	
		}
	}


	public static void payItem() {
		
		while(true) {
			
			System.out.println("[지급내역 항목 조회 및 수정]");
			System.out.println("================================================================================");
			
			PayItemDao.listShow();
			System.out.println("================================================================================");

			System.out.println("1. 사용여부 수정");
			System.out.println("2. 항목 등록");
			System.out.println("3. 항목 삭제");
			System.out.println("4. 이전 화면");
			System.out.print("번호를 입력해주세요: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input) {
			case "1" : 
				while(true) {
					
					System.out.print("수정할 항목의 번호를 입력해주세요: ");
					int num = scan.nextInt();
					System.out.println("--------------------------------------------------------------------------------");
					if (PayItemDao.toUseChange(num)) {
						break;
					} else {
						System.out.println();
					}
				}
				break;
				
			case "2":
				System.out.print("등록할 항목을 입력해주세요(String): ");
				String item = scan.nextLine();
//			
//			System.out.println("사용자부담율을 입력해주세요(단위 : %): ");
//			double subscriberRate = scan.nextDouble();
//			
//			System.out.println("사업자부담율을 입력해주세요(단위 : %): ");
//			double companyRate = scan.nextDouble();
//			
				System.out.println("--------------------------------------------------------------------------------");
				PayItemDao.itemAdd(item);
				
//			DeducationDao.itemAdd(item, subscriberRate, companyRate);
				
				break;
			case "3":
				System.out.print("삭제할 항목의 번호를 입력해주세요: ");
				int num = scan.nextInt();
				System.out.println("--------------------------------------------------------------------------------");
				if(!PayItemDao.delete(num)) {
					System.out.println();
				}
				
				break;
			case "4":
				return;
				
			default :
				System.out.println("유효한 번호를 입력해주세요");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			}
		}
		
		
	}
	
	
}
