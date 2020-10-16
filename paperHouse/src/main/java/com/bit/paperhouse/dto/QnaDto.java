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
	private int status;
}
