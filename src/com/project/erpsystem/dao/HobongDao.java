package com.project.erpsystem.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.project.erpsystem.vo.HobongVo;

//삭제하기
public class HobongDao {
	public static ArrayList<HobongVo> hobongList;
	
	static {
		HobongDao.hobongList = new ArrayList<HobongVo>();
	}
	
	
	public static void load() {
		String path = "data\\hobong.txt";
		
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
				HobongVo hobong = new HobongVo(temp[0], temp[1]
													,Integer.parseInt(temp[2])
													,Integer.parseInt(temp[3]));
				HobongDao.hobongList.add(hobong);
			}
			
			reader.close();
			
			
		} catch (Exception e) {
			System.out.println("at HobongDao.load");
			e.printStackTrace();
		}
	}//load
}
