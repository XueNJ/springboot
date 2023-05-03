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
public class OutputShoppingCartGoods {
	
	private Long goodsID;
	private String goodsName;
	private Long goodsPrice;
	private Long goodQuantity;
	private Long buyQuantity;
	private String goodsImageName;
	private String status;
	
	@PostConstruct
	public OutputShoppingCartGoods init() {
		return new OutputShoppingCartGoods();
	}
	
}
