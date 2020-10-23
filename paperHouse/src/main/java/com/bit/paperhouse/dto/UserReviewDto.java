package com.bit.paperhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDto {
	
	private int reviewSeq;
	private int articleSeq;
	private int userSeq;
	private String nickName;
	private String cont;
	private String reviewDate;
	
	//paging
	private int pageNumber = 0;	
	private int recordCountPerPage = 10;	
	private int start = 1;
	private int end = 10;
	
}
