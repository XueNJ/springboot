package com.training.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.demo.oracle.dao.DMLOperationsBevrageGoodsDao;
import com.training.demo.oracle.dao.FrontendCriteriaQueryDao;
import com.training.demo.oracle.dao.DMLOperationsBevrageOrderDao;
import com.training.demo.oracle.entity.Bevrage_Goods;
import com.training.demo.oracle.entity.Bevrage_Order;
import com.training.demo.oracle.entity.Bevrage_OrderDetail;
import com.training.demo.vo.InputOrderFrontend;
import com.training.demo.vo.InputShoppingCartGoods;
import com.training.demo.vo.InputVendingMachine;
import com.training.demo.vo.OutputGoods;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputOrderQuery;
import com.training.demo.vo.OutputPayment;
import com.training.demo.vo.OutputResultMessage;
import com.training.demo.vo.OutputShoppingCartGoods;
import com.training.demo.vo.OutputShoppingCartGoodsAdd;
import com.training.demo.vo.OutputShoppingCartGoodsInfo;
import com.training.demo.vo.SessionMember;

@Service
public class FrontendService {

//	private static Logger logger = LoggerFactory.getLogger(BackendService.class);

	// ------------------------------ Autowired ------------------------------
	@Autowired
	private FrontendCriteriaQueryDao frontendCriteriaQueryDao;
	@Autowired
	private DMLOperationsBevrageOrderDao dMLOperationsBevrageOrderDao;
	@Autowired
	private DMLOperationsBevrageGoodsDao dMLOperationsBevrageGoodsDao;
	@Resource(name = "sessionScopedBeanSCGList")
	private Map<Long, OutputShoppingCartGoods> sessionScopedBeanSCGMap;
	@Resource(name = "sessionScopedBeanSessionMember")
	private SessionMember sessionScopedBeanMember;
	@Autowired
	private List<OutputShoppingCartGoods> outputShoppingCarGoodsList;
	@Autowired
	private OutputShoppingCartGoods outputShoppingCartGoods;
	@Autowired
	private OutputShoppingCartGoodsInfo outputShoppingCartGoodsInfo;
	@Autowired
	private OutputResultMessage outputResultMessage;
	@Autowired
	private InputVendingMachine inputVendingMachine;
	@Autowired
	private OutputGoods outputGoods;
	@Autowired
	private OutputShoppingCartGoodsAdd outputShoppingCartGoodsAdd;
	@Autowired
	private OutputPayment outputPayment;
	@Autowired
	private OutputOrderQuery outputOrderQuery;

	// ------------------------------ Serveice Method ------------------------------
	/*
	 * (前台)加入購物車商品 :
	 */
	public OutputShoppingCartGoodsAdd addCartGoods(InputShoppingCartGoods vo) {
		boolean boolResult = false;
		String strMessage = "";
		outputShoppingCarGoodsList.clear();
		// 判斷數量充足
		inputVendingMachine.setAction("");
		inputVendingMachine.setGoodsID(vo.getGoodsID());
		inputVendingMachine.setGoodsName("");
		OutputGoodsQuery outputGoodsQuery = frontendCriteriaQueryDao.queryGoods(inputVendingMachine);
		for (OutputGoods o : outputGoodsQuery.getGoods()) {
			outputGoods.init();
			outputGoods.setGoodsID(o.getGoodsID());
			outputGoods.setGoodsName(o.getGoodsName());
			outputGoods.setGoodsPrice(o.getGoodsPrice());
			outputGoods.setGoodsImageName(o.getGoodsImageName());
			outputGoods.setGoodsQuantity(o.getGoodsQuantity());
			outputGoods.setStatus(o.getStatus());
		}
		if (outputGoods.getGoodsQuantity() < vo.getBuyQuantity()) {
			boolResult = false;
			strMessage = "商品數量不足!";
		} else {
			boolResult = true;
		}
		if (boolResult) {
			// 初次 & 沒有重複商品
			if (sessionScopedBeanSCGMap.size() == 0 || !sessionScopedBeanSCGMap.containsKey(vo.getGoodsID())) {
				outputShoppingCartGoods = outputShoppingCartGoods.init();
				outputShoppingCartGoods.setGoodsID(outputGoods.getGoodsID());
				outputShoppingCartGoods.setGoodsName(outputGoods.getGoodsName());
				outputShoppingCartGoods.setGoodsImageName(outputGoods.getGoodsImageName());
				outputShoppingCartGoods.setGoodsPrice(outputGoods.getGoodsPrice());
				outputShoppingCartGoods.setGoodQuantity(outputGoods.getGoodsQuantity());
				outputShoppingCartGoods.setBuyQuantity(vo.getBuyQuantity());
				outputShoppingCartGoods.setStatus(outputGoods.getStatus());
				sessionScopedBeanSCGMap.put(outputShoppingCartGoods.getGoodsID(), outputShoppingCartGoods);
				outputShoppingCartGoodsAdd.setOutputShoppingCartGoods(outputShoppingCartGoods);
				boolResult = true;
				strMessage = "加入購物車成功!";
			} else {
				if (sessionScopedBeanSCGMap.get(vo.getGoodsID()).getBuyQuantity() + vo.getBuyQuantity() > outputGoods
						.getGoodsQuantity()) {
					boolResult = false;
					strMessage = "商品數量不足!";
				} else {
					sessionScopedBeanSCGMap.get(vo.getGoodsID()).setBuyQuantity(
							sessionScopedBeanSCGMap.get(vo.getGoodsID()).getBuyQuantity() + vo.getBuyQuantity());
				}
			}
		}
		// set ouput message
		outputResultMessage.setResult(boolResult == true ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputShoppingCartGoodsAdd.setOutputResultMessage(outputResultMessage);
		outputShoppingCartGoodsAdd.setOutputShoppingCartGoods(sessionScopedBeanSCGMap.get(vo.getGoodsID()));
		return outputShoppingCartGoodsAdd;
	}

	/*
	 * (前台)查詢購物車商品 :
	 */
	public OutputShoppingCartGoodsInfo queryCartGoods() {
		int intTotalPrice = 0;
		outputShoppingCarGoodsList.clear();
		if (sessionScopedBeanSCGMap.size() != 0) {
			for (Entry<Long, OutputShoppingCartGoods> entry : sessionScopedBeanSCGMap.entrySet()) {
				outputShoppingCarGoodsList.add(entry.getValue());
				intTotalPrice += entry.getValue().getGoodsPrice() * entry.getValue().getBuyQuantity();
			}
		}
		outputShoppingCartGoodsInfo.setShoppingCartGoods(outputShoppingCarGoodsList);
		outputShoppingCartGoodsInfo.setTotalAmount(intTotalPrice);
		return outputShoppingCartGoodsInfo;
	}

	/*
	 * (前台)清空購物車商品 :
	 */
	public OutputResultMessage clearCartGoods() {
		try {
			sessionScopedBeanSCGMap.clear();
			outputResultMessage.setResult("S");
			outputResultMessage.setMessage("購物車清空成功!");
		} catch (Exception ex) {
			outputResultMessage.setResult("E");
			outputResultMessage.setMessage("購物車清空成功!");
		}
		return outputResultMessage;
	}

	/*
	 * (前台) 商品查詢
	 */
	public OutputGoodsQuery queryGoods(InputVendingMachine vo) {
		return frontendCriteriaQueryDao.queryGoods(vo);
	}

	/*
	 * (前台) 歷史訂單查詢
	 */
	public OutputOrderQuery queryOrder(InputOrderFrontend vo) {
		boolean boolResult = false;
		String strMessage = "";
		try {
			if (sessionScopedBeanMember.getId() == null) {
				strMessage = "請先登入系統!";
			} else {
				vo.setId(sessionScopedBeanMember.getId());
				outputOrderQuery = frontendCriteriaQueryDao.queryOrder(vo);
				boolResult = true;
				strMessage = "查詢成功!";
			}
		} catch (Exception ex) {
			boolResult = false;
			strMessage = "系統錯誤!";
			ex.getStackTrace();
		}
		outputResultMessage.setResult(boolResult == true ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputOrderQuery.setOutputResultMessage(outputResultMessage);
		return outputOrderQuery;
	}

	/*
	 * (前台) 歷史訂單查詢
	 */
	@Transactional
	public OutputPayment paymentGoods(int inputMoney) {
		boolean boolResult = false;
		String strMessage = "";
		int intTotalPrice = 0;
		// 查詢訂單商品金額確認
		if (sessionScopedBeanMember.getId() == null) {
			strMessage = "請先登入系統！";
			boolResult = false;
		} else if (sessionScopedBeanSCGMap.size() == 0) {
			strMessage = "購物車無商品，無須付款！";
			boolResult = true;
		} else {
			List<Bevrage_OrderDetail> listBOD = new ArrayList<Bevrage_OrderDetail>();
			for (Entry<Long, OutputShoppingCartGoods> entry : sessionScopedBeanSCGMap.entrySet()) {
				outputShoppingCarGoodsList.add(entry.getValue());
				intTotalPrice += entry.getValue().getGoodsPrice() * entry.getValue().getBuyQuantity();
				Bevrage_OrderDetail bod = new Bevrage_OrderDetail();
				bod.setGoodsID(entry.getValue().getGoodsID());
				bod.setGoodsBuyPrice(entry.getValue().getGoodsPrice());
				bod.setBuyQuantity(entry.getValue().getBuyQuantity());
				listBOD.add(bod);
			}
			// 檢核 付款金額 & 商品總金額
			if (inputMoney < intTotalPrice) {
				strMessage = "商品總金額:" + intTotalPrice + "，付款金額:" + inputMoney + "。[不足:" + (intTotalPrice - inputMoney)
						+ "]";
			} else {
				try {
					// 新增訂單
					Bevrage_Order bo = Bevrage_Order.builder().customerID(sessionScopedBeanMember.getId())
							.orderDate(LocalDateTime.now()).orderdetail(listBOD).build();
					Bevrage_Order boSave = dMLOperationsBevrageOrderDao.save(bo);
					if (boSave != null) {
						boolResult = true;
						// 更新庫存
						List<Bevrage_Goods> bgList = new ArrayList<>();
						for (Bevrage_OrderDetail bod : boSave.getOrderdetail()) {
							Bevrage_Goods bg = Bevrage_Goods.builder()
									.goodsID(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getGoodsID())
									.goodsName(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getGoodsName())
									.imageName(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getGoodsImageName())
									.price(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getGoodsPrice())
									.quantity(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getGoodQuantity()
											- bod.getBuyQuantity())
									.status(sessionScopedBeanSCGMap.get(bod.getGoodsID()).getStatus()).build();
							bgList.add(bg);
						}
						dMLOperationsBevrageGoodsDao.saveAll(bgList);
						outputShoppingCarGoodsList.clear();
						int totalAmount = 0;
						for (Entry<Long, OutputShoppingCartGoods> entry : sessionScopedBeanSCGMap.entrySet()) {
							outputShoppingCartGoods = outputShoppingCartGoods.init();
							outputShoppingCartGoods = entry.getValue();
							outputShoppingCarGoodsList.add(outputShoppingCartGoods);
							totalAmount += entry.getValue().getBuyQuantity() * entry.getValue().getGoodsPrice();
						}
						outputShoppingCartGoodsInfo = outputShoppingCartGoodsInfo.init();
						outputShoppingCartGoodsInfo.setShoppingCartGoods(outputShoppingCarGoodsList);
						outputShoppingCartGoodsInfo.setTotalAmount(totalAmount);
						outputPayment.setOutputShoppingCartGoodsInfo(outputShoppingCartGoodsInfo);

						strMessage = "交易成功，訂單已成功建立！";
					}
				} catch (Exception ex) {
					ex.getStackTrace();
					boolResult = false;
					strMessage = "系統錯誤!";
				}
			}
		}
		outputResultMessage.setResult(boolResult ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputPayment.setOutputResultMessage(outputResultMessage);
		// 成功 清除購物車
		if (boolResult) {
			sessionScopedBeanSCGMap.clear();
		}
		return outputPayment;
	}

}
