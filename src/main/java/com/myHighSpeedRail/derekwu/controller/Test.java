package com.myHighSpeedRail.derekwu.controller;

import org.springframework.data.repository.query.Param;

public class Test {

	public static void main(String[] args) {
		System.out.println(empMorekeywordSearch("你好 謝謝 再見 掰掰"));
		

	}
	public static String empMorekeywordSearch(@Param("keywords")String keywords) {
		String searchQuery = "select * from lost_property where ";
		//防呆:刪掉結尾的空格
		while(keywords.endsWith(" ")) {
			keywords = keywords.replaceFirst(".$","");
		}
		//用" "決定 detail_outward like '%姓名%' 的總數
//		System.out.println("keywords.length = "+ keywords.length());
//		int spaceCount = 0;
//		char space = ' ';
		//char只吃單引號啦幹
		
//		for(int i=0;i<keywords.length();i++) {
//			if(keywords.charAt(i)==' ') {
//				spaceCount++;
//			}
//		}
//		System.out.println(spaceCount);
		String arrayKeywords[] = keywords.split(" ");
		for(int i=0;i<arrayKeywords.length;i++) {
			if(i==0)searchQuery =searchQuery + "detail_outward like '%" + arrayKeywords[i] + "%'";
			else searchQuery = searchQuery + " and detail_outward like '%" + arrayKeywords[i] + "%'";
		}
		
		return searchQuery;
	}

}
