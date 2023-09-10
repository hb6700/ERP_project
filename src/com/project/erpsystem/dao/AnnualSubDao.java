package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.vo.AnnualSubVo;
import com.project.erpsystem.vo.AnnualVo;

public class AnnualSubDao {

	public static ArrayList<AnnualSubVo> list;
	
	static {
		list=new ArrayList<AnnualSubVo>();
	}
	
	
	public static void load() {
		
		try {
			String dir="data\\AnnualLeaveUsage.txt";
			
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				
				list.add(new AnnualSubVo(temp[0],temp[1],temp[2],Integer.parseInt(temp[3]),temp[4]));
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void save() {
		
		try {
			String dir="data\\AnnualLeaveUsage.txt";
			
			BufferedWriter writer=new BufferedWriter(new FileWriter(dir));
			
			for(AnnualSubVo av : list) {
				String result=String.format("%s,%s,%s,%d,%s\r\n"
										,av.getId()
										,av.getStartAnnual()
										,av.getEndAnnal()
										,av.getDateAnnual()
										,av.getReason());
				writer.write(result);
			}
			
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public static int annualSubApply(String startAnnual, String endAnnual, String reason) {
		
		int result=0; // -1 잔여 연차 없음 / 1 연차 저장 성공 / 0 주말만 씀
		
		String[] stAn=startAnnual.split("-");
		String[] enAn=endAnnual.split("-");

//		int anDate=Integer.parseInt(enAn[2])-Integer.parseInt(stAn[2])+1; //휴가 쓰는 날짜 (토일 제외x)
		
		Calendar stCal=Calendar.getInstance();
		Calendar enCal=Calendar.getInstance();
		
		stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
		enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
		
		int anDate=(int)((enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24)+1);
		
		
		int count=0; //토 일 해당시 count 증가
		for(int i=0; i<anDate; i++) { //토 일 제외시키는 루프
			
			stCal.add(Calendar.DAY_OF_WEEK, i);
			
			if(stCal.get(Calendar.DAY_OF_WEEK) == 1 || stCal.get(Calendar.DAY_OF_WEEK) == 7) {
				count++;
			}
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
		}
		
		int totalDate=anDate-count; //토 일 뺀 총 휴가일수 결과
		
		if(totalDate == 0) { //쓴 연차수가 0일일때 ex)주말 2일
			result=0;
		}else {
			for(AnnualVo an : AnnualDao.list) {
				if(an.getId().equals(UserDao.auth.getId())) {
					if(an.getLeftoverAnnual()-totalDate < 0) { //신청 일수가 - 일경우 -1출력
						result=-1;
					}else {
						an.setLeftoverAnnual(an.getLeftoverAnnual()-totalDate);
						an.setUsedAnnal(an.getUsedAnnal()+totalDate);
						list.add(new AnnualSubVo(UserDao.auth.getId(), startAnnual, endAnnual, totalDate, reason));
						result=1;
					}
				}
			}
		}
		
		
		
		
		
		return result;
		
		
		
	}


	public static boolean annualCheck(String startAnnual, String endAnnual) {
		
		boolean result=true;
		
//		boolean loop=true;
		
		//------------------------------------------------------------------- 유효성검사
		String regex="^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(startAnnual);
		
		if(!m.find()) {
			result=false;
		}
		
		m=p.matcher(endAnnual);
		
		if(!m.find()) {
			result=false;
		}
		
		//-------------------------------------------------------------------존재하는 날짜인지 확인
		//Calendar에 2022-18-88 값넣어도 다른 날짜가 출력되지 오류가 나오진 않음, 그 둘을 비교
		
		if(result) {
			String[] stAn=startAnnual.split("-");
			String[] enAn=endAnnual.split("-");
			
			Calendar stCal=Calendar.getInstance();
			Calendar enCal=Calendar.getInstance();
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
			enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
			
			String reStCal=String.format("%tF", stCal);
			String reEnCal=String.format("%tF", enCal);
			
			if((!reStCal.equals(startAnnual)) || (!reEnCal.equals(endAnnual))) {
				result=false;
			}
			
			
			//-------------------------------------------------------------------시작일 끝나는일 위치가 반대였을 경우 체크
			
			long totalDate=(enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24);
			
			if(totalDate < 0) {
				result=false;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
//		do { //do while 순서대로 에러가 하나라도 나오면 break로 멈추는 기능 ex) 문자가 들어갈경우 밑에까지 다실행해야되서 calendar에서 에러나옴 
//			
//			//-------------------------------------------------------------------입력 날짜가 '-' 두개가 있는지 확인
//			int countS=0;
//			for(int i=0; i<startAnnual.length(); i++) { 
//				if(startAnnual.charAt(i) == '-') {
//					countS++;
//				}
//			}
//			int countE=0;
//			for(int i=0; i<endAnnual.length(); i++) { 
//				if(endAnnual.charAt(i) == '-') {
//					countE++;
//				}
//			}
//			if(countS != 2 || countE != 2) { 
//				result=false;
//			}
//			
//			if(result == false) { // false 일경우 while문 멈추기
//				break;
//			}
//			
//			//--------------------------------------------------------------------입력 날짜가 다 숫자인지 확인
//			
//			String reAn=startAnnual.replace("-", "")+endAnnual.replace("-", "");
//			
//			for(int i=0; i<reAn.length(); i++) {
//				if(reAn.charAt(i) < '0' || '9' < reAn.charAt(i)){
//					result=false;
//					break;
//				}
//			}
//			
//			if(result == false) {
//				break;
//			}
//			
//			//-------------------------------------------------------------------- 4-2-2 입력이 맞는지 확인
//			
//			String[] stAn=startAnnual.split("-");
//			String[] enAn=endAnnual.split("-");
//			
//			
//			
//			if(stAn[0].length() != 4 || stAn[1].length() != 2 || stAn[2].length() != 2) {
//				result=false;
//			}
//			
//			if(result == false) {
//				break;
//			}
//			
//			//-------------------------------------------------------------------- 존재하는 날짜인지 확인
//			//Calendar에 2022-18-88 값넣어도 다른 날짜가 출력되지 오류가 나오진 않음, 그 둘을 비교
//			
//			Calendar stCal=Calendar.getInstance();
//			Calendar enCal=Calendar.getInstance();
//			
//			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
//			enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1])-1, Integer.parseInt(enAn[2]));
//			
//			String reStCal=String.format("%tF", stCal);
//			String reEnCal=String.format("%tF", enCal);
//			
//			if((!reStCal.equals(startAnnual)) || (!reEnCal.equals(endAnnual))) {
//				result=false;
//			}
//			
//			if(result == false) {
//				break;
//			}
//			
//			//-------------------------------------------------------------------- 시작일 끝나는일 위치가 반대였을 경우 체크
//			
//			long totalDate=(enCal.getTimeInMillis()/1000/60/60/24)-(stCal.getTimeInMillis()/1000/60/60/24);
//			
//			if(totalDate < 0) {
//				result=false;
//			}
//			
//			if(result == false) {
//				break;
//			}
//			
//			loop=false; //루프 한번돌고 끝남
//			
//		}while(loop);
		
		
		
		return result;

	}


	public static String checkAnnualApply() {
		String result="";
		
		for(AnnualSubVo ans : list) {
			if(ans.getId().equals(UserDao.auth.getId())) {
				result+=String.format("%10s\t%10s\t%5s\t\t%4s\n"
										,ans.getStartAnnual()
										,ans.getEndAnnal()
										,ans.getDateAnnual()
										,ans.getReason());
			}
		}
		
		return result;
	}


	
	
	

}
