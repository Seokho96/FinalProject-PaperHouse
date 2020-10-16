package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto implements Serializable {

	private int writerSeq;
	private int articleSeq;
	private String title;
	private String cont;
	private String uploadDate;
	private int viewCount;
	private int likes;
	private String categorys;
	private String fileOriginal;
	private String fileSystem;
	
}
