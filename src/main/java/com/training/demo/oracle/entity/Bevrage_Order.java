package com.training.demo.oracle.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString(exclude = {"orderdetail"})
@Entity
@Table(name = "BEVERAGE_ORDER", schema="LOCAL")
public class Bevrage_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SEQ_GEN")
	@SequenceGenerator(name = "ORDER_ID_SEQ_GEN", sequenceName = "ORDER_ID_SEQ", allocationSize = 1)
	@Column(name = "ORDER_ID", unique = true, nullable = false)
	private Long orderID;

	@Column(name = "ORDER_DATE")
	private LocalDateTime orderDate;

	@Column(name = "CUSTOMER_ID")
	private String customerID;
	
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private List<Bevrage_OrderDetail> orderdetail;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER) // EAGER(在查詢時立刻載入關聯的物件)
	@JoinColumn(name = "CUSTOMER_ID", insertable = false, updatable = false)
	private Bevrage_Member member;

}
