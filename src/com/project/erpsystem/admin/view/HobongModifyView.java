package com.project.erpsystem.admin.view;

import java.util.Scanner;

import com.project.erpsystem.dao.SalaryStepModifyDao;

public class HobongModifyView {
	public static void hobongBasicPayModify() {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("[기본급 조회 및 수정]");
			System.out.println();
			
			System.out.print("수정할 직급을 입력해주세요: ");
			String position = scan.nextLine();
			
			System.out.print("수정할 호봉(숫자)을 입력해주세요: ");
			String hobong = scan.nextLine();
			
			if (!SalaryStepModifyDao.hobongBasicPayModify(position, hobong)) {
				System.out.println("올바른 직급 또는 호봉을 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			} else {
				return;
			}
			
		}
		
	}

	public static void hobongPositionPayModify() {
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("[직책수당 조회 및 수정]");
			System.out.println();
			
			System.out.print("수정할 직급을 입력해주세요: ");
			String position = scan.nextLine();
			
//			System.out.print("수정할 호봉(숫자)을 입력해주세요: ");
//			String hobong = scan.nextLine();
			
			if (!SalaryStepModifyDao.hobongPositionPayModify(position)) {
				System.out.println("올바른 직급을 입력해주세요.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
				scan.nextLine();
			} else {
				return;
			}
			
			
		}
		
	}
}
