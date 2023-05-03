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
public class OutputGoodsQuery {
	
	private List<OutputGoods> Goods;
	private int totalCount;
	
	@PostConstruct
	public OutputGoodsQuery init() {
		return new OutputGoodsQuery();
	}
	
}
