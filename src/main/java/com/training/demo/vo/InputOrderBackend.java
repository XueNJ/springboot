package com.training.demo.vo;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class InputOrderBackend {
	
	private String action;
	private Long mainPage;
	private Long showPage;
	private LocalDateTime searchStartDate;
	private LocalDateTime searchEndDate;
	
}
