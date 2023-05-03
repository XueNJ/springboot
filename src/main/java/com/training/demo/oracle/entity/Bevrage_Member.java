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
//@ToString(exclude = {"order"})
@EqualsAndHashCode(of = { "identificationNO" })
@Entity
@Table(name = "BEVERAGE_MEMBER", schema = "LOCAL")
public class Bevrage_Member {

	@Id
	@Column(name = "IDENTIFICATION_NO", unique = true, nullable = false)
	private String identificationNO;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "STATUS")
	private String status;

}
