package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class PaymentDto implements Serializable {

	private int paySeq;
	private int userSeq;
	private int writerSeq;
	private String approvalNumber;
	private String paymentMethod;
	private String paymentDetail;
	private String paymentDate;
}
