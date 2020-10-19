package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto implements Serializable {

	private String searchCategory;
	private String searchWord;
	private String searchSort;
	private String sort;
	private int wNowPage;
	private int cNowPage;
	private int start;
	private int end;
	private String categorySort;
}
