package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto implements Serializable {
	private int noticeSeq;
	private String title;
	private String cont;
	private String uploadDate;
	private String fileOriginal;
	private String fileSystem;
	private int viewCount;
	private int del;
}
