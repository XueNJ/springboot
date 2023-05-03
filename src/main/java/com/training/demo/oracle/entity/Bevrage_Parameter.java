package com.training.demo.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = {"parameterID"})
@Entity
@Table(name = "BEVERAGE_PARAMETER", schema="LOCAL")
public class Bevrage_Parameter {

	@Id
	@Column(name = "PARAMETER_ID", unique = true, nullable = false)
	private String parameterID;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "TYPE")
	private String quantity;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "ORDERBY")
	private Integer orderBy;
	
}
