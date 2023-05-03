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
public class OutputGoods {
	
	private Long goodsID;
	private String goodsName;
	private Long goodsPrice;
	private Long goodsQuantity;
	private String goodsImageName;
	private String status;
	private String statusName;
	private String strImagePath;
	
	@PostConstruct
	public OutputGoods init() {
		return new OutputGoods();
	}
	
}
