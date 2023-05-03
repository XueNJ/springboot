package com.training.demo.Backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.demo.service.BackendService;
import com.training.demo.vo.InputGoods;
import com.training.demo.vo.InputGoodsAdd;
import com.training.demo.vo.InputGoodsUpdate;
import com.training.demo.vo.InputOrderBackend;
import com.training.demo.vo.OutputGoodsModify;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputOrderQuery;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/BackendController")
public class BackendController {
	// ----------- Autowired  ------------
	@Autowired
	private BackendService backendService;
	// ----------- JPA Criteria Queries  ------------
	/*
	 * (後台)商品列表查詢 :
	 * 
	 * 測試範例(json):
	 	{
		  "action": "List",
		  "mainPage": 1,
		  "searchGoodsID": 0,
		  "searchGoodsName": "Coke",
		  "searchGoodsQuantityDown": 1,
		  "searchOrderBy": "Ace",
		  "searchPriceDown": 3,
		  "searchPriceUp": 5,
		  "searchStatus": "1",
		  "showPage": 10
		}
	 * 
	 */
	@ApiOperation(value = "後台商品列表查詢  by JPA Criteria Queries")
	@PostMapping(value = "/queryGoods")
	public ResponseEntity<OutputGoodsQuery> queryGoods(@RequestBody InputGoods vo) {
		return ResponseEntity.ok(backendService.queryGoods(vo));
	}
	
	/*
	 * (後台)訂單列表查詢 :
	 * 
	 * 測試範例(json):
	 	{
		  "action": "List",
		  "mainPage": 0,
		  "searchGoodsID": 0,
		  "searchGoodsName": "",
		  "searchGoodsQuantityDown": 0,
		  "searchOrderBy": "",
		  "searchPriceDown": 0,
		  "searchPriceUp": 0,
		  "searchStatus": "",
		  "showPage": 9
		}
	 * 
	 */
	@ApiOperation(value = "後台訂單列表查詢  by JPA Criteria Queries")
	@PostMapping(value = "/queryOrder")
	public ResponseEntity<OutputOrderQuery> queryOrder(@RequestBody InputOrderBackend vo) {
		return ResponseEntity.ok(backendService.queryOrder(vo));
	}
	// ----------- JPA DML operations  ------------
	/*
	 * (後台)商品新增/更新
	 * 備註:
	 * 在 @PutMapping 加入該行 consumes= {MediaType.MULTIPART_FORM_DATA_VALUE} <==== 才取得到圖片檔案資訊。
	 * 
	 * 測試資料新增(text) : 
		goodsName : Apple
		goodsPrice : 30
		goodsQuantity : 20
		updateGoodsImage : file.jpg(隨意選擇圖片...)
		status : 1
	 *
	 */
	@ApiOperation(value = "後台商品新增/更新  by JPA DML Operations.")
//	@PutMapping(value = "/createGoods", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@PostMapping(value = "/createGoods", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<OutputGoodsModify> createGoods(@ModelAttribute InputGoodsAdd vo) throws IOException {
		return ResponseEntity.ok(backendService.createGoods(vo));
	}
	/*
	 * (後台)商品更新
	 * 備註:
	 * 在 @PutMapping 加入該行 consumes= {MediaType.MULTIPART_FORM_DATA_VALUE} <==== 才取得到圖片檔案資訊。
	 * 
	 * 測試資料更新(text) : 
		goodsName : Apple
		goodsPrice : 30
		goodsQuantity : 20
		updateGoodsImage : file.jpg(隨意選擇圖片...)
		status : 1
	 *
	 */
	@ApiOperation(value = "後台商品更新  by JPA DML Operations.")
//	@PutMapping(value = "/modifyGoods", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@PostMapping(value = "/updateGoods", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<OutputGoodsModify> updateGoods(@ModelAttribute InputGoodsUpdate vo) throws IOException {
		return ResponseEntity.ok(backendService.updateGoods(vo));
	}
	/*
	 * (後台)商品刪除
	 * 
	 * 測試資料(text) : 
		goodsID : 1
	 *
	 */
	@ApiOperation(value = "後台商品刪除  by JPA DML Operations.")
	@DeleteMapping(value = "/deleteGoods")
	public ResponseEntity<Long> deleteGoods(@RequestParam("goodsID") long goodsID) {
		backendService.deleteGoods(goodsID);
		return ResponseEntity.ok(goodsID);
	}
}
