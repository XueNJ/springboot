package com.training.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class InputVendingMachine {
	
	private String action;
	private Long mainPage;
	private Long showPage;
	private Long goodsID;
	private String goodsName; 
	
}
