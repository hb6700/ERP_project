package com.project.erpsystem.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class PersonDao {

	public static ArrayList<PersonVo> list;
	
	static {
		list=new ArrayList<PersonVo>();
	}
	
	public static void load() {
		
		try {
			String dir="data\\user.txt";
			
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				PersonVo ps=new PersonVo();
				ps.setId(temp[0]);
				ps.setName(temp[1]);
				ps.setBirthDay(temp[2]);
				ps.setAddr(temp[3]);
				ps.setTel(temp[4]);
				ps.setDepartment(temp[5]);
				ps.setPosition(temp[6]);
				ps.setHobong(temp[7]);
				ps.setBeginDate(temp[8]);
				ps.setLicence(temp[9]);
				ps.setSchool(temp[10]);
				ps.setRating(temp[11]);
				ps.setWorkStatus(temp[12]);
				
				list.add(ps);
				
			}
			
			reader.close();
			
//			System.out.println(list.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void save() { //여기서는 추가 기능없음
		
		try {
			String dir="data\\user.txt";
			
			BufferedWriter writer=new BufferedWriter(new FileWriter(dir));
			
			for(PersonVo av : list) {// 쓸꺼면 수정해야됨
				String result=String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
						av.getId(),
						av.getName(),
						av.getBirthDay(),
						av.getAddr(),
						av.getTel(),
						av.getDepartment(),
						av.getPosition(),
						av.getHobong(),
						av.getBeginDate(),
						av.getLicence(),
						av.getSchool(),
						av.getRating(),
						av.getWorkStatus()
										);
				writer.write(result);
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void save() { 여기서는 추가 기능없음
//		
//		try {
//			String dir="data\\user.txt";
//			
//			BufferedWriter writer=new BufferedWriter(new FileWriter(dir));
//			
//			for(UserVo av : list) {// 쓸꺼면 수정해야됨
//				String result=String.format("%s,%s,%s,%d,%s"
//										,av.getId()
//										,av.getPw()
//										,av.getLevel() );
//				writer.write(result);
//			}
//			
//			
//			writer.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	
}
