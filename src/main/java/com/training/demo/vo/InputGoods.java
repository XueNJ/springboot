package com.training.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class InputGoods {
	
	private String action;
	private Long mainPage;
	private Long showPage;
	private Long searchGoodsID;
	private String searchGoodsName;
	private Long searchPriceDown;
	private Long searchPriceUp;
	private String searchOrderBy;
	private Long searchGoodsQuantityDown;
	private String searchStatus;
	
}
