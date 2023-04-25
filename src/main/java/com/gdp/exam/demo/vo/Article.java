package com.gdp.exam.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private Date regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private int hitCount;
	
	private String extra__writerName;
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;
	
//	public String getRegDateForPrint() {
//		return regDate.substring(2, 16);
//	}
//
//	public String getUpdateDateForPrint() {
//		return regDate.substring(2, 16);
//	}

}