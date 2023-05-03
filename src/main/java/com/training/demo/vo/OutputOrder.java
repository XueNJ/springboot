package com.training.demo.vo;

import java.time.LocalDateTime;
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
public class OutputOrder {
	
	private Long OrderID;
	private String CustomerID;
	private String CustomerName;
	private LocalDateTime orderDate;
	private List<OutputOrderDetail> OrderDetails;
	private int TotalAmount;
	
	@PostConstruct
	public OutputOrder init() {
		return new OutputOrder();
	}
	
	@PostConstruct
	public List<OutputOrder> initList() {
		return new ArrayList<OutputOrder>();
	}
	
}
