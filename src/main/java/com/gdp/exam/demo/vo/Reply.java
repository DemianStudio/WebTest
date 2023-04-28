package com.gdp.exam.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int id;
	private Date regDate;
	private Date updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String title;
	private String body;
	private int goodReactionPoint;
	private int badReactionPoint;

	private String extra__writerName;
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;

	/*
	 * public String getRegDateForPrint() { return regDate.substring(2, 16); }
	 */
	
	public Date getRegDateForPrint() {
		return regDate;
	}

	public Date getUpdateDateForPrint() {
		return regDate;
	}

	public String getForPrintBody() {
		return body.replaceAll("\n","<br>" );
	}
}