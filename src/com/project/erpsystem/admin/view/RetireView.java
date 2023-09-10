package com.project.erpsystem.admin.view;

import com.project.erpsystem.dao.RetireDao;

import java.util.Scanner;

public class RetireView {

	public static void retire() {
		
		// 5. 퇴직 관리
		Scanner scan = new Scanner(System.in);
		boolean loop=true;

		while(loop)
		{
			System.out.println("[퇴직관리]");
			System.out.println("1. 퇴직자 처리");
			System.out.println("2. 퇴직금 계산");
			System.out.println("3. 이전화면");
			
			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			System.out.println("=======================================");

			if (input.equals("1")) { 				// 1. 퇴사자 처리
				RetireDao.retireeProcess();
			} else if (input.equals("2")) { 		// 2. 퇴직금 계산
				RetireDao.calcSeverancePay();
			} else if (input.equals("3")) { 		// 3. 메인화면
				loop = false;
				AdminView.adminMain();
//				return;
			}
		}
		
	}
	
}
