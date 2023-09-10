package com.project.erpsystem.dao;

import com.project.erpsystem.main.PersonDao;
import com.project.erpsystem.main.PersonVo;

public class PersonDepartmentCh {

	public static String checkDepartmentMember(String dp) {
		String result="";
		for(PersonVo pv : PersonDao.list) {
			//부장, 차장, 과장, 대리, 주임, 사원
			if(pv.getDepartment().equals(dp) && pv.getWorkStatus().equals("true")) {
				result+=printDp(pv);
			}
		}
		return result;
	}
	
	
	public static String printDp(PersonVo pv) {
		String result="";
		result=String.format("%s\t%2s\t%3s\t%s\n"
				,pv.getDepartment()
				,pv.getPosition()
				,pv.getName()
				,pv.getTel());
		
		return result;
	}
	

}//c
