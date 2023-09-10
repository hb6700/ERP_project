package com.project.erpsystem.dao;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;
import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.main.UserVo;

public class PersonDepartmentDao {
	
	public static boolean changeDepartmentHierarchy(String input, PersonVo p) {//인사발령등록
		
		Scanner scan=new Scanner(System.in);
		
		String[] position= {"사원", "주임", "대리", "과장", "차장", "부장"};
		String[] department= {"인사팀", "구매팀", "기획팀", "영업팀", "개발팀", "재무팀"};
		
		
		if(input.equals("1")) {
			int hobong=Integer.parseInt(p.getHobong());
			if(hobong < 4) {
				p.setHobong(String.format("%d", hobong+1));
				System.out.println("호봉이 수정 되었습니다");
			}else {
				System.out.println("호봉을 더 이상 올릴 수 없습니다\n");
				return false;
			}
			
		}else if(input.equals("2")) {
			if(p.getPosition().equals(position[5])) {
				System.out.println("직급을 더 이상 올릴 수 없습니다\n");
				return false;
			}else {
				for(int i=0; i<position.length; i++) {
					if(position[i].equals(p.getPosition())) {
						p.setPosition(position[i+1]);
						p.setHobong("1");
						break;
					}
				}
				System.out.println("직급이 수정 되었습니다\n");
			}
		}else if(input.equals("3")) {
			System.out.println("변경될 부서를 입력해주세요");
			System.out.println("[인사팀, 구매팀, 기획팀, 영업팀, 개발팀, 재무팀]");
			System.out.print("목록 중에서 입력해주세요: ");
			String dpInput=scan.nextLine();
			
			
			if (dpInput.equals(p.getDepartment())) {
				System.out.println("현재와 다른 부서를 입력해주세요.\n");
				return false;
			}

			boolean frag = false;
			for (String dp : department) {
				if (dp.equals(dpInput)) {// 입력한 팀이 String 배열 department에 있을 경우 && 입력한 팀이 원래 팀이 아닐 경우
					p.setDepartment(dpInput);
					System.out.println("부서가 변경 되었습니다.\n");
					frag = true;
					return true;
				}
			}

			if (!frag) {
				System.out.println("[인사팀, 구매팀, 기획팀, 영업팀, 개발팀, 재무팀]중에서 입력해주세요.\n");
				return false;
			}
	          
	       }else if(input.equals("4")) {
	          return true;
	       }
	       
	       return true;
		
//	
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		while(true) {
//			for(PersonVo p : PersonDao.list) {
//				if(p.getId().equals(id)) {
//					System.out.printf("%s %s %s님\n",p.getDepartment(),p.getName(),p.getPosition());
//					PersonDepartmentView.changeDepartmentHierarchyMenuView();//1.호봉~4이전화면
//					String number = scan.nextLine();
//					//System.out.println(p.getId()+" "+p.getHobong());//2222
//					
//					if(number.equals("1")) {//인사관리정보if
//						System.out.print("호봉변동:" + p.getHobong() + "->");
//						String changedhobong = scan.nextLine();
//						
//						System.out.println(changedhobong);//2222222
//						p.setHobong(changedhobong);
//						
//		//				if(p.getHobong().equals("4")) {//호봉맥스체크if
//		//					System.out.printf("%s %s %s님 호봉을 올릴 수 없습니다.\n", p.getDepartment(),p.getName(),p.getPosition());
//		//					}
//		//				}//호봉맥스체크if
//						
//						System.out.printf("%s %s %s님 호봉 수정되었습니다.", p.getDepartment(), p.getName(), p.getPosition());
//						System.out.println("계속하시려면 엔터");
//						pause();
//						changeDepartmentHierarchy(id);
//						
//					} else if(number.equals("2")) {
//						
//						System.out.print("직급 변동:" + p.getPosition() + "->");
//						String changedPosition = scan.nextLine();
//						
//						p.setPosition(changedPosition);
//						System.out.printf("%s %s %s님 직급 수정되었습니다.",p.getDepartment(), p.getName(), p.getPosition());
//						System.out.println("계속하시려면 엔터");
//						pause();
//						changeDepartmentHierarchy(id);
//					} else if(number.equals("3")) {
//						
//						System.out.println("1.인사팀 2.재무팀 3.개발팀 4.기획팀 5.영업팀 6.구매팀");
//						System.out.println("현재 부서:" + p.getDepartment());
//						System.out.print("변경할 부서 입력하세요:");
//						String changedDepartment = scan.nextLine();
//						
//						System.out.println("--------------------------------");
//						System.out.println("부서 변동:" + p.getDepartment() + "->" + changedDepartment);
//						p.setDepartment(changedDepartment);
//						System.out.println("계속하시려면 엔터");
//						pause();
//						changeDepartmentHierarchy(id);
//					} else if(number.equals("4")) {
//						System.out.println("--------------------------------");
//						PersonDepartmentView.PersonDepartmentChView();;
//					}
//				
//				
//				 }//if 
//			
//				}//for
//		}
//		
//		System.out.println();
//		System.out.println("올바르지 않은 사번입니다. 사원 목록은 위와 같습니다.");
//		System.out.println("인사관리정보화면으로 돌아갑니다.");
//		System.out.println("------------------------");
//		PersonDepartmentView.PersonDepartmentChView();
		
    }//changeDepartmentHierarchyDao


	
	public static boolean changeEmployee(String modifyNumber,PersonVo p) {//재직자정보수정메소드
		
		Scanner scan=new Scanner(System.in);
		
		String modify="";
	
		if(modifyNumber.equals("1")){
			System.out.printf("1. 생년월일: ");
			modify = scan.nextLine();
			if(regexs("^[0-9]{4}-[0-9]{2}-[0-9]{2}"
						,modify
						,"올바른 생일을 입력해주세요.")) {
				//수정한 인사관리내용이 양식에 올바를 경우
				p.setBirthDay(modify);
			}else {
				return false;
			}

		} else if(modifyNumber.equals("2")) {
			System.out.printf("2. 전화번호: ");
			modify = scan.nextLine();
			if(regexs("^010-[0-9]{3,4}-[0-9]{4}$"
					,modify
					,"올바른 전화번호를 입력해주세요.")) {
				//수정한 인사관리내용이 양식에 올바를 경우
				p.setTel(modify);
			} else {
				return false;
			}
			
		} else if(modifyNumber.equals("3")) {
			System.out.printf("3. 주소: ");
			modify = scan.nextLine();
			if(regexs("^[가-힣]{1,5}시\\s[가-힣]{1,5}\\s[가-힣]{1,5}$"
					,modify
					,"올바른 주소를 입력해주세요.")) {
				//수정한 인사관리내용이 양식에 올바를 경우
				p.setAddr(modify);
			} else {
				return false;
			}
			
		} else if(modifyNumber.equals("4")) {
			System.out.printf("4. 입사일: ");
			modify = scan.nextLine();
			if(regexs("^[0-9]{4}-[0-9]{2}-[0-9]{2}"
					,modify
					,"올바른 날짜를 입력해주세요.")) {
				//수정한 인사관리내용이 양식에 올바를 경우
				p.setBeginDate(modify);
			} else {
				return false;
			}
			
		}else if(modifyNumber.equals("5")) {
			System.out.printf("5. 면허/자격: ");
			modify = scan.nextLine();
			p.setLicence(modify);
			
		}else if(modifyNumber.equals("6")) {
			System.out.printf("6. 학력/경력: ");
			modify = scan.nextLine();
			p.setSchool(modify);
		}else if(modifyNumber.equals("7")) {
			return true;
		}else {
			System.out.println("숫자 1~7만 입력해주세요.");
			return false;
		}
		

		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("수정이 완료되었습니다.");
		System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
		scan.nextLine();
		return true;
		
		
		
		

	}//changeEmployee

	
	
	public static boolean newEmployee(PersonVo newEmployee) {//신규입사자등록메소드
		
		
		PersonVo v = PersonDao.list.get(PersonDao.list.size()-1); //새로운 사번(ID) 생성 과정
		if(v.getId().contains("A")) { //가져온 계정이 관리자 계정일 경우(관리자계정은A001이라 처리 안하면 M2로 나옴)
			v = PersonDao.list.get(PersonDao.list.size()-2);
		}
		String ExtractNewEmployeeNumber1 = v.getId().replace("M", "").replace("A", "");
		int NewEmployeeNumber = Integer.parseInt(ExtractNewEmployeeNumber1)+1;
		String newEmployeeId = "M"+ NewEmployeeNumber;//새로운 사번(ID) 생성 과정
		
		
		//신규입사자 등록 유효성 검사
		if(isValid(newEmployee)) {
			System.out.printf("(%s)%s님 정보 등록되었습니다.\n", newEmployee.getName(), newEmployee.getPosition());
			newEmployee.setId(newEmployeeId);
			newEmployee.setWorkStatus("true");
			PersonDao.list.add(newEmployee);
			
			loginEmployeeAdd(newEmployee);
			
			
			
			System.out.println("계속 진행하시려면 Enter를 입력해주세요.");
			
			Scanner scan = new Scanner(System.in);
			scan.nextLine();
			
			return true;
		
		} else {
			System.out.println("정보 등록에 실패했습니다.\n");
			return false;
		}
		
		
		
		
		
	}//newEmployee
	
	
	private static void loginEmployeeAdd(PersonVo newEmployee) {

		UserDao.list.add(new UserVo(newEmployee.getId(),"1111","1"));
		
	}



	//유효성검사
	public static boolean isValid(PersonVo newEmployee) {
		String regex = "";
		Pattern p1 = null;
		Matcher m1 = null;
		
		
		
		//이름
//		regex = "[가-힣]";
//		p1 = Pattern.compile(regex);
//		m1 = p1.matcher(newEmployee.getName());
//		if(!(m1.find())) {
//			System.out.println("이름 > 필수, 한글 2~5자 이내");
//			return false;
//		}
		

		
		
		//생년월일
		regex="^[0-9]{4}-[0-9]{2}-[0-9]{2}";
		p1 = Pattern.compile(regex);
		m1 = p1.matcher(newEmployee.getBirthDay());
		if(!(m1.find())) {
			System.out.println("올바른 생일을 입력해주세요.");
			return false;
		}
		
		//전화번호
		regex = "^010-[0-9]{3,4}-[0-9]{4}$";
		p1 = Pattern.compile(regex);
		m1 = p1.matcher(newEmployee.getTel());
		if(!(m1.find())) {
			System.out.println("올바른 전화번호를 입력해주세요.");
			return false;
		}
//		
		//주소
		regex = "^[가-힣]{1,5}시\\s[가-힣]{1,5}\\s[가-힣]{1,5}$";
		p1 = Pattern.compile(regex);
		m1 = p1.matcher(newEmployee.getAddr());
		if(!(m1.find())) {
			System.out.println("올바른 주소를 입력해주세요.");
			return false;
		}
//		
		//입사날짜
		regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
		p1 = Pattern.compile(regex);
		m1 = p1.matcher(newEmployee.getBeginDate());
		if(!(m1.find())) {
			System.out.println("올바른 입사 날짜를 입력해주세요.");
			return false;
		}
//		
//		
//		//부서
////		regex = "[가-힣]{2}팀$";
////		p1 = Pattern.compile(regex);
////		p1 = Pattern.compile(regex);
////		m1 = p1.matcher(department);
////		if(!(m1.find())) {
////			System.out.println("올바른 부서를 입력해주세요.");
////			return false;
////		}
//		
		boolean frag = false;
		
		String[] dp= {"인사팀", "구매팀", "기획팀", "영업팀", "개발팀", "재무팀"};
		
		for(String list1 : dp) {
			if(list1.equals(newEmployee.getDepartment())){
				frag = true;
				
				break;
			}
		}
		
		if (!frag) {
			System.out.println("부서를 잘못 입력하셨습니다");
			return false;
		}
		
		
		String[] po= {"주임", "부장", "차장", "대리", "사원", "과장"};
		
		frag = false;
		for(String list2 : po) {
			if(list2.equals(newEmployee.getPosition())){
				frag = true;
				break;
			}
		}
		if (!frag) {
			
			System.out.println("직급을 잘못 입력하셨습니다");
			return false;
		}
		
//		
//		
//		
		//호봉
		if(0 > Integer.parseInt(newEmployee.getHobong()) || 4 < Integer.parseInt(newEmployee.getHobong())) {
			System.out.println("호봉은 1~4만 입력 가능합니다.");
			return false;
		}
		
		return true;
		
		
		
	}	
	
	
	
	private static boolean regexs(String regex, String ps, String msg) {
		String regexs = "";
		Pattern p1 = null;
		Matcher m1 = null;
		
		
		regexs = regex;
		p1 = Pattern.compile(regexs);
		p1 = Pattern.compile(regexs);
		m1 = p1.matcher(ps);
		if(!(m1.find())) {
			System.out.println(msg);
			return false;
		}
		
		return true;
	}



	public static void updateArrayList(List<PersonVo> list, PersonVo updatedObject) {
		for(int i = 0; i < list.size(); i++) {
			PersonVo old = list.get(i);
			if(old.getId().equals(updatedObject.getId())) {
				list.set(i, updatedObject);
				break;
			}
		}
	}//updateArryaList
	
	
	public static void pause() {
		
		Scanner scan = new Scanner(System.in);
		String stop = scan.nextLine();
		
	}//pause

}