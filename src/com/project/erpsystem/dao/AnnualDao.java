package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.erpsystem.main.UserDao;
import com.project.erpsystem.vo.AnnualVo;

public class AnnualDao {

	public static ArrayList<AnnualVo> list;
	
	static {
		list=new ArrayList<AnnualVo>();
	}
	
	public static void load() {
		
		try {
			String dir="data\\annual.txt";
			
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				
				list.add(new AnnualVo(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void save() {
		
		try {
			String dir="data\\annual.txt";
			
			BufferedWriter writer=new BufferedWriter(new FileWriter(dir));
			
			for(AnnualVo av : list) {
				String result=String.format("%s,%d,%d,%d\r\n"
										,av.getId()
										,av.getAllotmentAnnual()
										,av.getUsedAnnal()
										,av.getLeftoverAnnual());										;
				writer.write(result);
			}
			
			
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public static AnnualVo checkAnnualApply() { //남은 연차 출력 메소드
		AnnualVo an=new AnnualVo();
		
		String id=UserDao.auth.getId();
		
		for(AnnualVo av : list) {
			if(av.getId().equals(id)) {
				an=av;
				break;
			}
		}
		
		return an;
	}
	
	
	
	
	
	
}
