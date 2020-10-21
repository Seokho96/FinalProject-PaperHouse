package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikesDto implements Serializable {
	
	private int articleSeq;
	private int userSeq;
	private String userLike;
	private int likes;
	
}
