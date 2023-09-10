//package com.project.erpsystem.dao;
package com.project.erpsystem.admin.view;
import java.util.Scanner;

import com.project.erpsystem.admin.view.AdminView;
import com.project.erpsystem.dao.PersonDepartmentDao;
import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;

public class PersonDepartmentView {//인사관리정보 관련 뷰가 들어있는 클래스
	
	
	public static void PersonDepartmentChView() {//인사관리정보뷰
		
		Scanner scan = new Scanner(System.in);

//		PersonDao.load();
		
		while(true) {
			System.out.println("[인사관리정보]");
			System.out.println("1. 신규입사자 등록");
			System.out.println("2. 재직자 정보 수정");
			System.out.println("3. 인사발령 등록");
			System.out.println("4. 메인화면");
			System.out.print("번호를 입력하세요: ");
			String select = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			
			if(select.equals("1")) {
				newEmployeeView();
				
			} else if(select.equals("2")) {
				changeEmployeeView();
				
			} else if(select.equals("3")) {
				changeDepartmentHierarchyView();
				
			} else if(select.equals("4")) {
				return; //이전으로
				
			} else {
				System.out.println("잘못입력했습니다.");
//				System.out.println("================================================================================");
				System.out.println("");
			}
			
		}
		
		
	}
	

	public static void changeDepartmentHierarchyView() {//인사발령등록뷰
		
		
		Scanner scan = new Scanner(System.in);
		
		
		//System.out.println("------------------------");
		System.out.println("[인사발령 등록]");
		System.out.print("인사발령할 직원의 사번을 입력하세요: ");
		String id = scan.nextLine();
		System.out.println("--------------------------------------------------------------------------------");
		
		PersonVo p=new PersonVo();
		
		for(PersonVo ps : PersonDao.list) {
			if(ps.getId().equals(id)) {
				p=ps;
				break;
			}
		}
		
		
		
		while(true) {
			
			if (p.getId() != null) {
				System.out.printf("[%s %s %s님 %s호봉]\n",p.getDepartment(),p.getName(),p.getPosition(), p.getHobong());
				System.out.println("1. 호봉승급");
				System.out.println("2. 승진");
				System.out.println("3. 부서이동");
				System.out.println("4. 이전화면");
				System.out.print("번호를 입력하세요: ");
				String input=scan.nextLine();
				
				System.out.println("--------------------------------------------------------------------------------");
				if(PersonDepartmentDao.changeDepartmentHierarchy(input, p)) {
					return;
				}
				
			}else {
				System.out.println("일치하는 직원 번호가 없습니다.");
				System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
//				Scanner scan = new Scanner(System.in);
				scan.nextLine();
				return;
			}
			
		
		
		}
			

		
	}





	
	private static void changeEmployeeView() {//재직자정보수정메소드
		
		Scanner scan = new Scanner(System.in);

//		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("[재직자 정보 수정]");
		System.out.print("수정할 직원의 사번을 입력하세요: ");
		String id = scan.nextLine();
		System.out.println("--------------------------------------------------------------------------------");
		
		PersonVo p=new PersonVo();
		
		for(PersonVo ps : PersonDao.list) {
			if(ps.getId().equals(id)) {
				p=ps;
				break;
			}
		}//for
		
		
		
		while(true) {
			if (p.getId() != null) {
				System.out.printf("[%s %s %s님]\n", p.getDepartment(), p.getName(), p.getPosition());
				System.out.println("================================================================================");
				System.out.printf("1. 생년월일:%s\n",p.getBirthDay());
				System.out.printf("2. 전화번호:%s\n",p.getTel());
				System.out.printf("3. 주소:%s\n",p.getAddr());
				System.out.printf("4. 입사일:%s\n",p.getBeginDate());
				System.out.printf("5. 면허/자격:%s\n",p.getLicence());
				System.out.printf("6. 학력/경력:%s\n", p.getSchool());
				System.out.println("================================================================================");
				System.out.println("7. 이전 화면");
				
				System.out.print("수정할 항목의 번호를 입력하세요:");
				String modifyNumber = scan.nextLine();

				System.out.println("--------------------------------------------------------------------------------");
				
				if(PersonDepartmentDao.changeEmployee(modifyNumber,p)) {
					return;
				}
				
			}else {
				System.out.println("일치하는 직원 번호가 없습니다.");
				return;
			}
			
			
		}
	}
	
	public static void newEmployeeView() {
		
		
//		while(true) {
			
			//System.out.println("------------------------");
			System.out.println("[신규입사자 등록]");
			
			Scanner scan = new Scanner(System.in);
			
			PersonVo newEmployee = new PersonVo();
			
			System.out.println();
			
//			System.out.println("양식: 한글(예시:강감찬)");
			System.out.print("1. 이름:");
			String name = scan.nextLine();
			newEmployee.setName(name);
//			System.out.println("양식: xxxx-xx-xx");
			System.out.print("2. 생년월일(xxxx-xx-xx):");
			String birthDay = scan.nextLine();
			newEmployee.setBirthDay(birthDay);
//			System.out.println("양식: 010-xxxx-xxxx");
			System.out.print("3. 전화번호(010-xxxx-xxx):");
			String tel = scan.nextLine();
			newEmployee.setTel(tel);
			
//			System.out.println("양식: xx시 xx구 xx동");
			System.out.print("4. 주소(xx시 xx구 xx동):");
			String addr = scan.nextLine();
			newEmployee.setAddr(addr);
//			System.out.println("양식: xxxx-xx-xx");
			System.out.print("5. 입사일(xxxx-xx-xx):");
			String beginDate = scan.nextLine();
			newEmployee.setBeginDate(beginDate);
			System.out.println("목록: 인사팀, 구매팀, 기획팀, 영업팀, 개발팀, 재무팀");
			System.out.print("6. 부서:");
			String department = scan.nextLine();
			newEmployee.setDepartment(department);
			System.out.println("목록: 사원, 주임, 대리, 과장, 차장, 부장");
			System.out.print("7. 직급:");
			String position = scan.nextLine();
			newEmployee.setPosition(position);
//			System.out.println("목록: 숫자 1~4");
			System.out.print("8. 호봉(1~4):");
			String hobong = scan.nextLine();
			newEmployee.setHobong(hobong);
			System.out.print("9. 면허/자격:");
			String licence = scan.nextLine();
			newEmployee.setLicence(licence);
			System.out.print("10. 학력/경력:");
			String school = scan.nextLine();
			newEmployee.setSchool(school);
			
			
			newEmployee.setRating("0");
			
			if(PersonDepartmentDao.newEmployee(newEmployee)) {
				return;
			}
			
			
//		}
		
		
	}
	
//	public static void changeDepartmentHierarchyMenuView() {
//		System.out.println("1. 호봉승급");
//		System.out.println("2. 승진");
//		System.out.println("3. 부서이동");
//		System.out.println("4. 이전화면");
//		System.out.print("번호를 입력하세요:");
//	}
	


	

		
	

}