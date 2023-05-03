package com.training.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class InputMemberModify {
	
	private String action;
	private String id;
	private String name;
	private String pwd;
	private String pwdCheck;
	private String status;
	
}
