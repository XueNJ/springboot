package com.training.demo.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.training.demo.oracle.dao.BackendCriteriaQueryDao;
import com.training.demo.oracle.dao.DMLOperationsBevrageGoodsDao;
import com.training.demo.oracle.entity.Bevrage_Goods;
import com.training.demo.vo.InputGoods;
import com.training.demo.vo.InputGoodsAdd;
import com.training.demo.vo.InputGoodsUpdate;
import com.training.demo.vo.InputOrderBackend;
import com.training.demo.vo.OutputGoods;
import com.training.demo.vo.OutputGoodsModify;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputOrderQuery;
import com.training.demo.vo.OutputResultMessage;

@Service
public class BackendService {

//	private static Logger logger = LoggerFactory.getLogger(BackendService.class);

	// ------------------------------ Autowired ------------------------------
	@Autowired
	private BackendCriteriaQueryDao backendCriteriaQueryDao;
	@Autowired
	private DMLOperationsBevrageGoodsDao backendDMLOperationsDao;
	@Autowired
	private OutputGoodsModify outputGoodsModify;
	@Autowired
	private OutputGoods outputGoods;
	@Autowired
	private OutputResultMessage outputResultMessage;

	// ------------------------------ Serveice Method ------------------------------
	/*
	 * (後台) 商品查詢
	 */
	public OutputGoodsQuery queryGoods(InputGoods vo) {
		return backendCriteriaQueryDao.queryGoods(vo);
	}

	/*
	 * (後台)商品新增
	 */
	@Transactional
	public OutputGoodsModify createGoods(InputGoodsAdd vo) throws IOException {
		String filePath = "C:\\home\\VendingMachine\\DrinksImage\\"; // Server存放
		boolean boolResult = false;
		MultipartFile updateGoodsImage = vo.getUpdateGoodsImage();
		try {
			// 圖片上傳
			if (updateGoodsImage.getOriginalFilename().equals("")) {
				byte[] bytes = updateGoodsImage.getBytes();
				Path path = Paths.get(filePath + updateGoodsImage.getOriginalFilename());
				Files.write(path, bytes);
			}
			Bevrage_Goods bevrageGoods = Bevrage_Goods.builder().goodsName(vo.getGoodsName()).price(vo.getGoodsPrice())
					.quantity(vo.getGoodsQuantity()).imageName(vo.getUpdateGoodsImage().getOriginalFilename())
					.status(vo.getStatus()).build();
			Bevrage_Goods rtnBevrageGoods = backendDMLOperationsDao.save(bevrageGoods);
			outputGoods.setGoodsID(rtnBevrageGoods.getGoodsID());
			outputGoods.setGoodsName(rtnBevrageGoods.getGoodsName());
			outputGoods.setGoodsPrice(rtnBevrageGoods.getPrice());
			outputGoods.setGoodsQuantity(rtnBevrageGoods.getQuantity());
			outputGoods.setGoodsImageName(rtnBevrageGoods.getImageName());
			outputGoods.setStatus(rtnBevrageGoods.getStatus());
			outputGoods.setStatusName(rtnBevrageGoods.getStatus() == "1" ? "啟用" : "停用");
			outputGoods.setStrImagePath(rtnBevrageGoods.getImageName());
			outputGoodsModify.setOutputGoods(outputGoods);
			boolResult = true;
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		outputResultMessage.setResult(boolResult ? "S" : "E");
		outputResultMessage.setMessage(boolResult ? "新增成功!" : "新增失敗!");
		outputGoodsModify.setOutputResultMessage(outputResultMessage);
		return outputGoodsModify;
	}

	/*
	 * (後台)商品更新
	 */
	@Transactional
	public OutputGoodsModify updateGoods(InputGoodsUpdate vo) {
		String filePath = "C:\\home\\VendingMachine\\DrinksImage\\"; // Server存放
		boolean boolResult = false;
		MultipartFile updateGoodsImage = vo.getUpdateGoodsImage();
		try {
			// 圖片上傳
			if (updateGoodsImage.getOriginalFilename().equals("")) {
				byte[] bytes = updateGoodsImage.getBytes();
				Path path = Paths.get(filePath + updateGoodsImage.getOriginalFilename());
				Files.write(path, bytes);
			}
			Optional<Bevrage_Goods> optGoods = backendDMLOperationsDao.findById(vo.getGoodsID());
			Bevrage_Goods bevrageGoods = null;
			if (optGoods.isPresent()) {
				bevrageGoods = optGoods.get();
				bevrageGoods.setGoodsName(vo.getGoodsName());
				bevrageGoods.setPrice(vo.getGoodsPrice());
				bevrageGoods.setQuantity(vo.getGoodsQuantity());
				bevrageGoods.setImageName(vo.getUpdateGoodsImage().getOriginalFilename());
				bevrageGoods.setStatus(vo.getStatus());
				outputGoods.setGoodsID(bevrageGoods.getGoodsID());
				outputGoods.setGoodsName(bevrageGoods.getGoodsName());
				outputGoods.setGoodsPrice(bevrageGoods.getPrice());
				outputGoods.setGoodsQuantity(bevrageGoods.getQuantity());
				outputGoods.setGoodsImageName(bevrageGoods.getImageName());
				outputGoods.setStatus(bevrageGoods.getStatus());
				outputGoodsModify.setOutputGoods(outputGoods);
				boolResult = true;
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		outputResultMessage.setResult(boolResult ? "S" : "E");
		outputResultMessage.setMessage(boolResult ? "更新成功!" : "更新失敗!");
		outputGoodsModify.setOutputResultMessage(outputResultMessage);
		return outputGoodsModify;
	}

	/*
	 * (後台)商品刪除
	 */
	public void deleteGoods(long storeID) {
		backendDMLOperationsDao.deleteById(storeID);
	}

	/*
	 * (後台) 訂單查詢
	 */
	public OutputOrderQuery queryOrder(InputOrderBackend vo) {
		return backendCriteriaQueryDao.queryOrder(vo);
	}

}
