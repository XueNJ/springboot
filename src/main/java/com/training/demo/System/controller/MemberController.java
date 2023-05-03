package com.training.demo.System.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.demo.service.MemberService;
import com.training.demo.vo.InputMemberLogin;
import com.training.demo.vo.InputMemberModify;
import com.training.demo.vo.OutputLogin;
import com.training.demo.vo.OutputLogout;
import com.training.demo.vo.OutputMemberModify;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/MemberController")
public class MemberController {
	
	// ------------------------------ Autowired ----------------------------
	@Autowired
	private MemberService memberService;
	// ------------------------------ Backend ------------------------------
	/*
	 * Login :
	 * 
	 * 測試範例(json): 
	 {
		"id": 1,
		"pwd": "1"
	 }
	 */
	@ApiOperation(value = "登入系統...")
	@PostMapping(value = "/login")
	public ResponseEntity<OutputLogin> login(@RequestBody InputMemberLogin vo) throws IOException {
		return ResponseEntity.ok(memberService.login(vo));
	}

	/*
	 * Logout :
	 * 
	 * 測試範例(無):
	 * 
	 */
	@ApiOperation(value = "登出系統...")
	@PostMapping(value = "/logout")
	public ResponseEntity<OutputLogout> logout() {
		return ResponseEntity.ok(memberService.logout());
	}
	
	/*
	 * Register & Modify :
	 * 
	 * 測試範例新增(json):
		 {
			  "action": "Create",
			  "id": "1",
			  "name": "HelloKitty",
			  "pwd": "1",
			  "pwdCheck": "1",
			  "status": "1"
		 }
	 * 
	 * 測試範例修改(json):
		 {
			  "action": "",
			  "id": "1",
			  "name": "Max",
			  "pwd": "1",
			  "pwdCheck": "1",
			  "status": "1"
		 }
	 * 
	 */
	@ApiOperation(value = "新增&更新人員...")
	@PostMapping(value = "/memberModify")
	public ResponseEntity<OutputMemberModify> memberModify(@RequestBody InputMemberModify vo) throws IOException {
		return ResponseEntity.ok(memberService.memberModify(vo));
	}
	
}
