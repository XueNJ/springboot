package com.training.demo.vo;

import javax.annotation.PostConstruct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class OutputMemberModify {
	
	private OutputMember outputMember;
	private OutputResultMessage outputResultMessage;
	
	@PostConstruct
	public OutputMemberModify init() {
		return new OutputMemberModify();
	}
}
