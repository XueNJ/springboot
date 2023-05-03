package com.training.demo.oracle.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.demo.oracle.entity.Bevrage_Goods;
import com.training.demo.oracle.entity.Bevrage_Order;
import com.training.demo.oracle.entity.Bevrage_OrderDetail;
import com.training.demo.vo.InputGoods;
import com.training.demo.vo.InputOrderBackend;
import com.training.demo.vo.OutputGoods;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputOrder;
import com.training.demo.vo.OutputOrderDetail;
import com.training.demo.vo.OutputOrderQuery;

@Repository
public class BackendCriteriaQueryDao {

	@PersistenceContext(name = "oracleEntityManager")
	private EntityManager entityManager;
	@Autowired
	private OutputGoodsQuery outputGoodsQuery;
	@Autowired
	private OutputGoods outputGoods;
	@Autowired
	private List<OutputGoods> OutputGoodsList;
	@Autowired
	private OutputOrderQuery outputOrderQuery;
	@Autowired
	private OutputOrder outputOrder;
	@Autowired
	private List<OutputOrder> outputListOrder;
	@Autowired
	private OutputOrderDetail outputOrderDetail;
	@Autowired
	private List<OutputOrderDetail> outputListOrderDetail;

	// ----------- JPA Criteria Queries ------------
	/*
	 * (後台)商品列表查詢 :
	 */
	public OutputGoodsQuery queryGoods(InputGoods vo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bevrage_Goods> cq = cb.createQuery(Bevrage_Goods.class);
		Root<Bevrage_Goods> goods = cq.from(Bevrage_Goods.class);
		// 商品編號
		Predicate goodsID = cb.equal(goods.get("goodsID"), vo.getSearchGoodsID());
		// 商品名稱
		Predicate goodsName = cb.equal(goods.get("goodsName"), vo.getSearchGoodsName());
		// 商品金額(起)<=
		Predicate priceDown = cb.lessThanOrEqualTo(goods.get("price"), vo.getSearchPriceDown());
		// 商品金額(迄)>=
		Predicate priceUp = cb.greaterThanOrEqualTo(goods.get("price"), vo.getSearchPriceUp());
		// 商品金額 between(起)&(迄)
		Predicate priceBetween = cb.between(goods.get("price"), vo.getSearchPriceDown(), vo.getSearchPriceUp());
		// 庫存低於量 <=
		Predicate quantityDown = cb.lessThanOrEqualTo(goods.get("quantity"), vo.getSearchGoodsQuantityDown());
		// 商品狀態
		Predicate status = cb.equal(goods.get("status"), vo.getSearchStatus());
		// 金額排序
		Order order = vo.getSearchStatus().equals("Desc") ? cb.desc(goods.get("price")) : cb.asc(goods.get("price"));
		// 查詢條件 Predicate to Array
		List<Predicate> conditionsList = new ArrayList<Predicate>();
		if (vo.getSearchGoodsID() > 0) {
			conditionsList.add(goodsID);
		}
		if (!vo.getSearchGoodsName().equals("")) {
			conditionsList.add(goodsName);
		}
		if (vo.getSearchPriceDown() > 0 && vo.getSearchPriceUp() > 0) {
			conditionsList.add(priceBetween);
		} else if (vo.getSearchPriceDown() > 0 && vo.getSearchPriceUp() > 0) {
			conditionsList.add(priceDown);
		} else if (vo.getSearchPriceDown() > 0 && vo.getSearchPriceUp() > 0) {
			conditionsList.add(priceUp);
		}
		if (vo.getSearchGoodsQuantityDown() > 0) {
			conditionsList.add(quantityDown);
		}
		if (!vo.getSearchStatus().equals("")) {
			conditionsList.add(status);
		}
		// 放入全部查詢條件
		cq.select(goods).where(conditionsList.toArray(new Predicate[] {})).orderBy(order);
		// 執行查詢 setMaxResults(起)/setMaxResults(迄)
		int intMainPage = 0; // 指定頁數
		int intShowPage = 0; // 每頁筆數
		int intEndSeq = 0; // 起
		int intStartSeq = 0; // 迄
		// 列表 & 明細
		if (vo.getAction().equals("List")) {
			intMainPage = (int) (vo.getMainPage() == 0 ? 1 : vo.getMainPage());
			intShowPage = (int) (vo.getShowPage() == 0 ? 10 : vo.getShowPage());
			intEndSeq = intShowPage * intMainPage;
			intEndSeq = intEndSeq < 10 ? 10 : intEndSeq;
			intStartSeq = intEndSeq - intShowPage + 1;
			intStartSeq = intStartSeq - 1;
		} else {
			intStartSeq = 0;
			intEndSeq = 1;
		}
		// 執行結果
		TypedQuery<Bevrage_Goods> query = entityManager.createQuery(cq);
		TypedQuery<Bevrage_Goods> queryResult = entityManager.createQuery(cq).setFirstResult(intStartSeq)
				.setMaxResults(intEndSeq);
		// 初始化
		outputGoodsQuery = outputGoodsQuery.init();
		OutputGoodsList.clear();
		// 查詢結果塞值
		queryResult.getResultList().stream().forEach(S -> {
			outputGoods = outputGoods.init();
			outputGoods.setGoodsID(S.getGoodsID());
			outputGoods.setGoodsName(S.getGoodsName());
			outputGoods.setGoodsPrice(S.getPrice());
			outputGoods.setGoodsQuantity(S.getQuantity());
			outputGoods.setGoodsImageName(S.getImageName());
			outputGoods.setStatus(S.getStatus());
			outputGoods.setStatusName(S.getStatus().equals("1")? "啟用" : "停用");
			outputGoods.setStrImagePath("//DrinksImage//" + S.getImageName());
			OutputGoodsList.add(outputGoods);
		});
		outputGoodsQuery.setGoods(OutputGoodsList);
		outputGoodsQuery.setTotalCount(query.getResultList().size());
		return outputGoodsQuery;
	}

	/*
	 * (後台)商品列表查詢 :
	 */
	public OutputOrderQuery queryOrder(InputOrderBackend vo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bevrage_Order> cq = cb.createQuery(Bevrage_Order.class);
		Root<Bevrage_Order> order = cq.from(Bevrage_Order.class);
		// 訂單日期 (起)
		Predicate startOrderDate = null;
		// 訂單日期 (迄)
		Predicate endOrderDate = null;
		// 訂單日期 between(起)&(迄)
		Predicate betweenOrderDate = null;
		// 日期排序
		Order orderBy = cb.asc(order.get("orderDate"));
		// 查詢條件 Predicate to Array
		List<Predicate> conditionsList = new ArrayList<Predicate>();
		// Condition Add
		if (vo.getSearchStartDate() != null && vo.getSearchEndDate() != null) {
			// 訂單日期 between(起)&(迄)
			betweenOrderDate = cb.between(order.get("orderDate"), vo.getSearchEndDate(), vo.getSearchStartDate());
			conditionsList.add(betweenOrderDate);
		} else if (vo.getSearchEndDate() != null && vo.getSearchStartDate() == null) {
			endOrderDate = cb.greaterThanOrEqualTo(order.get("orderDate"), vo.getSearchEndDate());
			conditionsList.add(endOrderDate);
		} else if (vo.getSearchEndDate() == null && vo.getSearchStartDate() != null) {
			startOrderDate = cb.lessThanOrEqualTo(order.get("orderDate"), vo.getSearchStartDate());
			conditionsList.add(startOrderDate);
		}
		// 放入全部查詢條件
		cq.select(order).where(conditionsList.toArray(new Predicate[] {})).orderBy(orderBy);
		// 執行查詢 setMaxResults(起)/setMaxResults(迄)
		int intMainPage = 0; // 指定頁數
		int intShowPage = 0; // 每頁筆數
		int intEndSeq = 0; // 起
		int intStartSeq = 0; // 迄
		// 列表 & 明細
		if (vo.getAction().equals("List")) {
			intMainPage = (int) (vo.getMainPage() == 0 ? 1 : vo.getMainPage());
			intShowPage = (int) (vo.getShowPage() == 0 ? 10 : vo.getShowPage());
			intEndSeq = intShowPage * intMainPage;
			intEndSeq = intEndSeq < 10 ? 10 : intEndSeq;
			intStartSeq = intEndSeq - intShowPage + 1;
			intStartSeq = intStartSeq - 1;
		} else {
			intStartSeq = 0;
			intEndSeq = 1;
		}
		// 執行結果
		TypedQuery<Bevrage_Order> query = entityManager.createQuery(cq);
		TypedQuery<Bevrage_Order> queryResult = entityManager.createQuery(cq).setFirstResult(intStartSeq)
				.setMaxResults(intEndSeq);
		// 初始化
		outputOrderQuery = outputOrderQuery.init();
		outputListOrder = outputOrder.initList();
		// 查詢結果塞值
		for (Bevrage_Order enBo : queryResult.getResultList()) {
			outputOrder = outputOrder.init();
			outputListOrderDetail = outputOrderDetail.initList();
			outputOrder.setOrderID(enBo.getOrderID());
			outputOrder.setCustomerID(enBo.getMember().getIdentificationNO());
			outputOrder.setCustomerName(enBo.getMember().getCustomerName());
			outputOrder.setOrderDate(enBo.getOrderDate());
			// 訂單明細處理
			int OrderTotalAmount = 0;
			for (Bevrage_OrderDetail enBod : enBo.getOrderdetail()) {
				outputOrderDetail = outputOrderDetail.init(); // 初始化
				outputOrderDetail.setOrderDetailID(enBod.getOrderDetailID());
				outputOrderDetail.setGoodsName(enBod.getGoods().getGoodsName());
				outputOrderDetail.setGoodsImageName(enBod.getGoods().getImageName());
				outputOrderDetail.setImagePath("//DrinksImage//" + enBod.getGoods().getImageName());
				outputOrderDetail.setGoodsBuyPrice(enBod.getGoodsBuyPrice());
				outputOrderDetail.setBuyQuantity(enBod.getBuyQuantity());
				outputOrderDetail.setBuyAmount(enBod.getGoodsBuyPrice() * enBod.getBuyQuantity());
				outputListOrderDetail.add(outputOrderDetail);
				OrderTotalAmount += enBod.getGoodsBuyPrice() * enBod.getBuyQuantity();
			}
			outputOrder.setOrderDetails(outputListOrderDetail);
			outputOrder.setTotalAmount(OrderTotalAmount);
			outputListOrder.add(outputOrder);
		}
		outputOrderQuery.setOrders(outputListOrder);
		outputOrderQuery.setTotalCount(query.getResultList().size());
		return outputOrderQuery;
	}

}
