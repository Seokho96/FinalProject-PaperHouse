package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class UserSubscribeDto implements Serializable {
	
	private int userSeq;
	private int writerSeq;
	private String startDate;
	private String endDate;
	private String status;
	
}
