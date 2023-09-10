package com.project.erpsystem.admin.view;

import java.util.Scanner;

import com.project.erpsystem.dao.DeducationDao;

public class InsureanceView {

	public static void subTitle(String str) {
			System.out.println("[" + str + "]");
			
	}

	public static void insureanceChoice(String input) {
		
		boolean loop = true;
		
		while(loop) {
			
			switch(input){
			case "1" : InsureanceView.subTitle("건강보험"); break;
			case "2" : InsureanceView.subTitle("고용보험"); break;
			case "3" : InsureanceView.subTitle("산재보험"); break;
			case "4" : InsureanceView.subTitle("국민연금"); break;
			}
			
			
			System.out.println("1. 가입자 부담율 수정");
			System.out.println("2. 사업자 부담율 수정");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력해주세요: ");
			
			Scanner scan = new Scanner(System.in);
			
			String input2 = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			switch(input2) {
			case "1" : DeducationDao.subscriberRate(input); break;
			case "2" : DeducationDao.companyRate(input); break;
			case "3" : loop=false; break;
			default : System.out.println("올바른 숫자를 입력해주세요");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
						scan.nextLine();
			}
			
		}
		
		
		
	}

}
