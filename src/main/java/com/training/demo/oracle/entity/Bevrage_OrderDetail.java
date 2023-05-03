package com.training.demo.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = { "orderDetailID" })
@Entity
@Table(name = "BEVERAGE_ORDER_DETAIL", schema = "LOCAL")
public class Bevrage_OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_DETAIL_ID_SEQ_GEN")
	@SequenceGenerator(name = "ORDER_DETAIL_ID_SEQ_GEN", sequenceName = "ORDER_DETAIL_ID_SEQ", allocationSize = 1)
	@Column(name = "ORDER_DETAIL_ID", unique = true, nullable = false)
	private Long orderDetailID;

	@Column(name = "ORDER_ID")
	private Long orderID;

	@Column(name = "GOODS_ID")
	private Long goodsID;

	@Column(name = "GOODS_BUY_PRICE")
	private Long goodsBuyPrice;

	@Column(name = "BUY_QUANTITY")
	private Long buyQuantity;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER) // EAGER(在查詢時立刻載入關聯的物件)
	@JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
	private Bevrage_Order order;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER) // EAGER(在查詢時立刻載入關聯的物件)
	@JoinColumn(name = "GOODS_ID", insertable = false, updatable = false)
	private Bevrage_Goods goods;
	
}
