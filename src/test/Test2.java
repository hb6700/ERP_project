package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

import com.project.erpsystem.main.PersonVo;

public class Test2 {

	public static ArrayList<PersonVo> list;
	
	static {
		list=new ArrayList<PersonVo>();
	}
	
	
	
	public static void main(String[] args) {
		
		HashSet<String> hs=new HashSet<String>();
		
		try {
			String dir1="data\\AnnualLeaveUsage.txt";
			
			BufferedReader reader=new BufferedReader(new FileReader(dir1));
			
			String line=null;
			
			
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
//				PersonVo ps=new PersonVo();
				
				String id="M130";
				if(temp[0].equals(id)) {
					System.out.println(temp[3]);
					
					
				}
//				hs.add(temp[6]);
				
//				ps.setId(temp[0]);
//				ps.setName(temp[1]);
//				ps.setBirthDay(temp[2]);
//				ps.setAddr(temp[3]);
//				ps.setTel(temp[4]);
//				ps.setDepartment(temp[5]);
//				ps.setPosition(temp[6]);
//				ps.setHobong(temp[7]);
//				ps.setBeginDate(temp[8]);
//				ps.setLicence(temp[9]);
//				ps.setSchool(temp[10]);
//				ps.setRating(temp[11]);
//				ps.setWorkStatus(temp[12]);
//				
//				list.add(ps);
				
			}
			
			reader.close();
			
//			System.out.println(list.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		System.out.println(hs.toString());
//		
//		System.out.println("\t\t쌍용 주식회사");
//		System.out.println("\t\t     |");
//		System.out.println("    ┌──────┬──────┬──────┬──────┬─────┐");
//		System.out.println("  인사팀   구매팀  기획팀   영업팀  개발팀  재무팀");
//		
//		System.out.println("[부서]\t[직급]\t[이름]\t[전화번호]");
//		String result=String.format("%s\t%3s\t%3s\t%s\n"
//				,"인사팀"
//				,"과장"
//				,"김수진"
//				,"대전광역시 중구 방배동");
//		System.out.println(result);
		
		
		
	}
}
