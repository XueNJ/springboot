package com.training.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class InputGoodsUpdate {
	
	private Long goodsID;
	private String goodsName;
	private Long goodsPrice;
	private Long goodsQuantity;
	private MultipartFile updateGoodsImage;
	private String status;
	
}
