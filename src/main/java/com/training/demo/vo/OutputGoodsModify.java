package com.training.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class OutputGoodsModify {
	
	private OutputResultMessage outputResultMessage;
	private OutputGoods outputGoods;
}
