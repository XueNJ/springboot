package com.training.demo.vo;

import java.util.ArrayList;
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
public class OutputOrderDetail {
	
	private Long orderDetailID;
	private String goodsImageName;
//	private Long orderID;
	private String ImagePath;
	private String goodsName;
	private Long goodsBuyPrice;
	private Long buyQuantity;
	private Long buyAmount;
	
	@PostConstruct
	public OutputOrderDetail init() {
		return new OutputOrderDetail();
	}
	
	@PostConstruct
	public List<OutputOrderDetail> initList() {
		return new ArrayList<OutputOrderDetail>();
	}
	
}
