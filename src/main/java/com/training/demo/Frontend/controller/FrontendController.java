package com.training.demo.Frontend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.demo.service.FrontendService;
import com.training.demo.vo.InputOrderFrontend;
import com.training.demo.vo.InputShoppingCartGoods;
import com.training.demo.vo.InputVendingMachine;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputOrderQuery;
import com.training.demo.vo.OutputPayment;
import com.training.demo.vo.OutputResultMessage;
import com.training.demo.vo.OutputShoppingCartGoodsAdd;
import com.training.demo.vo.OutputShoppingCartGoodsInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/FrontendController")
public class FrontendController {
	
	// ------------------------------ Autowired ----------------------------
	@Autowired
	private FrontendService frontendService;
	
	// ------------------------------ Backend ------------------------------
	/*
	 * (前台)加入購物車商品 :
	 * 
	 * 測試範例(json): 
	 {
	  "buyQuantity": 1,
	  "goodsID": 2,
	  "goodsImageName": "test.jpg",
	  "goodsName": "test",
	  "goodsPrice": 2
	  }
	 */
	@ApiOperation(value = "加入購物車商品...")
	@PostMapping(value = "/addCartGoods")
	public ResponseEntity<OutputShoppingCartGoodsAdd> addCartGoods(@RequestBody InputShoppingCartGoods vo) {
		return ResponseEntity.ok(frontendService.addCartGoods(vo));
	}
	
	/*
	 * (前台)查詢購物車商品 :
	 * 
	 * 測試範例(json): 無。
	 */
	@ApiOperation(value = "查詢購物車商品...")
	@PostMapping(value = "/queryCartGoods")
	public ResponseEntity<OutputShoppingCartGoodsInfo> queryCartGoods() {
		return ResponseEntity.ok(frontendService.queryCartGoods());
	}
	
	/*
	 * (前台)清空購物車商品 :
	 * 
	 * 測試範例(json): 無。
	 */
	@ApiOperation(value = "清空購物車商品...")
	@PostMapping(value = "/clearCartGoods")
	public ResponseEntity<OutputResultMessage> clearCartGoods() {
		return ResponseEntity.ok(frontendService.clearCartGoods());
	}
	
	// ----------- JPA Criteria Queries  ------------
		/*
		 * (前台)商品列表查詢 :
		 * 
		 * 測試範例(json):
		 	{
			  "action": "List",
			  "goodsID": 0,
			  "goodsName": "",
			  "mainPage": 2,
			  "showPage": 10
			}
		 * 
		 */
		@ApiOperation(value = "前台商品查詢  by JPA Criteria Queries")
		@PostMapping(value = "/queryGoods")
		public ResponseEntity<OutputGoodsQuery> queryGoods(@RequestBody InputVendingMachine vo) {
			return ResponseEntity.ok(frontendService.queryGoods(vo));
		}
		
		/*
		 * (前台)訂單查詢 :
		 * 
		 * 測試範例(json):
		 	{
			  "action": "List",
			  "id": "1",
			  "mainPage": 0,
			  "showPage": 10
			}
		 * 
		 */
		@ApiOperation(value = "前台訂單查詢  by JPA Criteria Queries")
		@PostMapping(value = "/queryOrder")
		public ResponseEntity<OutputOrderQuery> queryOrder(@RequestBody InputOrderFrontend vo) {
			return ResponseEntity.ok(frontendService.queryOrder(vo));
		}
		
		/*
		 * (前台)前台付款結帳  :
		 * 
		 * 測試範例(text):
		 * inputMoney : 隨意輸入金額
		 * 
		 */
		@ApiOperation(value = "前台付款結帳  by JPA DML Operations.")
		@PostMapping(value = "/paymentGoods")
		public ResponseEntity<OutputPayment> paymentGoods(@RequestParam("inputMoney") int inputMoney) {
			return ResponseEntity.ok(frontendService.paymentGoods(inputMoney));
		}
}
