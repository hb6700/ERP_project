package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.erpsystem.admin.view.DeducationView;
import com.project.erpsystem.vo.AttendanceVo;
import com.project.erpsystem.vo.DeducationVo;

public class DeducationDao {

	public static ArrayList<DeducationVo> list;
	
	static {
		DeducationDao.list = new ArrayList<DeducationVo>();
	}
	
	
	public static void load() {
		String path = "data\\deducation.txt";
		
		File file = new File(path);
		//파일이 없으면 load를 멈춘다.
		if(!file.exists()) {
			return;
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			while((line=reader.readLine())!=null) {
				
				String[] temp = line.split(",");
				DeducationVo deducation = new DeducationVo(Integer.parseInt(temp[0])
													,temp[1]
													,Float.parseFloat(temp[2])
													,Float.parseFloat(temp[3])
													,Boolean.parseBoolean(temp[4]));
				DeducationDao.list.add(deducation);
			}
			
			reader.close();
			
			
		} catch (Exception e) {
			System.out.println("at DeducationDao.load");
			e.printStackTrace();
		}
	}//load
	
	
	public static void listShow() {
		
		System.out.printf("%3s%10s\t\t%s\n", "[번호]", "[항목]", "[사용여부]");
		for (DeducationVo deducation : DeducationDao.list) {
			System.out.printf("%3d%10s\t\t%b\n", deducation.getNum(), deducation.getDeducation(), deducation.isToUse());
		}
		
	}
	
	
	//사용여부 변경
	public static boolean toUseChange(int num) {
		
		boolean frag = false;
		for (DeducationVo deducation : DeducationDao.list) {
			if (deducation.getNum() == num) {
				deducation.setToUse(!deducation.isToUse());;
				frag = true;
				break;
			}
		}
		
		if (!frag) {
			System.out.println("유효한 번호를 입력해주세요");
			return false;
		}
		System.out.println("변경되었습니다.\n");
		return true;
		
	}
	
	//항목 추가
	public static boolean itemAdd(String item, double subscriberRate, double companyRate) {
		
		if (subscriberRate <= 0 || subscriberRate > 100 || companyRate <= 0 || companyRate > 100) {
			System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.");
			//System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
			//Scanner scan = new Scanner(System.in);
			//scan.nextLine();
			return false;
		}
		
		//일련번호 > 모든 일련번호 중 가장 큰 숫자를 확인
		Integer maxNo = DeducationDao.list.stream()
				.map(s -> s.getNum())
				.max((a,b) -> a- b)
				.get();
		
		int no = maxNo +1;
		
		DeducationVo d = new DeducationVo(no, item, subscriberRate*0.01, companyRate*0.01, true);
		DeducationDao.list.add(d);	//새로운 항목 추가
		
		System.out.println("추가되었습니다.\n");
		return true;
		
		
	}
	
	//항목삭제
	public static boolean delete(int num) {
		boolean frag = false;
		
		for (DeducationVo d : DeducationDao.list) {
			if (d.getNum() == num) {
				frag = true;
				DeducationDao.list.remove(d);
				break;
			}
		}
		
		if (!frag) {
			System.out.println("유효한 번호를 입력해주세요 ");
			return false;
		}
		System.out.println("삭제했습니다.\n");
		return true;
		//DeducationView.pauseDe();
	}


	public static boolean subscriberRate(String num) {
		
		for (DeducationVo d : DeducationDao.list) {
			if (d.getNum() == Integer.parseInt(num)) {
				System.out.printf("현재 가입자부담율(단위:%%) : %.2f\n", d.getSubscriberRate()*100);
				
				System.out.print("수정할 가입자부담율(단위:%) :");
				
				Scanner scan = new Scanner(System.in);
				
				double rate = scan.nextDouble();
				System.out.println("--------------------------------------------------------------------------------");
				
				if (rate <= 0 || rate > 100) {
					System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.");
					return false;
				}
				
				d.setSubscriberRate(rate/100);
				
				break;
			}
		}
		
		System.out.println("수정되었습니다.\n");
		return true;
		
	}


	public static boolean companyRate(String num) {
		
		for (DeducationVo d : DeducationDao.list) {
			if (d.getNum() == Integer.parseInt(num)) {
				System.out.printf("현재 사업주부담율(단위:%%) : %.2f\n", d.getCompanyRate()*100);
				
				System.out.print("수정할 사업주부담율(단위:%) :");
				
				Scanner scan = new Scanner(System.in);
				
				double rate = scan.nextDouble();
				System.out.println("--------------------------------------------------------------------------------");
				if (rate <= 0 || rate > 100) {
					System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.");
					return false;
				}
				
				d.setCompanyRate(rate/100);
				
				break;
			}
		}
		
		System.out.println("수정되었습니다.\n");
		return true;
		
	} 
	
	public static void save() {
		try {
			
			String path = "data\\Deducation.txt";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (DeducationVo d : DeducationDao.list) {
				
				writer.write(String.format("%d,%s,%f,%f,%b\n"
											,d.getNum()
											,d.getDeducation()
											,d.getSubscriberRate()
											,d.getCompanyRate()
											,d.isToUse()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
