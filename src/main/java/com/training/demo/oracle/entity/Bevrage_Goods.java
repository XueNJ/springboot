package com.training.demo.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Data
@ToString
//@ToString(exclude = {"orderdetail"})
@Entity
@Table(name = "BEVERAGE_GOODS", schema="LOCAL")
public class Bevrage_Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOODS_ID_SEQ_GEN")
    @SequenceGenerator(name = "GOODS_ID_SEQ_GEN", sequenceName = "GOODS_ID_SEQ", allocationSize = 1)
	@Column(name = "GOODS_ID", unique = true, nullable = false)
	private Long goodsID;
	
	@Column(name = "GOODS_NAME")
	private String goodsName;
	
	@Column(name = "PRICE")
	private Long price;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
	@Column(name = "IMAGE_NAME")
	private String imageName;
	
	@Column(name = "STATUS")
	private String status;
	
//	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
//	@JoinColumn(name = "GOODS_ID")
//	private List<OrderDetail> orderdetail;
	
}
