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
public class SessionMember {
	
	private String id;
	private String name;
	private String pwd;
	private String status;
	
	@PostConstruct
	public SessionMember init() {
		return new SessionMember();
	}
	
}
