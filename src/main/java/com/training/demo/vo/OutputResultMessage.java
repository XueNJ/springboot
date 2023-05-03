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
public class OutputResultMessage {
	
	private String Result;
	private String Message;
	
	@PostConstruct
	public OutputResultMessage init() {
		return new OutputResultMessage();
	}
	
}
