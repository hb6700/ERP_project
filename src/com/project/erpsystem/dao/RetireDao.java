package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.main.UserVo;

public class RetireDao {

	
	//1. 퇴직자 관리
	public static void retireeProcess() {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("사번: ");
		String id=scan.nextLine();
		
		boolean loop=true;

		for(UserVo uv : UserDao.list) {
			//퇴사 처리 사원확인
			if(uv.getId().equals(id)) {
				for(PersonVo p : PersonDao.list) {
					if(p.getId().equals(id)) {
						
					
						if(p.getWorkStatus().equals("true")) {
							System.out.printf("%s님을 퇴직처리 하시겠습니까?(Y/N) ", p.getName());
							String input = scan.nextLine();
							while(loop){
								if(input.equals("Y") || input.equals("y")) {
									p.setWorkStatus("false");
									PersonDao.save();
									System.out.printf("%s님을 퇴직처리 했습니다\n", p.getName());
									System.out.println("================================================================================");
									//M103,2010-05-23,2011-05-25,1,2100248
									//사번

									int sum=0;
									sum+=salary(p.getId(), 5);						//5월 월급 더하기
									sum+=salary(p.getId(), 6);						//6월 월급 더하기
									sum+=salary(p.getId(), 7);						//7월 월급 더하기
									int calcSeverancePay = (sum/calc_prev_3_month_days()) * 30 * (calc_work_days(p.getBeginDate()) / 365);

									try {
										FileWriter fw = new FileWriter("data\\severancePay.txt", true);
										fw.write(p.getId() + "," + p.getBeginDate() + "," + LocalDate.now() + "," +  (calc_work_days(p.getBeginDate())/365) + "," + calcSeverancePay + "\n");	//calcSeverancePay
										fw.close();
									}catch(Exception e){
										e.printStackTrace();
									}
									return;
								}else if(input.equals("N") || input.equals("n")) {
									System.out.println("다시 입력해주세요");
									loop=false;
								}else {
									System.out.println("다시 입력해주세요");
									loop=false;
								}
							}	
						}
						else {
							System.out.printf("%s님은 퇴사자 입니다\n", p.getName());
							System.out.println("=======================================");
						}
						return;
					}
				}
			}
		}
	}
	
	
	//2. 퇴직금 계산
	public static void calcSeverancePay() {
		Scanner scan = new Scanner(System.in);
		
		//사번 입력
		System.out.print("사번: ");
		String id=scan.nextLine();
		
		
		//재직자인지 아닌지 확인하기 위해 데이터 대조 
		for(PersonVo p : PersonDao.list) {
			if(p.getId().equals(id)) {						//...음...
//				sevenPay();

				if(p.getWorkStatus().equals("true")) {				//만약 ~가 true라면(재직자라면)
					int sum=0;
					sum+=salary(p.getId(), 5);						//5월 월급 더하기
					sum+=salary(p.getId(), 6);						//6월 월급 더하기
					sum+=salary(p.getId(), 7);						//7월 월급 더하기
					
					System.out.printf("%s님은 재직자입니다.\n",p.getName());
					System.out.printf("%s의 입사일 : %s\n", p.getName(), p.getBeginDate());
					System.out.printf("재직일수 : %d(일)\n", calc_work_days(p.getBeginDate()));
					//System.out.printf("이전 3개월 일자 합 : %d\n", calc_prev_3_month_days());
					//System.out.printf("이전 3개월 월급 합 : %,d\n", sum);
					
					//퇴직금 계산 : 1일 평균임금(퇴직일 이전 3개월 동안 지급 받은 임금의 총액(실수령액 기준)/퇴직일 이전 3개월의 총 일 수) X 30(일) X (재직일수/365)
					//sum : 퇴직일 이전 3개월 동안 지급받은 임금의 총액(실수령액 기준)
					//calc_prev_3_month_days() : 퇴직일 이전 3개월의 총 일 수
					//calc_work_days(p.getBeginDate()) : 재직일수
					int calcSeverancePay = (sum/calc_prev_3_month_days()) * 30 * (calc_work_days(p.getBeginDate()) / 365);
					System.out.printf("%s님의 예상 퇴직금 : %,d\n",p.getName(), calcSeverancePay);
					System.out.println("================================================================================");
				}
				else {
					System.out.printf("%s님은 퇴사자 입니다\n",p.getName());
					int retireeMoney = paidSeverancePay(p.getId());
					System.out.printf("지급된 퇴직금 : %,d\n", retireeMoney);
					System.out.println("================================================================================");
				}
				break;
			}
		}
	}
	

	
	//퇴직금 파일 불러오기
	public static int paidSeverancePay(String id) {
		try {
			String dir="data\\severancePay.txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				if(temp[0].equals(id)) {
					return (Integer.parseInt(temp[4]));			//퇴직금 파일 > 퇴직금 급여액
				}
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//재직일수 구하기
	public static int calc_work_days(String strDate) {
		//날짜빼기
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(strDate, formatter);
        int DiffDays = (int) ChronoUnit.DAYS.between(dateTime, now);
        return DiffDays;
	}
	
	
	//지난 3개월 일 수의 합 구하기
	public static int calc_prev_3_month_days() {
		
		LocalDate now = LocalDate.now();
		int days = 0;
		for(int i=1;i<4;i++) {
			LocalDate temp_date = now.minusMonths(i);
//			temp_date = temp_date.withDayOfMonth();
//			System.out.printf("%d월 누적 %d일\n", temp_date.getMonthValue(), days);
			
			//이전 3개월을 계산한거 
			days+=temp_date.lengthOfMonth();
		}
		return days;
	}
	
	
	//월급 계산 -> 급여데이터의 실수령액 가져오는 부분
	public static int salary(String id, int month) {
		
		int money=0;
		try {
			//혹시 데이터 파일이 추가될까봐 + 같은 소스 세번 써야 될거 같아서 >> '%d'사용
			String dir=String.format("data\\PayStub%d.txt", month);
			
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			String line=null;
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				if(id.equals(temp[0])) {
					money = Integer.parseInt(temp[16]);
					break;						
				}
			}		
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return money;
	}
	
}
