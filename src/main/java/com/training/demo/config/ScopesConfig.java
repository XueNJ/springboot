package com.training.demo.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.training.demo.vo.InputMemberLogin;
import com.training.demo.vo.InputVendingMachine;
import com.training.demo.vo.OutputGoods;
import com.training.demo.vo.OutputGoodsModify;
import com.training.demo.vo.OutputGoodsQuery;
import com.training.demo.vo.OutputLogin;
import com.training.demo.vo.OutputLogout;
import com.training.demo.vo.OutputMember;
import com.training.demo.vo.OutputMemberModify;
import com.training.demo.vo.OutputOrder;
import com.training.demo.vo.OutputOrderDetail;
import com.training.demo.vo.OutputOrderQuery;
import com.training.demo.vo.OutputPayment;
import com.training.demo.vo.OutputResultMessage;
import com.training.demo.vo.OutputShoppingCartGoods;
import com.training.demo.vo.OutputShoppingCartGoodsAdd;
import com.training.demo.vo.OutputShoppingCartGoodsInfo;
import com.training.demo.vo.SessionMember;

@Configuration
public class ScopesConfig {

	@Bean
	@SessionScope
	public Map<Long, OutputShoppingCartGoods> sessionScopedBeanSCGList() {
		return new LinkedHashMap<Long, OutputShoppingCartGoods>();
	}
	@Bean
	@SessionScope
	public SessionMember sessionScopedBeanSessionMember() {
		return new SessionMember();
	}
	
	@Bean
	public OutputShoppingCartGoodsInfo outputShoppingCartGoodsInfo() {
		return new OutputShoppingCartGoodsInfo();
	}

	@Bean
	public OutputShoppingCartGoods outputShoppingCartGoods() {
		return new OutputShoppingCartGoods();
	}

	@Bean
	public OutputOrder outputOrder() {
		return new OutputOrder();
	}

	@Bean
	public OutputOrderDetail OoutputOrderDetail() {
		return new OutputOrderDetail();
	}

	@Bean
	public OutputOrderQuery outputOrderQuery() {
		return new OutputOrderQuery();
	}
	
	@Bean
	public OutputResultMessage outputResultMessage() {
		return new OutputResultMessage();
	}
	
	@Bean
	public OutputGoods outputGoods() {
		return new OutputGoods();
	}
	
	@Bean
	public OutputGoodsQuery outputGoodsQuery() {
		return new OutputGoodsQuery();
	}
	
	@Bean
	public OutputMember outputMember() {
		return new OutputMember();
	}
	
	@Bean
	public InputMemberLogin inputMember() {
		return new InputMemberLogin();
	}
	
	@Bean
	public OutputMemberModify outputMemberModify() {
		return new OutputMemberModify();
	}
	
	@Bean
	public InputVendingMachine inputVendingMachine() {
		return new InputVendingMachine();
	}
	
	@Bean
	public OutputLogin outputLogin() {
		return new OutputLogin();
	}
	
	@Bean
	public OutputLogout outputLogout() {
		return new OutputLogout();
	}
	
	@Bean
	public OutputGoodsModify outputGoodsModify() {
		return new OutputGoodsModify();
	}
	
	@Bean
	public OutputShoppingCartGoodsAdd outputShoppingCartGoodsAdd() {
		return new OutputShoppingCartGoodsAdd();
	}
	
	@Bean
	public OutputPayment outputPayment() {
		return new OutputPayment();
	}
	
}