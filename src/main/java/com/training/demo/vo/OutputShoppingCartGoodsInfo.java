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
public class OutputShoppingCartGoodsInfo {

	private List<OutputShoppingCartGoods> shoppingCartGoods;
	private int totalAmount;
	
	@PostConstruct
	public OutputShoppingCartGoodsInfo init() {
		return new OutputShoppingCartGoodsInfo();
	}

}
