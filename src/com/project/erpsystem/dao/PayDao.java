package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import com.project.erpsystem.admin.view.AdminPayView;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.member.view.MemberPayView;
import com.project.erpsystem.vo.AttendanceVo;
import com.project.erpsystem.vo.DeducationVo;
import com.project.erpsystem.vo.HobongVo;
import com.project.erpsystem.vo.PayVo;
import com.project.erpsystem.vo.SalaryStepModifyVo;


public class PayDao {
	
	public static ArrayList<PayVo> payList;
	
	static {
		PayDao.payList = new ArrayList<PayVo>();
	}
	
	
	//급여명세서 파일 > ArrayList로 저장
	public static boolean load(String year, String month) {
		
		PayDao.payList.clear();
		
		String path = String.format("data\\PayStub%s.txt", month);
		
		
		File file = new File(path);
		//해당 월의 급여파일이 없으면 load를 멈춘다.
		if(!file.exists()) {
			return false;
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			while((line=reader.readLine())!=null) {
				
				String[] temp = line.split(",");
				PayVo pay = new PayVo(temp[0], temp[1], temp[2]
						, temp[3]
						, Integer.parseInt(temp[4])
						, Integer.parseInt(temp[5])
						, Integer.parseInt(temp[6])
						, Integer.parseInt(temp[7])
						, Integer.parseInt(temp[8])
						, Integer.parseInt(temp[9])
						, Integer.parseInt(temp[10])
						, Integer.parseInt(temp[11])
						, Integer.parseInt(temp[12])
						, Integer.parseInt(temp[13])
						, Integer.parseInt(temp[14])
						, Integer.parseInt(temp[15])
						, Integer.parseInt(temp[16]));
				PayDao.payList.add(pay);
			}
			reader.close();
			
			return true;
			
			
		} catch (Exception e) {
			System.out.println("at PayDao.loadList");
			e.printStackTrace();
		}
		
		return false;
		
	}//listLoad
	
	//급여명세서 조회
	public static void listShow(String month, String id) {
		
		for(PayVo pay : PayDao.payList) {
			if (pay.getId().equals(id)) {
//				frag = true;
//				String month = pay.getPaymentDate().split("-")[1];
				System.out.println(pay.getPaymentDate());
				System.out.println("============================================================================================================");
				System.out.printf("[%s님의 %s월 명세서]\n", pay.getName(), month);
				System.out.println("지급내역(단위:원)");
				System.out.println("============================================================================================================");
				System.out.println("[기본급]\t[직책수당]\t[연장근로수당]\t[휴일근로수당]");
				System.out.printf("%,d\t\t%,d\t\t%,d\t\t%,d\n"
									, pay.getBasicPay()
									, pay.getPositionPay()
									, pay.getOvertimePay()
									, pay.getHolidayPay());
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.printf("[국민연금]\t[건강보험]\t[고용보험]\t[장기요양보험]\t[소득세]\t[지방소득세]\n");
				System.out.printf("%,d\t\t%,d\t\t%,d\t\t%,d\t\t%,d\t\t%,d\t\t\n"
									, pay.getNationalPension()
									, pay.getHealthlnsurance()
									, pay.getEmploymentInsurance()
									, pay.getLongTermCareInsuracne()
									, pay.getIncomeTax()
									, pay.getLocalIncomeTax());
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.println("[지급총액]\t[공제총액]\t[실지급액]");
				System.out.printf("%,d\t%,d\t\t%,d\n"
									,pay.getTotalPay()
									,pay.getTotalDeducations()
									,pay.getNetPay());
				System.out.println("============================================================================================================");
				
			} 
		}
		
	}//listShow
	
	
	//급여명세서 작성
	public static void listAdd(String month) {
		
		String path = String.format("data\\PayStub%s.txt", month);
		
		
		File file = new File(path);
		//해당 월의 급여파일이 있으면 add를 멈춘다.
		if(file.exists()) {
			System.out.println("이미 작성되어 있습니다.");
			return;
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			AttendanceDao.load("2023", month);
			PersonDao.load();
			
			String tempId="";
			for(AttendanceVo attendance : AttendanceDao.list) {
				//사번, 이름, 부서, 지급일, 기본급, 직책수당, 연장근로수당 휴일근로수당 국민연금 건강보험 고용보험 장기요양보험 소득세 지방소득세 지급총액 공제총액 실수령액
				
				String userId = attendance.getId();
				String position="", hobong="";
				int basicPay=0, positionPay=0, totalPay=0, totalDeducations=0, netPay=0;
				
				if (tempId.equals(userId)) {
					continue;
				}
				
				writer.write(attendance.getId() + ",");							//사번
				
				for(PersonVo person : PersonDao.list) {
					if (person.getId().equals(attendance.getId())) {
						writer.write(person.getName() + ",");					//이름
						writer.write(person.getDepartment() + ",");				//부서
						position = person.getPosition();
						hobong = person.getHobong();
						break;
					}
				}
				
				//Calendar c1 = Calendar.getInstance();
				writer.write("2023-" + month + "-10,");								//지급일
				
				for (SalaryStepModifyVo sv : SalaryStepModifyDao.list) {
					if (sv.getPosition().equals(position) && sv.getHobong().equals(hobong)) {
						basicPay = Integer.parseInt(sv.getBasicPay());
						positionPay = Integer.parseInt(sv.getPositionPay());
						
						writer.write(basicPay + ",");							//기본급
						writer.write(positionPay + ",");						//직책수당
						break;
					}
				}
				
				
				
				/*
				for (HobongVo hobongTable : HobongDao.hobongList) {
					if (hobongTable.getPosition().equals(position) && hobongTable.getHobong().equals(hobong)) {
						basicPay = hobongTable.getBasicPay();
						positionPay = hobongTable.getPositionPay();
						
						writer.write(basicPay + ",");							//기본급
						writer.write(positionPay + ",");						//직책수당
						break;
					}
					
				}
				*/
				
				int hour=0, minute=0;
				for(AttendanceVo attendanceTime : AttendanceDao.list) {
					if (attendanceTime.getId().equals(userId)) {
						String[] time = attendanceTime.getOvertime().split(":");
						hour += Integer.parseInt(time[0]);
						minute += Integer.parseInt(time[1]);
						
					}
				}
				hour = hour + (minute/60);
				int overtimePay = (int)(hour * 1.5 * 12440);
				writer.write(overtimePay + ",");						//연장근로시간
				
				writer.write(0 + ",");										//휴일근로시간
				
				//공제항목(국민연금 건강보험 고용보험 장기요양보험 소득세 지방소득세)
				for (DeducationVo deducation : DeducationDao.list) {
//					if (deducation.isToUse()) {
//						if (deducation.getDeducation().equals("))
//					}
					if (deducation.getSubscriberRate() <=0) {
						continue;
					}
					writer.write((int)((basicPay + positionPay)*deducation.getSubscriberRate()) + ",");
					totalDeducations += (basicPay + positionPay)*deducation.getSubscriberRate();
					
				}
				totalPay = basicPay + positionPay + overtimePay;
				writer.write(totalPay + ",");
				writer.write(totalDeducations+ ",");
				
				netPay = totalPay - totalDeducations;
				writer.write(netPay+"\r\n");
				
				tempId = userId;
				
			}
			
			writer.close();
			System.out.println("급여명세서 작성 되었습니다.");
			System.out.println("================================================================================");
			//AdminPayView.pause();
			
		} catch (Exception e) {
			System.out.println("at PayDao.listAdd");
			e.printStackTrace();
		}
		
		
	}

	public static boolean isExistence(String id, String month) {
		
		
		String path = String.format("data\\PayStub%s.txt", month);
		
		File file = new File(path);
		//해당 월의 급여파일이 있으면 add를 멈춘다.
		if(!file.exists()) {
			return false;
		}
		
		PayDao.load("year", month);
		
		for (PayVo pay : PayDao.payList) {
			if (pay.getId().equals(id)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
