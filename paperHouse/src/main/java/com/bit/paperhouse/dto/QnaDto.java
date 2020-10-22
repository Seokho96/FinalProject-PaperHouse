package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDto implements Serializable {
	
	private int userSeq;
	private int qnaSeq;
	private String title;
	private String conts;
	private String fileOriginal;
	private String fileSystem;
	private String reply;
	private String qdate;
	private int status;


	//검색 과 페이징을 위한 변수들
	private String choice;
	private String searchWord;

	// paging
	private int pageNumber = 0;	// 현재 페이지
	private int recordCountPerPage = 10;	// 표현할 페이지의 글수

	private int start = 1;
	private int end = 10;



}
