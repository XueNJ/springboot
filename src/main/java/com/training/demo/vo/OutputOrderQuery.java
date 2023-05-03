package com.training.demo.vo;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class OutputOrderQuery {
	
	private OutputResultMessage outputResultMessage;
	private List<OutputOrder> Orders;
	private int TotalCount;
	
	@PostConstruct
	public OutputOrderQuery init() {
		return new OutputOrderQuery();
	}
	
}
