package test;

import java.util.Calendar;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		System.out.println("[할당 연차]\t[잔여 연차]\t[산용 연차]");
		System.out.printf("%5s\t\t%5s\t\t%5s\n", 12, 0, 12);
		
		
//		String[] a= {"02","01"};
//		
//		System.out.println(Integer.parseInt(a[0]));
		
		int a=1000;
		int b=2000;
		
		String s="김수진";
		
		System.out.println(s.length());
		
		String startAnnual="2023-08s09";
		String endAnnual="2023-13-89";
			
		
		
		int countS=0;
		for(int i=0; i<startAnnual.length(); i++) { 
			if(startAnnual.charAt(i) == '-') {
				countS++;
			}
		}
		int countE=0;
		for(int i=0; i<endAnnual.length(); i++) { 
			if(endAnnual.charAt(i) == '-') {
				countE++;
			}
		}
		if(countS != 2 || countE != 2) { 
			System.out.println("잘못 입력하셨습니다");
//			MemberView.annualMenu1();
		}
		
		System.out.println();
		
		String reAn=startAnnual.replace("-","")+endAnnual.replace("-", "");
		System.out.println(reAn);
		for(int i=0; i<reAn.length(); i++) {
			if(reAn.charAt(i) < '0' || '9' < reAn.charAt(i)){
				System.out.println("잘못 입력하셨습니다");
//				MemberView.annualMenu1();
			}
		}
		
		
		System.out.println("맞음");
		
		System.out.println("------------------");
		
		startAnnual="2023-12-30";
		endAnnual="2023-12-31";
		
		String[] stAn=startAnnual.split("-");
		String[] enAn=endAnnual.split("-");
		
		Calendar stCal=Calendar.getInstance();
		Calendar enCal=Calendar.getInstance();
		
		stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
		enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
		
		String reStCal=String.format("%tF", stCal);
		String reEnCal=String.format("%tF", enCal);
		
		System.out.println(reStCal);
		System.out.println(reEnCal);
		
		
		
		if((!reStCal.equals(startAnnual)) || (!reEnCal.equals(endAnnual))) {
			System.out.println("틀림");
		}else {
			System.out.println("맞음");
		}
		System.out.println("-----------");
		
		long totalDate=(enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24);
		
		
		System.out.println(enCal.getTimeInMillis()/1000/60/60/24);
		System.out.println(stCal.getTimeInMillis()/1000/60/60/24);
		
		System.out.println(totalDate);
		
		if(totalDate < 0) {
			System.out.println("잘못 입력하셨습니다");
//			MemberView.annualMenu1();
		}else {
			System.out.println("맞음");
		}
		
		System.out.println("-----------");
		
		
		startAnnual="2023-12-09";
		endAnnual="2023-12-08";
		
		System.out.println(annualCheck(startAnnual, endAnnual));
		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("--------------------------------------------------");
//		boolean loop=true;
//		
//		while(loop){
//			System.out.print("신청하시겠습니까? (Y/N): ");
//			String input=scan.nextLine();
//			if(input.toLowerCase().equals("y")) {
//				break;
//			}else if(input.toLowerCase().equals("n")) {
//				break;
//				
//			}else {
//				System.out.println("Y 또는 N 입력 바랍니다");
//			}
//			
//		}
		
		startAnnual="2023-08-18";
		endAnnual="2023-09-01";
		
		
		
		stAn=startAnnual.split("-");
		enAn=endAnnual.split("-");

		
		
		stCal=Calendar.getInstance();
		enCal=Calendar.getInstance();
		
		stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
		enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
		
		int anDate=(int)((enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24)+1);
		
		System.out.println("anDate: "+anDate);
		
		System.out.println(
				anDate);
		
		System.out.println(stCal.get(Calendar.DAY_OF_WEEK));
		stCal.get(Calendar.DAY_OF_WEEK);
		
		System.out.println("--------------------");
		int count=0;
		for(int i=0; i<anDate; i++) {
			
			stCal.add(Calendar.DAY_OF_WEEK, i);
			
			System.out.println(stCal.get(Calendar.DAY_OF_WEEK)+"count: "+count );
			
			if(stCal.get(Calendar.DAY_OF_WEEK) == 1 || stCal.get(Calendar.DAY_OF_WEEK) == 7) {
				count++;
			}
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
			
		}
		System.out.println("--------------------");
		System.out.println(count);
		
//		System.out.println();
//		stCal.add(Calendar.DAY_OF_WEEK, 1);
//		System.out.println(stCal.get(Calendar.DAY_OF_WEEK));
		
		
//		stCal.add(Calendar.DAY_OF_WEEK, 11);
//		System.out.println(stCal.get(Calendar.DAY_OF_WEEK));
		
		anDate=anDate-count;
		
		System.out.println(anDate);
		
		String result=String.format("%10s\t%10s\t%5s\t\t%4s\n"
						,"2023-04-08"
						,"2023-04-08"
						,"1"
						,"병원");
		result+=String.format("%5s\t\t%5s\t%5s\t\t%5s\n"
				,"2023-04-08"
				,"2023-04-08"
				,"1"
				,"병원");
		
		System.out.println("  [시작일]\t  [종료일]\t[사용 일수]\t[신청사유]");
		System.out.println(result);
		
		
		
		
		
		
		
		
		
		
	}//m
	
	
	public static boolean annualCheck(String startAnnual, String endAnnual) {
		
		boolean result=true;
		
		boolean loop=true;
		
		
		do { //do while 순서대로 에러가 하나라도 나오면 break로 멈추는 기능 ex) 문자가 들어갈경우 밑에까지 다실행해야되서 calendar에서 에러나옴 
			
			//-------------------------------------------------------------------입력 날짜가 '-' 두개가 있는지 확인
			int countS=0;
			for(int i=0; i<startAnnual.length(); i++) { 
				if(startAnnual.charAt(i) == '-') {
					countS++;
				}
			}
			int countE=0;
			for(int i=0; i<endAnnual.length(); i++) { 
				if(endAnnual.charAt(i) == '-') {
					countE++;
				}
			}
			if(countS != 2 || countE != 2) { 
				result=false;
			}
			
			if(result == false) {
				break;
			}
			
			//--------------------------------------------------------------------입력 날짜가 다 숫자인지 확인
			
			String reAn=startAnnual.replace("-", "")+endAnnual.replace("-", "");
			
			for(int i=0; i<reAn.length(); i++) {
				if(reAn.charAt(i) < '0' || '9' < reAn.charAt(i)){
					result=false;
					break;
				}
			}
			
			if(result == false) {
				break;
			}
			
			//-------------------------------------------------------------------- 4-2-2 입력이 맞는지 확인
			
			String[] stAn=startAnnual.split("-");
			String[] enAn=endAnnual.split("-");
			
			
			
			if(stAn[0].length() != 4 || stAn[1].length() != 2 || stAn[2].length() != 2) {
				result=false;
			}
			
			if(result == false) {
				break;
			}
			
			//-------------------------------------------------------------------- 존재하는 날짜인지 확인
			//Calendar에 2022-18-88 값넣어도 다른 날짜가 출력되지 오류가 나오진 않음, 그 둘을 비교
			
			Calendar stCal=Calendar.getInstance();
			Calendar enCal=Calendar.getInstance();
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
			enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
			
			String reStCal=String.format("%tF", stCal);
			String reEnCal=String.format("%tF", enCal);
			
			if((!reStCal.equals(startAnnual)) || (!reEnCal.equals(endAnnual))) {
				result=false;
			}
			
			if(result == false) {
				break;
			}
			
			//-------------------------------------------------------------------- 시작일 끝나는일 위치가 반대였을 경우 체크
			
			long totalDate=(enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24);
			
			if(totalDate < 0) {
				result=false;
			}
			
			if(result == false) {
				break;
			}
			
			loop=false; //루프 한번돌고 끝남
			
		}while(loop);
		
		
		
		return result;
	}
	
}
