package com.bit.paperhouse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	

	private int userSeq;
	private String email;
	private String pwd;
	private String nickname;
	private String authority;
	private int enabled;
	
}
