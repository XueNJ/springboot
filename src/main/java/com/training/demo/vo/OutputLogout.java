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
public class OutputLogout {
	
	private OutputResultMessage outputResultMessage;
	private OutputMember outputMember;
	@PostConstruct
	public OutputLogout init() {
		return new OutputLogout();
	}
	
}
